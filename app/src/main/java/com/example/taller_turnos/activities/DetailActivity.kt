package com.example.taller_turnos.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.taller_turnos.databinding.ActivityClassDetailBinding
import com.example.taller_turnos.model.Clase
import com.example.taller_turnos.viewModel.ClaseViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClassDetailBinding
    private val viewModel: ClaseViewModel by viewModels()

    private var claseId: Int = -1

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        binding= ActivityClassDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        claseId = intent.getIntExtra("CLASE_ID", -1)

        observarClase()

        binding.btnAccion.setOnClickListener {
            viewModel.toggleInscpricion(claseId)
        }


    }

    private fun observarClase() {
        viewModel.clases.observe(this) { lista ->
            val clase = lista.find { it.id == claseId }
            clase?.let {
                mostarClase(it)
            }
        }
    }

    private fun mostarClase(clase: Clase) {
        binding.tvNombre.text = clase.nombre
        binding.tvNombreMeastra.text = "Maestra : ${clase.nombreMaestra}"
        binding.tvCupo.text = "Cupo ${clase.inscriptos}/${clase.cupoMaximo}"

        when {
            clase.estoyInscrpito -> {
                binding.btnAccion.text = "Darme de baja"
                binding.btnAccion.isEnabled = true
            }
            clase.inscriptos >= clase.cupoMaximo -> {
                binding.btnAccion.text = "Cupo completo"
                binding.btnAccion.isEnabled = false
            }
            else -> {
                binding.btnAccion.text = "Anotarme"
                binding.btnAccion.isEnabled = true
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}