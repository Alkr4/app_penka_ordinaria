package com.example.aplicacion_penka_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("¡Inicio de sesión exitoso!")
                .setContentText("Has iniciado sesión correctamente")
                .setConfirmText("Continuar")
                .setConfirmClickListener { dialog ->
                    dialog.dismissWithAnimation()
                    startActivity(Intent(this, Menu::class.java))
                }
                .show()

            findViewById<TextView>(R.id.tv_register).setOnClickListener {
                startActivity(Intent(this, Ingreso::class.java))
            }

            findViewById<TextView>(R.id.tv_forgot).setOnClickListener {
                startActivity(Intent(this, OlvideContrasena::class.java))
            }
        }
    }
}
