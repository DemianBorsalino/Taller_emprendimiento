package com.example.taller_turnos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taller_turnos.model.Clase

class ClaseViewModel : ViewModel() {

    private val _clases = MutableLiveData<List<Clase>>()
    val clases: LiveData<List<Clase>> = _clases

    init {
        _clases.value = listOf(
            Clase(1, "Crochet inicial","Pimpinela", 6, 4),
            Clase(2, "Crochet inicial", "Pimpinela", 6, 3)
        )
    }

    fun agregarClase(clase : Clase) {
        val listaActual = _clases.value?.toMutableList() ?: mutableListOf()
        listaActual.add(clase)
        _clases.value = listaActual
    }

    fun eliminarClase(clase: Clase) {
        val listaActual = _clases.value?.toMutableList() ?: return
        listaActual.remove(clase)
        _clases.value = listaActual
    }

    fun anotarse(claseId: Int) {
        val lista = _clases.value?.toMutableList() ?: return

        val index = lista.indexOfFirst { it.id == claseId }
        if (index != -1) {
            val clase = lista[index]
            if (clase.inscriptos < clase.cupoMaximo) {
                lista[index] = clase.copy(
                    inscriptos = clase.inscriptos + 1
                )
                _clases.value = lista
            }
        }
    }

    fun toggleInscpricion(claseId : Int) {
        val lista = _clases.value?.toMutableList() ?: return

        val index = lista.indexOfFirst { it.id == claseId }
        if (index == -1 ) return

        val clase = lista[index]

        if (!clase.estoyInscrpito && clase.inscriptos < clase.cupoMaximo) {
            lista[index] = clase.copy(
                clase.inscriptos + 1,
                estoyInscrpito = true
            )
        } else if (clase.estoyInscrpito) {
            lista[index] =  clase.copy(
                inscriptos = clase.inscriptos -1,
                estoyInscrpito = false
            )
        }

        _clases.value = lista
    }

}