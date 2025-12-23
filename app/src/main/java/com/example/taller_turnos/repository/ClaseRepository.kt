package com.example.taller_turnos.repository

import android.content.Context
import com.example.taller_turnos.database.ClaseDBHelper
import com.example.taller_turnos.model.Clase

class ClaseRepository private constructor(
    private val db: ClaseDBHelper
){
    fun obtenerClases(): List<Clase> = db.getAll()

    fun toggleAnotado(clase: Clase) {
        val nueva = if (clase.anotado) {
            clase.copy(
                anotado = false,
                inscriptos = clase.inscriptos - 1
            )
        } else {
            clase.copy(
                anotado = true,
                inscriptos = clase.inscriptos + 1
            )
        }
        db.updateClase(nueva)
    }

    companion object {
        @Volatile private var INSTANCE: ClaseRepository? = null

        fun getInstance(context: Context): ClaseRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ClaseRepository(
                    ClaseDBHelper(context.applicationContext)
                ).also { INSTANCE = it }
            }
        }
    }

}