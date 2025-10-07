package com.example.aplicacion_penka_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class OlvideContrasena : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvide_contrasena)

        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        val inputCodigo = findViewById<EditText>(R.id.inputCodigo)
        val inputNueva = findViewById<EditText>(R.id.inputNueva)
        val inputConfirmar = findViewById<EditText>(R.id.inputConfirmar)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnEnviar.setOnClickListener {
            Toast.makeText(this, "Correo enviado a ${inputEmail.text}", Toast.LENGTH_SHORT).show()
        }

        btnGuardar.setOnClickListener {
            Toast.makeText(this, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show()
        }
    }
}
