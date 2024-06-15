package com.example.tfg

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.tfg.Data.RetrofitService_JA
import com.example.tfg.Data.RetrofitService_JS
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class Estadisticas : AppCompatActivity() {

    lateinit var nombreJugador : EditText
    lateinit var busqueda : Button
    lateinit var nombre : TextView
    lateinit var datosJugador : ConstraintLayout
    lateinit var dorsalJugador : TextView
    lateinit var equipoJugador : TextView
    lateinit var posicionJugador : TextView
    lateinit var anosNBA : TextView
    lateinit var edadJugador : TextView
    lateinit var paisNacJugador : TextView
    lateinit var pesoJugador : TextView
    lateinit var alturaJugador : TextView
    lateinit var playerID : TextView
    lateinit var puntosJugador : TextView
    lateinit var rebotesJugador : TextView
    lateinit var asistenciasJugador : TextView
    lateinit var robosJugador : TextView
    lateinit var perdidasJugador : TextView
    lateinit var minutosJugador : TextView
    lateinit var tirosLibresJugador : TextView
    lateinit var doblesJugador : TextView
    lateinit var triplesJugador : TextView
    lateinit var noEncontrado : TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)

        nombreJugador = findViewById(R.id.et_nombreJugador)
        busqueda = findViewById(R.id.busqueda)
        nombre = findViewById(R.id.nombre)
        datosJugador = findViewById(R.id.datosJugador)
        dorsalJugador = findViewById(R.id.dorsalJugador)
        equipoJugador = findViewById(R.id.equipoJugador)
        posicionJugador = findViewById(R.id.posicionJugador)
        anosNBA = findViewById(R.id.anosNBAJugador)
        edadJugador = findViewById(R.id.edadJugador)
        paisNacJugador = findViewById(R.id.paisNacJugador)
        pesoJugador = findViewById(R.id.pesoJugador)
        alturaJugador = findViewById(R.id.alturaJugador)
        playerID = findViewById(R.id.id_Jugador)
        puntosJugador = findViewById(R.id.puntosJugador)
        rebotesJugador = findViewById(R.id.rebotesJugador)
        asistenciasJugador = findViewById(R.id.asistenciasJugador)
        robosJugador = findViewById(R.id.robosJugador)
        perdidasJugador = findViewById(R.id.perdidasJugador)
        minutosJugador = findViewById(R.id.minutosJugador)
        tirosLibresJugador = findViewById(R.id.tirosLibresJugador)
        doblesJugador = findViewById(R.id.doblesJugador)
        triplesJugador = findViewById(R.id.triplesJugador)
        noEncontrado = findViewById(R.id.noEncontrado)

        datosJugador.isVisible = false
        noEncontrado.isVisible = false
        playerID.isVisible = false

        val service = RetrofitService_JA.RetrofitServiceFactory.makeRetrofitService()
        val service2 = RetrofitService_JS.RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {
            val players = service.activePlayers("96abec686d594a69ab55fd8aceeffbf3")
            val playerStats = service2.statsPlayers("96abec686d594a69ab55fd8aceeffbf3")

            busqueda.setOnClickListener {
                var i = 0
                var encontrado = false
                do
                {
                    if ( players[i].FirstName == nombreJugador.text.toString() || players[i].LastName == nombreJugador.text.toString() || players[i].YahooName == nombreJugador.text.toString() )
                    {
                        datosJugador.isVisible = true
                        noEncontrado.isVisible = false
                        encontrado = true
                        playerID.text = players[i].PlayerID.toString()
                        var encontrado2 = false
                        var i2 = 0

                        // DATOS JUGADOR

                        val fechaNacimientoStr = players[i].BirthDate
                        val fechaNacimiento = SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" , Locale.getDefault()).parse(fechaNacimientoStr)
                        val fechaActual = Date()
                        val diffInMillis = fechaActual.time - fechaNacimiento.time
                        val edad = TimeUnit.MILLISECONDS.toDays( diffInMillis ) / 365
                        edadJugador.text = edad.toString()
                        val alturaInt = players[i].Height
                        val alturaOrg = alturaInt * 2.54
                        val alturaRedondeada = alturaOrg.toInt()
                        val altura = String.format( "%d,%02d" , alturaRedondeada / 100 , alturaRedondeada % 100 )
                        alturaJugador.text = altura + " m"
                        val pesoInt = players[i].Weight
                        val pesoSinParse = pesoInt / 2.205
                        val pesoDouble = pesoSinParse.toDouble()
                        val peso = "%.1f".format(pesoDouble).toDouble()
                        pesoJugador.text = peso.toString() + " kg"
                        val expNBA = players[i].Experience + 1
                        anosNBA.text = expNBA.toString()
                        val nombreCompleto = players[i].FirstName + " " + players[i].LastName
                        nombre.text = nombreCompleto
                        val dorsal = players[i].Jersey
                        dorsalJugador.text = dorsal.toString()
                        val paisNac = players[i].BirthCountry
                        paisNacJugador.text = paisNac

                        val posicion = players[i].Position
                        when ( posicion )
                        {
                            "PG" -> posicionJugador.text = "BASE"
                            "SG" -> posicionJugador.text = "ESCOLTA"
                            "SF" -> posicionJugador.text = "ALERO"
                            "PF" -> posicionJugador.text = "ALA-PIVOT"
                            "C" -> posicionJugador.text = "PIVOT"
                        }

                        val equipo = players[i].Team
                        when ( equipo )
                        {
                            "BOS" -> equipoJugador.text = "Boston Celtics - ESTE"
                            "MIL" -> equipoJugador.text = "Milwaukee Bucks - ESTE"
                            "CLE" -> equipoJugador.text = "Cleveland Cavaliers - ESTE"
                            "ORL" -> equipoJugador.text = "Orlando Magic - ESTE"
                            "NY" -> equipoJugador.text = "New York Knicks - ESTE"
                            "MIA" -> equipoJugador.text = "Miami Heat - ESTE"
                            "PHI" -> equipoJugador.text = "Philadelphia 76ers - ESTE"
                            "IND" -> equipoJugador.text = "Indiana Pacers - ESTE"
                            "CHI" -> equipoJugador.text = "Chicago Bulls - ESTE"
                            "BKN" -> equipoJugador.text = "Brooklyn Nets - ESTE"
                            "TOR" -> equipoJugador.text = "Toronto Raptors - ESTE"
                            "CHA" -> equipoJugador.text = "Charlotte Hornets - ESTE"
                            "DET" -> equipoJugador.text = "Detroit Pistons - ESTE"
                            "WAS" -> equipoJugador.text = "Washington Wizards - ESTE"
                            "MIN" -> equipoJugador.text = "Minnesota Timberwolves - OESTE"
                            "OKC" -> equipoJugador.text = "Oklahoma City Thunder - OESTE"
                            "DEN" -> equipoJugador.text = "Denver Nuggets - OESTE"
                            "LAC" -> equipoJugador.text = "Los Angeles Clippers - OESTE"
                            "NO" -> equipoJugador.text = "New Orleans Pelicans - OESTE"
                            "PHO" -> equipoJugador.text = "Phoenix Suns - OESTE"
                            "SAC" -> equipoJugador.text = "Sacramento Kings - OESTE"
                            "DAL" -> equipoJugador.text = "Dallas Mavericks - OESTE"
                            "GS" -> equipoJugador.text = "Golden State Warriors - OESTE"
                            "LAL" -> equipoJugador.text = "Los Angeles Lakers - OESTE"
                            "UTA" -> equipoJugador.text = "Utah Jazz - OESTE"
                            "HOU" -> equipoJugador.text = "Houston Rockets - OESTE"
                            "MEM" -> equipoJugador.text = "Memphis Grizzlies - OESTE"
                            "POR" -> equipoJugador.text = "Porland Trail Blazers  - OESTE"
                            "SA" -> equipoJugador.text = "San Antonio Spurs  - OESTE"
                        }

                        do
                        {
                            if( playerStats[i2].PlayerID == playerID.text.toString().toInt() )
                            {
                                encontrado2 = true
                                puntosJugador.text = playerStats[i2].Points.toString()
                                rebotesJugador.text = playerStats[i2].Rebounds.toString()
                                asistenciasJugador.text = playerStats[i2].Assists.toString()
                                robosJugador.text = playerStats[i2].Steals.toString()
                                perdidasJugador.text = playerStats[i2].Turnovers.toString()
                                minutosJugador.text = playerStats[i2].Minutes.toString()
                                tirosLibresJugador.text = playerStats[i2].FreeThrowsPercentage.toString() + "%"
                                doblesJugador.text = playerStats[i2].TwoPointersPercentage.toString() + "%"
                                triplesJugador.text = playerStats[i2].ThreePointersPercentage.toString() + "%"
                            }
                            else
                            {
                                i2++
                            }
                        }
                        while ( encontrado2 == false && i2 < playerStats.size )

                    }
                    else
                    {
                        i++
                    }
                }
                while ( encontrado == false && i < players.size )

                if ( encontrado == false )
                {
                    datosJugador.isVisible = false
                    noEncontrado.isVisible = true
                }
            }
        }
    }
}