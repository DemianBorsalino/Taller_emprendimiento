package com.example.taller_turnos.activities

import android.content.Intent
import android.view.MenuItem
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taller_turnos.R
import com.example.taller_turnos.adapter.ClaseAdapter
import com.example.taller_turnos.databinding.ActivityClassListBinding
import com.example.taller_turnos.model.Clase
import com.example.taller_turnos.viewModel.ClaseViewModel


class ListClassActivity: AppCompatActivity() {

    private lateinit var binding: ActivityClassListBinding
    private lateinit var adapter: ClaseAdapter

    private val viewmodel: ClaseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)

        adapter = ClaseAdapter(emptyList()) { clase ->
            irADetalle(clase)
        }

        binding.rvClasses.layoutManager = LinearLayoutManager(this)
        binding.rvClasses.adapter = adapter

        viewmodel.clases.observe(this) { lista ->
            adapter.actualizarLista(lista)
        }

        binding.fabCreateClass.setOnClickListener {  }


    }

    private fun irADetalle(clase: Clase) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("Clase_ID", clase.id)
        startActivity(intent)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // Ir a configuración (más adelante)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
