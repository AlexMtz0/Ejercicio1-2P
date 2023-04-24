package com.example.actuno_2p

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Preferencias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferencias)
        var tvPTitulo = findViewById<TextView>(R.id.tvPTitulo)
        var tvPNombre = findViewById<TextView>(R.id.tvPNombre)
        var tvPEdad = findViewById<TextView>(R.id.tvPEdad)
        var tvPAltura = findViewById<TextView>(R.id.tvPAltura)
        var tvPPre = findViewById<TextView>(R.id.tvPPre)
        var btPRegresar = findViewById<Button>(R.id.btPRegresar)

        val sharedPreference = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE) ?: return
        val nombre = sharedPreference.getString("nombre", "")
        val edad = sharedPreference.getInt("edad", 0).toString()
        val altura = sharedPreference.getFloat("altura", 0.0f).toString()
        val dinero = sharedPreference.getFloat("dinero", 0.0f).toString()
        tvPNombre.text = nombre
        tvPEdad.text = edad
        tvPAltura.text = altura
        tvPPre.text = dinero

        btPRegresar.setOnClickListener{
            val s = Intent(this,ListaDeJuegos::class.java)
            startActivity(s)
        }
    }
}