package com.example.taller_turnos.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taller_turnos.model.Clase
import com.example.taller_turnos.repository.ClaseRepository

class ClaseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ClaseRepository.getInstance(application)

    private val _clases = MutableLiveData<List<Clase>>()
    val clases: LiveData<List<Clase>> = _clases

    init {
        cargarClases()
    }

    fun cargarClases() {
        _clases.value = repository.obtenerClases()
    }

    fun toggleAnotado(clase: Clase) {
        repository.toggleAnotado(clase)
        cargarClases()
    }

}