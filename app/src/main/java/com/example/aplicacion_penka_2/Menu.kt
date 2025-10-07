package com.example.aplicacion_penka_2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<Button>(R.id.btn_crud).setOnClickListener {
            startActivity(Intent(this, Crud::class.java))
        }

        findViewById<Button>(R.id.btn_sensores).setOnClickListener {
            startActivity(Intent(this, Sensores::class.java))
        }
    }
}
