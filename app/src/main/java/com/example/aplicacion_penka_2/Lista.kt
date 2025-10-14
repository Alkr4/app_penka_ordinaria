package com.example.aplicacion_penka_2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


class Lista : AppCompatActivity() {
    private lateinit var list: ListView
    private lateinit var datos: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        list = findViewById(R.id.listado_usuarios)
        datos = Volley.newRequestQueue(this)
        cargaLista()
    }

    private fun cargaLista() {
        val listaUsu = ArrayList<String>()
        val url = "http://18.211.13.143/consultas.php"
        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val json = JSONArray(response)
                    for (i in 0 until json.length()) {
                        val usuarios = json.getJSONObject(i)
                        val linea =
                            "${usuarios.getString("Nombre")} ${usuarios.getString("Usuario")}"
                        listaUsu.add(linea)
                    }
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUsu)
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