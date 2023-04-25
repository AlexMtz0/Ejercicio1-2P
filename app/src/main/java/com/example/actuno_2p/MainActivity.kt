package com.example.actuno_2p

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvDatosSolicitados: TextView
    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etAltura: EditText
    private lateinit var etDinero: EditText
    private lateinit var swPrefe: Switch
    private lateinit var btContinuar: Button
    private val NOMBRE = "nombre"
    private val EDAD = "edad"
    private val ALTURA = "altura"
    private val DINERO = "dinero"
    private val SWITCH = "switch_estado"
    private var nombre: String = ""
    private var edad: Int = 0
    private var altura: Float = 0.0f
    private var dinero: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Vistas()
        Log.d("PREFERENCIAS", savedInstanceState?.getString(NOMBRE).toString())
    }
    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("PREFERENCIAS", "onSaveInstanceState")
        outState.putString(NOMBRE, nombre )
        outState.putInt(EDAD, edad )
        outState.putFloat(ALTURA, altura )
        outState.putFloat(DINERO, dinero )
        outState?.run {
            putString(NOMBRE, nombre)
            putInt(EDAD, edad)
            putFloat(ALTURA, altura)
            putFloat(DINERO, dinero)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        Log.d("PREFERENCIAS", "onResume")
        val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
        val switchActivado = miSharedPreferences.getBoolean(SWITCH, false)
        if (switchActivado) {
            nombre = miSharedPreferences.getString(NOMBRE, "").toString()
            edad = miSharedPreferences.getInt(EDAD, 0)
            altura = miSharedPreferences.getFloat(ALTURA, 0.0f)
            dinero = miSharedPreferences.getFloat(DINERO, 0.0f)
        }
        //tvDatosSolicitados.text = nombre
        super.onResume()
    }

    override fun onStart() {
        Log.d("PREFERENCIAS", "onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d("PREFERENCIAS", "onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("PREFERENCIAS", "onDestroy")
        super.onDestroy()
    }

    private fun Vistas() {
        tvDatosSolicitados = findViewById(R.id.tvDatosSolicitados)
        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etAltura = findViewById(R.id.etAltura)
        etDinero = findViewById(R.id.etDinero)
        swPrefe = findViewById(R.id.swPrefe)
        btContinuar = findViewById(R.id.btContinuar)
        swPrefe.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
                val editor = miSharedPreferences.edit()
                editor.putString(NOMBRE, nombre)
                editor.putInt(EDAD, edad)
                editor.putFloat(ALTURA, altura)
                editor.putFloat(DINERO, dinero)
                editor.putBoolean(SWITCH, true)
                editor.apply()
            } else {
                val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
                val editor = miSharedPreferences.edit()
                editor.putBoolean(SWITCH, false)
                editor.apply()
            }
        }
        btContinuar.setOnClickListener {
            nombre = etNombre.text.toString()
            edad = etEdad.text.toString().toInt()
            altura = etAltura.text.toString().toFloat()
            dinero = etDinero.text.toString().toFloat()
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            val editor = miSharedPreferences.edit()
            editor.putString(NOMBRE, nombre)
            editor.putInt(EDAD, edad)
            editor.putFloat(ALTURA, altura)
            editor.putFloat(DINERO, dinero)
            editor.apply()
            val s = Intent(this, ListaDeJuegos::class.java)
            startActivity(s)
            finish()
        }

    }



}