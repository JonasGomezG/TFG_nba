package com.example.tfg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Ranking : AppCompatActivity() {

    lateinit var tv_user1 : TextView
    lateinit var tv_intentos1 : TextView
    lateinit var tv_aciertos1 : TextView

    lateinit var tv_user2 : TextView
    lateinit var tv_intentos2 : TextView
    lateinit var tv_aciertos2 : TextView

    lateinit var tv_user3 : TextView
    lateinit var tv_intentos3 : TextView
    lateinit var tv_aciertos3 : TextView

    lateinit var tv_user4 : TextView
    lateinit var tv_intentos4 : TextView
    lateinit var tv_aciertos4 : TextView

    lateinit var tv_user5 : TextView
    lateinit var tv_intentos5 : TextView
    lateinit var tv_aciertos5 : TextView

    lateinit var tv_user6 : TextView
    lateinit var tv_intentos6 : TextView
    lateinit var tv_aciertos6 : TextView

    lateinit var tv_user7 : TextView
    lateinit var tv_intentos7 : TextView
    lateinit var tv_aciertos7 : TextView

    lateinit var tv_user8 : TextView
    lateinit var tv_intentos8 : TextView
    lateinit var tv_aciertos8 : TextView

    lateinit var tv_user9 : TextView
    lateinit var tv_intentos9 : TextView
    lateinit var tv_aciertos9 : TextView

    lateinit var tv_user10 : TextView
    lateinit var tv_intentos10 : TextView
    lateinit var tv_aciertos10 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        tv_user1 = findViewById(R.id.tv_1user)
        tv_intentos1 = findViewById(R.id.tv_1intentos)
        tv_aciertos1 = findViewById(R.id.tv_1aciertos)

        tv_user2 = findViewById(R.id.tv_2user)
        tv_intentos2 = findViewById(R.id.tv_2intentos)
        tv_aciertos2 = findViewById(R.id.tv_2aciertos)

        tv_user3 = findViewById(R.id.tv_3user)
        tv_intentos3 = findViewById(R.id.tv_3intentos)
        tv_aciertos3 = findViewById(R.id.tv_3aciertos)

        tv_user4 = findViewById(R.id.tv_4user)
        tv_intentos4 = findViewById(R.id.tv_4intentos)
        tv_aciertos4 = findViewById(R.id.tv_4aciertos)

        tv_user5 = findViewById(R.id.tv_5user)
        tv_intentos5 = findViewById(R.id.tv_5intentos)
        tv_aciertos5 = findViewById(R.id.tv_5aciertos)

        tv_user6 = findViewById(R.id.tv_6user)
        tv_intentos6 = findViewById(R.id.tv_6intentos)
        tv_aciertos6 = findViewById(R.id.tv_6aciertos)

        tv_user7 = findViewById(R.id.tv_7user)
        tv_intentos7 = findViewById(R.id.tv_7intentos)
        tv_aciertos7 = findViewById(R.id.tv_7aciertos)

        tv_user8 = findViewById(R.id.tv_8user)
        tv_intentos8 = findViewById(R.id.tv_8intentos)
        tv_aciertos8 = findViewById(R.id.tv_8aciertos)

        tv_user9 = findViewById(R.id.tv_9user)
        tv_intentos9 = findViewById(R.id.tv_9intentos)
        tv_aciertos9 = findViewById(R.id.tv_9aciertos)

        tv_user10 = findViewById(R.id.tv_10user)
        tv_intentos10 = findViewById(R.id.tv_10intentos)
        tv_aciertos10 = findViewById(R.id.tv_10aciertos)

    }
}