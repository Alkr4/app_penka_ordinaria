package com.example.aplicacion_penka_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import cn.pedant.SweetAlert.SweetAlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Ingreso : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var lastname: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var password: EditText
    private lateinit var repeatPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var datos: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)

        name = findViewById(R.id.et_name)
        lastname = findViewById(R.id.et_lastname)
        email = findViewById(R.id.et_email)
        phone = findViewById(R.id.et_phone)
        password = findViewById(R.id.et_password)
        repeatPassword = findViewById(R.id.et_repeat_password)
        btnRegister = findViewById(R.id.btn_register)

        datos = Volley.newRequestQueue(this)

        btnRegister.setOnClickListener {
            if (password.text.toString() == repeatPassword.text.toString() &&
                name.text.isNotEmpty() && lastname.text.isNotEmpty() &&
                email.text.isNotEmpty() && phone.text.isNotEmpty()
            ) {
                registrarUsuario()
            } else if (password.text.toString() != repeatPassword.text.toString()){
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Las contraseñas no coinciden")
                    .show()
            } else {
                SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Atención")
                    .setContentText("Debe completar todos los campos")
                    .show()
            }
        }
    }

    private fun registrarUsuario() {
        val url = "http://18.211.13.143/registro.php?name=${name.text}&lastname=${lastname.text}&email=${email.text}&phone=${phone.text}&password=${password.text}"
        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("¡Éxito!")
                    .setContentText("Usuario registrado correctamente.")
                    .setConfirmClickListener {
                        it.dismissWithAnimation()
                        finish()
                    }
                    .show()
            },
            { error ->
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Error al registrar el usuario: ${error.message}")
                    .show()
            }
        )
        datos.add(request)
    }
}