package com.example.tfg

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tfg.Data.Model.jugadoresActivos
import com.example.tfg.Data.RetrofitService_JA
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.launch


class ViewJuego : AppCompatActivity() {

    lateinit var tv_resultado : TextView
    lateinit var btn_comprobar : Button
    lateinit var btn_siguiente : Button
    lateinit var tv_dorsal : TextView
    lateinit var tv_equipo : TextView
    lateinit var tv_alturaYpeso : TextView
    lateinit var tv_posicion : TextView
    lateinit var nombreJugador : EditText
    lateinit var numeroAleatorio : TextView
    lateinit var idUser : String
    var intentosNoActu : Int = 0
    var aciertosNoActu : Int = 0

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val mDatabase = database.getReference("usuarios")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_juego)

        tv_resultado = findViewById(R.id.tv_resultado)
        btn_comprobar = findViewById(R.id.btn_comprobar)
        btn_siguiente = findViewById(R.id.btn_siguiente)
        tv_dorsal = findViewById(R.id.tv_dorsal)
        tv_equipo = findViewById(R.id.tv_equipo)
        tv_alturaYpeso = findViewById(R.id.tv_alturaYpeso)
        tv_posicion = findViewById(R.id.tv_posicion)
        nombreJugador = findViewById(R.id.nombreJugador)
        numeroAleatorio = findViewById(R.id.numeroAleatorio)

        val service = RetrofitService_JA.RetrofitServiceFactory.makeRetrofitService()
        lifecycleScope.launch {
            val players = service.activePlayers("96abec686d594a69ab55fd8aceeffbf3")
            val numAleatorio = (0..players.size).random()
            numeroAleatorio.text = players[numAleatorio].YahooName
            pintar( players , numAleatorio )

            btn_siguiente.setOnClickListener {
                val numAleatorio = (0..players.size).random()
                numeroAleatorio.text = players[numAleatorio].YahooName
                pintar( players , numAleatorio )
            }

            btn_comprobar.setOnClickListener {
                comprobar()
            }
        }
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    fun pintar( players: jugadoresActivos, numeroAleatorio: Int )
    {
        btn_comprobar.isEnabled = true
        tv_dorsal.text = players[numeroAleatorio].Jersey.toString()
        val posicion = players[numeroAleatorio].Position
        when ( posicion )
        {
            "PG" -> tv_posicion.text = "BASE"
            "SG" -> tv_posicion.text = "ESCOLTA"
            "SF" -> tv_posicion.text = "ALERO"
            "PF" -> tv_posicion.text = "ALA-PIVOT"
            "C" -> tv_posicion.text = "PIVOT"
        }
        val equipo = players[numeroAleatorio].Team
        when ( equipo )
        {
            "BOS" -> tv_equipo.text = "Boston Celtics - ESTE"
            "MIL" -> tv_equipo.text = "Milwaukee Bucks - ESTE"
            "CLE" -> tv_equipo.text = "Cleveland Cavaliers - ESTE"
            "ORL" -> tv_equipo.text = "Orlando Magic - ESTE"
            "NY" -> tv_equipo.text = "New York Knicks - ESTE"
            "MIA" -> tv_equipo.text = "Miami Heat - ESTE"
            "PHI" -> tv_equipo.text = "Philadelphia 76ers - ESTE"
            "IND" -> tv_equipo.text = "Indiana Pacers - ESTE"
            "CHI" -> tv_equipo.text = "Chicago Bulls - ESTE"
            "BKN" -> tv_equipo.text = "Brooklyn Nets - ESTE"
            "TOR" -> tv_equipo.text = "Toronto Raptors - ESTE"
            "CHA" -> tv_equipo.text = "Charlotte Hornets - ESTE"
            "DET" -> tv_equipo.text = "Detroit Pistons - ESTE"
            "WAS" -> tv_equipo.text = "Washington Wizards - ESTE"
            "MIN" -> tv_equipo.text = "Minnesota Timberwolves - OESTE"
            "OKC" -> tv_equipo.text = "Oklahoma City Thunder - OESTE"
            "DEN" -> tv_equipo.text = "Denver Nuggets - OESTE"
            "LAC" -> tv_equipo.text = "Los Angeles Clippers - OESTE"
            "NO" -> tv_equipo.text = "New Orleans Pelicans - OESTE"
            "PHO" -> tv_equipo.text = "Phoenix Suns - OESTE"
            "SAC" -> tv_equipo.text = "Sacramento Kings - OESTE"
            "DAL" -> tv_equipo.text = "Dallas Mavericks - OESTE"
            "GS" -> tv_equipo.text = "Golden State Warriors - OESTE"
            "LAL" -> tv_equipo.text = "Los Angeles Lakers - OESTE"
            "UTA" -> tv_equipo.text = "Utah Jazz - OESTE"
            "HOU" -> tv_equipo.text = "Houston Rockets - OESTE"
            "MEM" -> tv_equipo.text = "Memphis Grizzlies - OESTE"
            "POR" -> tv_equipo.text = "Porland Trail Blazers  - OESTE"
            "SA" -> tv_equipo.text = "San Antonio Spurs  - OESTE"
        }
        val alturaInt = players[numeroAleatorio].Height
        val alturaOrg = alturaInt * 2.54
        val alturaRedondeada = alturaOrg.toInt()
        val altura = String.format( "%d,%02d" , alturaRedondeada / 100 , alturaRedondeada % 100 )
        val pesoInt = players[numeroAleatorio].Weight
        val pesoSinParse = pesoInt / 2.205
        val pesoDouble = pesoSinParse.toDouble()
        val peso = "%.1f".format(pesoDouble).toDouble()
        tv_alturaYpeso.text = "$altura m , $peso kg"


    }

    @SuppressLint("ResourceAsColor")
    fun comprobar ()
    {
        val nombreJugador2 : String = nombreJugador.text.toString()
        val nombreJugadorAdivinar : String = numeroAleatorio.text.toString()
        if ( nombreJugador2 == nombreJugadorAdivinar )
        {
            tv_resultado.setBackgroundColor( R.color.green )
            tv_resultado.text = "Acertaste!"
        }
        else
        {
            tv_resultado.setBackgroundColor( R.color.red )
            tv_resultado.text = "Fallaste, el jugador era " + nombreJugadorAdivinar
        }
    }

}