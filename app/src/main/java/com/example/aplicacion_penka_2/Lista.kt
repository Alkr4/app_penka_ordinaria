package com.example.aplicacion_penka_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Lista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        Toast.makeText(this, "Cargando lista de usuarios...", Toast.LENGTH_SHORT).show()
        setupEditButtons()
    }

    private fun setupEditButtons() {
        // For each edit button, set up click listeners
        findViewById<Button>(R.id.btnEditar1).setOnClickListener {
            val intent = Intent(this, ModificarEliminar::class.java).apply {
                putExtra("nombre", "Juan")
                putExtra("apellido", "Pérez")
                putExtra("email", "juan@email.com")
                putExtra("telefono", "+56 9 1234 5678")
            }
            startActivity(intent)
        }

        // You can add more buttons for other users
        // For now, we'll make all edit buttons go to the same user for demo
    }
}