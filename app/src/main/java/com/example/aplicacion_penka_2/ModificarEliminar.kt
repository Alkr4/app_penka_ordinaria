package com.example.aplicacion_penka_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ModificarEliminar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_eliminar)

        val etNombre = findViewById<EditText>(R.id.et_nombre)
        val etApellido = findViewById<EditText>(R.id.et_apellido)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etTelefono = findViewById<EditText>(R.id.et_telefono)
        val btnModificar = findViewById<Button>(R.id.btn_modificar)
        val btnEliminar = findViewById<Button>(R.id.btn_eliminar)

        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val telefono = intent.getStringExtra("telefono") ?: ""

        etNombre.setText(nombre)
        etApellido.setText(apellido)
        etEmail.setText(email)
        etTelefono.setText(telefono)

        btnModificar.setOnClickListener {
            val nuevoNombre = etNombre.text.toString()
            val nuevoApellido = etApellido.text.toString()
            val nuevoEmail = etEmail.text.toString()
            val nuevoTelefono = etTelefono.text.toString()

            if (nuevoNombre.isNotEmpty() && nuevoApellido.isNotEmpty() && nuevoEmail.isNotEmpty()) {
                Toast.makeText(this, "Usuario modificado: $nuevoNombre $nuevoApellido", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnEliminar.setOnClickListener {
            Toast.makeText(this, "Usuario eliminado: $nombre $apellido", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}