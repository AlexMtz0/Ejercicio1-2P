package com.example.actuno_2p

import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class ListaAdapter (videojuegos: ArrayList<Videojuego>, context: Context): RecyclerView.Adapter<ListaAdapter.ContenedorDeVista>() {
    var inner_videojuegos: ArrayList<Videojuego> = videojuegos
    var inner_context: Context = context

    inner class ContenedorDeVista(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val tvNombreJuego: TextView
        val tvPrecio: TextView
        val tvConsola: TextView
        var btComprar: Button
        val ivImagen: ImageView
        val tvClasificacion: TextView


        init {
            // Define click listener for the ViewHolder's View.
            tvNombreJuego = view.findViewById(R.id.tvNombreJuego)
            tvPrecio = view.findViewById(R.id.tvPrecio)
            tvConsola = view.findViewById(R.id.tvConsola)
            btComprar = view.findViewById(R.id.btComprar)
            ivImagen = view.findViewById(R.id.ivImagen)
            tvClasificacion = view.findViewById(R.id.tvClasificacion)
            btComprar.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val clas = tvClasificacion.text.toString()
            val sharedPreferences = inner_context.getSharedPreferences("PERSISTENCIA", Context.MODE_PRIVATE)
            val edad = sharedPreferences.getInt("edad", 0)
            if (edad < 5 &&  (clas == "R" || clas == "T") ) {
                val alerta = AlertDialog.Builder(inner_context)
                alerta.setTitle("Atención!")
                alerta.setMessage("Tu edad es"+ edad +", esto no te permite comprar juegos clasificación T Y R ")
                alerta.setCancelable(false)
                alerta.setPositiveButton("Aceptar", null)
                alerta.show()
            }else if((edad >= 5 && edad <18) && clas == "R"){
                val alerta = AlertDialog.Builder(inner_context)
                alerta.setTitle("Atención!")
                alerta.setMessage("Tu edad es"+ edad +" y la clasificación " + clas +", no puedes comprar este juego")
                alerta.setCancelable(false)
                alerta.setPositiveButton("Aceptar", null)
                alerta.show()
            }else{
                val alerta = AlertDialog.Builder(inner_context)
                alerta.setTitle("Atención!")
                alerta.setMessage(
                    "¿Estás seguro deseas comprar " + inner_videojuegos.get(
                        adapterPosition
                    ).nombre
                )
                alerta.setCancelable(false)
                alerta.setPositiveButton("Aceptar") { dialogInterface, which ->
                    Toast.makeText(inner_context, "Le dio en aceptar", Toast.LENGTH_SHORT).show()
                }
                alerta.setNegativeButton("Cancelar") { dialogInterface, which ->
                    Toast.makeText(inner_context, "Pues no lo compró", Toast.LENGTH_SHORT).show()
                }
                alerta.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return inner_videojuegos.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ContenedorDeVista {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_interfaz_juego, viewGroup, false)

        return ContenedorDeVista(view)
    }

    override fun onBindViewHolder(viewHolder: ContenedorDeVista, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val videojuego: Videojuego = inner_videojuegos.get(position)
        viewHolder.tvNombreJuego.text = videojuego.nombre
        viewHolder.tvPrecio.text = videojuego.precio.toString()
        viewHolder.tvConsola.text = videojuego.consola
        viewHolder.ivImagen.setImageResource(inner_videojuegos.get(position).imagen)
        viewHolder.tvClasificacion.text = videojuego.clasificacion
    }
}