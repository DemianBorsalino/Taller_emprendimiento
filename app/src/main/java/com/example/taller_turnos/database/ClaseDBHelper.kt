package com.example.taller_turnos.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.taller_turnos.model.Clase

class ClaseDBHelper(context: Context)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    companion object {
        const val DB_NAME = "Taller.db"
        const val DB_VERSION = 1

        const val TABLE = "Clases"
        const val Col_ID = "id"
        const val Col_Nombre = "nombre"
        const val Col_Maestra = "maestra"
        const val Col_Cupo = "cupo"
        const val Col_Inscriptos = "inscriptos"
        const val Col_Anotado = "anotado"

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            Create Table $TABLE (
            $Col_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $Col_Nombre TEXT,
            $Col_Maestra TEXT,
            $Col_Cupo INTEGER,
            $Col_Inscriptos INTEGER,
            $Col_Anotado INTEGER
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE")
        onCreate(db)
    }

    fun getAll(): List<Clase>{
        val db = readableDatabase
        val cursor = db.query(TABLE, null, null, null, null, null, null)

        val lista = mutableListOf<Clase>()
        cursor.use {
            while (it.moveToNext()) {
                lista.add(
                    Clase(
                        id = it.getInt(it.getColumnIndexOrThrow(Col_ID)),
                        nombre = it.getString(it.getColumnIndexOrThrow(Col_Nombre)),
                        nombreMaestra = it.getString(it.getColumnIndexOrThrow(Col_Maestra)),
                        cupoMaximo = it.getInt(it.getColumnIndexOrThrow(Col_Cupo)),
                        inscriptos = it.getInt(it.getColumnIndexOrThrow(Col_Inscriptos)),
                        anotado = it.getInt(it.getColumnIndexOrThrow(Col_Anotado)) == 1
                    )
                )
            }
        }
        db.close()
        return lista
    }

    fun updateClase(clase: Clase) {
        val cv = ContentValues().apply {
            put(Col_Inscriptos,clase.inscriptos)
            put(Col_Anotado,if(clase.anotado) 1 else 0)
        }
        writableDatabase.update(
            TABLE,
            cv,
            "$Col_ID=?",
            arrayOf(clase.id.toString())
        )
    }

}