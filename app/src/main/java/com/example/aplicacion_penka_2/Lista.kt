package com.example.aplicacion_penka_2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import cn.pedant.SweetAlert.SweetAlertDialog

class Lista : AppCompatActivity() {
    private lateinit var list: ListView
    private lateinit var datos: RequestQueue
    private var listaUsuarios = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        list = findViewById(R.id.listado_usuarios)
        datos = Volley.newRequestQueue(this)

        list.setOnItemClickListener { parent, view, position, id ->
            val usuarioSeleccionado = listaUsuarios[position]
            val partes = usuarioSeleccionado.split("|")

            if (partes.size >= 5) {
                val intent = Intent(this, ModificarEliminar::class.java).apply {
                    putExtra("id", partes[0]) // Pasa el ID
                    putExtra("nombre", partes[1])
                    putExtra("apellido", partes[2])
                    putExtra("email", partes[3])
                    putExtra("telefono", partes[4])
                }
                startActivity(intent)
            } else {
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Error al procesar los datos del usuario.")
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        cargaLista()
    }

    private fun cargaLista() {
        listaUsuarios.clear()
        val url = "http://18.211.13.143/consultas.php"
        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val json = JSONArray(response)
                    for (i in 0 until json.length()) {
                        val usuarios = json.getJSONObject(i)
                        val linea =
                            "${usuarios.getString("id")}|${usuarios.getString("nombre")}|${usuarios.getString("apellido")}|${usuarios.getString("email")}|${usuarios.getString("telefono")}"
                        listaUsuarios.add(linea)
                    }
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUsuarios)
                    list.adapter = adapter
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            { error ->
                error.printStackTrace()
            }
        )
        datos.add(request)
    }
}