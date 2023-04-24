package com.example.actuno_2p

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView.RecyclerListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaDeJuegos : AppCompatActivity() {
    private lateinit var rvListado: RecyclerView
    private lateinit var listaJuegos: ArrayList<Videojuego>


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_juegos)
        poblarLista() //Funcion para meter información al array
        rvListado = findViewById(R.id.rvListadeJuegos) //RV
        //rvListado.setHasFixedSize(true)
        val CANTIDAD_COLUMNAS = 1 //Num de columnas para organizar el RV
        var administradorDeLayouts =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        administradorDeLayouts =  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        administradorDeLayouts = GridLayoutManager(this,CANTIDAD_COLUMNAS)

        rvListado.layoutManager = administradorDeLayouts //Lo ponemos en un grid (tabla) de dos columnas)
        rvListado.adapter = ListaAdapter(listaJuegos, this) //Enviamos al adaptder la información del array

        val fabPref = findViewById<FloatingActionButton>(R.id.fabPreferencias)

        fabPref.setOnClickListener{
            mostrarPrefencias()
        }
    }


    private fun poblarLista(){
        listaJuegos = ArrayList()
        val juegos = arrayOf<String>("Mario Bros", "Halo", "Tales of the Abyss", "MineCraft", "SackBoy", "Smash Bros", "OverWatch")
        val precios = arrayOf<Float>(1000.0F, 1300.0F, 999.99F, 235.0F, 750.0f, 1100.0F, 888.50F )
        val imagenes = arrayOf<Int>(R.drawable.mario,R.drawable.halo, R.drawable.abyss, R.drawable.minecraft, R.drawable.sackboy, R.drawable.mario, R.drawable.overwatch )
        val consolas = arrayOf<String>("Nintentdo", "Xbox", "Playstation", "P.C", "Playstation", "Nintento", "Playstation")
        val clas = arrayOf<String>("R","T","R","T","R","T","R")
        val videojuego1 = Videojuego(8,"Resident Evil", 1300.0F, "MultiPlataforma","R", R.drawable.game04)
        val videojuego2 = Videojuego(9,"Cube Adventura", 500.0F, "Juegosfera","T", R.drawable.halo)
        listaJuegos.add(videojuego1)
        listaJuegos.add(videojuego2)
        for ( i in juegos.indices){
            listaJuegos.add(Videojuego(i,juegos[i],precios[i], consolas[i],clas[i],imagenes[i] ))
        }
    }

    private fun mostrarPrefencias(){
        val s = Intent(this, Preferencias::class.java)
        startActivity(s)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_vertical -> {
                val CANTIDAD_COLUMNAS = 1 //Num de columnas para organizar el RV
                var administradorDeLayouts =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                administradorDeLayouts =  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                administradorDeLayouts = GridLayoutManager(this,CANTIDAD_COLUMNAS)

                rvListado.layoutManager = administradorDeLayouts //Lo ponemos en un grid (tabla) de dos columnas)
                rvListado.adapter = ListaAdapter(listaJuegos, this) //Enviamos al adaptder la información del array
                return true
            }
            R.id.menu_horizontal -> {
                //val CANTIDAD_COLUMNAS = 1 //Num de columnas para organizar el RV
                var administradorDeLayouts =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                administradorDeLayouts =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                //administradorDeLayouts = GridLayoutManager(this,CANTIDAD_COLUMNAS)

                rvListado.layoutManager = administradorDeLayouts //Lo ponemos en un grid (tabla) de dos columnas)
                rvListado.adapter = ListaAdapter(listaJuegos, this) //Enviamos al adaptder la información del array
                return true
            }
            R.id.menu_grid_2 -> {
                val CANTIDAD_COLUMNAS = 2 //Num de columnas para organizar el RV
                var administradorDeLayouts =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                administradorDeLayouts =  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                administradorDeLayouts = GridLayoutManager(this,CANTIDAD_COLUMNAS)

                rvListado.layoutManager = administradorDeLayouts //Lo ponemos en un grid (tabla) de dos columnas)
                rvListado.adapter = ListaAdapter(listaJuegos, this) //Enviamos al adaptder la información del array
                return true
            }
            R.id.menu_grid_3 -> {
                val CANTIDAD_COLUMNAS = 3 //Num de columnas para organizar el RV
                var administradorDeLayouts =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                administradorDeLayouts =  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                administradorDeLayouts = GridLayoutManager(this,CANTIDAD_COLUMNAS)

                rvListado.layoutManager = administradorDeLayouts //Lo ponemos en un grid (tabla) de dos columnas)
                rvListado.adapter = ListaAdapter(listaJuegos, this) //Enviamos al adaptder la información del array
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}