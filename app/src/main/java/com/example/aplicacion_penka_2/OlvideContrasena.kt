package com.example.aplicacion_penka_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import cn.pedant.SweetAlert.SweetAlertDialog
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class OlvideContrasena : AppCompatActivity() {
    private lateinit var inputEmail: EditText
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvide_contrasena)

        inputEmail = findViewById(R.id.inputEmail)
        btnEnviar = findViewById(R.id.btnEnviar)

        // Los campos de código y nueva contraseña ya no se usarán en la app.
        // El usuario completará el proceso en la página web que creamos.
        val inputCodigo = findViewById<EditText>(R.id.inputCodigo)
        val inputNueva = findViewById<EditText>(R.id.inputNueva)
        val inputConfirmar = findViewById<EditText>(R.id.inputConfirmar)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        // Se deshabilita la visibilidad y funcionalidad de los campos que ya no son necesarios.
        inputCodigo.visibility = android.view.View.GONE
        inputNueva.visibility = android.view.View.GONE
        inputConfirmar.visibility = android.view.View.GONE
        btnGuardar.visibility = android.view.View.GONE


        btnEnviar.setOnClickListener {
            val email = inputEmail.text.toString().trim()
            if (email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                val pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                pDialog.titleText = "Enviando..."
                pDialog.setCancelable(false)
                pDialog.show()
                enviarCorreoRecuperacion(email, pDialog)
            } else {
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Por favor, ingresa un correo electrónico válido.")
                    .show()
            }
        }
    }

    private fun enviarCorreoRecuperacion(email: String, pDialog: SweetAlertDialog) {
        // Asegúrate que la URL apunte a tu script en el servidor
        val url = "http://18.211.13.143/enviar_recuperacion.php?email=$email"
        val datos = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                pDialog.dismissWithAnimation()
                try {
                    val status = response.getString("status")
                    val message = response.getString("message")

                    if (status == "success") {
                        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("¡Éxito!")
                            .setContentText("Se ha enviado un correo con las instrucciones.")
                            .show()
                    } else {
                        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .setContentText(message)
                            .show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText("Respuesta inesperada del servidor.")
                        .show()
                }
            },
            { error ->
                pDialog.dismissWithAnimation()
                error.printStackTrace()
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error de Conexión")
                    .setContentText("No se pudo conectar al servidor. Revisa tu conexión a internet.")
                    .show()
            }
        )

        request.retryPolicy = DefaultRetryPolicy(
            10000, // 10 segundos de tiempo de espera
            0,     // 0 reintentos
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        datos.add(request)
    }
}