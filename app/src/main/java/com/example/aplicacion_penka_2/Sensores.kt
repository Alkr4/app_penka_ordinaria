package com.example.aplicacion_penka_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class Sensores : AppCompatActivity() {

    private var luzEncendida = false
    private var sonidoEncendido = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensores)

        val txtFechaHora = findViewById<TextView>(R.id.txtFechaHora)
        val txtTemperatura = findViewById<TextView>(R.id.txtTemperatura)
        val txtHumedad = findViewById<TextView>(R.id.txtHumedad)
        val iconTemperatura = findViewById<ImageView>(R.id.iconTemperatura)
        val iconLuz = findViewById<ImageView>(R.id.iconLuz)
        val iconSonido = findViewById<ImageView>(R.id.iconSonido)

        iconTemperatura.setImageResource(R.drawable.ic_temp_moderada)

        iconLuz.setOnClickListener {
            luzEncendida = !luzEncendida
            iconLuz.setImageResource(
                if (luzEncendida) R.drawable.ic_luz_on else R.drawable.ic_luz_off
            )
        }

        iconSonido.setOnClickListener {
            sonidoEncendido = !sonidoEncendido
            iconSonido.setImageResource(
                if (sonidoEncendido) R.drawable.ic_sonido_on else R.drawable.ic_sonido_off
            )
        }
    }
}
