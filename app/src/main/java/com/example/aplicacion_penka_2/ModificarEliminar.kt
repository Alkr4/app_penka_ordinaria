package com.example.aplicacion_penka_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import cn.pedant.SweetAlert.SweetAlertDialog

class ModificarEliminar : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etApellido: EditText
    private lateinit var etEmail: EditText
    private lateinit var etTelefono: EditText
    private lateinit var btnModificar: Button
    private lateinit var btnEliminar: Button
    private lateinit var datos: RequestQueue

    private var originalId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_eliminar)

        etNombre = findViewById(R.id.et_nombre)
        etApellido = findViewById(R.id.et_apellido)
        etEmail = findViewById(R.id.et_email)
        etTelefono = findViewById(R.id.et_telefono)
        btnModificar = findViewById(R.id.btn_modificar)
        btnEliminar = findViewById(R.id.btn_eliminar)

        datos = Volley.newRequestQueue(this)

        val id = intent.getStringExtra("id") ?: ""
        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val telefono = intent.getStringExtra("telefono") ?: ""

        originalId = id

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
                modificarUsuario(nuevoNombre, nuevoApellido, nuevoEmail, nuevoTelefono)
            } else {
                SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("¡Atención!")
                    .setContentText("Complete todos los campos")
                    .show()
            }
        }

        btnEliminar.setOnClickListener {
            eliminarUsuario()
        }
    }

    private fun modificarUsuario(nombre: String, apellido: String, email: String, telefono: String) {
        val url = "http://18.211.13.143/modificar.php?id=$originalId&nombre=$nombre&apellido=$apellido&email=$email&telefono=$telefono"
        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("¡Éxito!")
                    .setContentText("Usuario modificado: $nombre $apellido")
                    .setConfirmClickListener {
                        it.dismissWithAnimation()
                        finish()
                    }
                    .show()
            },
            { error ->
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Error al modificar el usuario: ${error.message}")
                    .show()
            }
        )
        datos.add(request)
    }

    private fun eliminarUsuario() {
        val url = "http://18.211.13.143/eliminar.php?id=$originalId"
        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("¡Éxito!")
                    .setContentText("Usuario eliminado: ${etNombre.text} ${etApellido.text}")
                    .setConfirmClickListener {
                        it.dismissWithAnimation()
                        finish()
                    }
                    .show()
            },
            { error ->
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Error al eliminar el usuario: ${error.message}")
                    .show()
            }
        )
        datos.add(request)
    }
}