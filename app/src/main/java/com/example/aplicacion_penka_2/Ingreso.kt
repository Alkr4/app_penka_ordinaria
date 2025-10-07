package com.example.aplicacion_penka_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Ingreso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)

        val name = findViewById<EditText>(R.id.et_name)
        val lastname = findViewById<EditText>(R.id.et_lastname)
        val email = findViewById<EditText>(R.id.et_email)
        val phone = findViewById<EditText>(R.id.et_phone)
        val password = findViewById<EditText>(R.id.et_password)
        val repeatPassword = findViewById<EditText>(R.id.et_repeat_password)
        val btnRegister = findViewById<Button>(R.id.btn_register)

        btnRegister.setOnClickListener {
            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
