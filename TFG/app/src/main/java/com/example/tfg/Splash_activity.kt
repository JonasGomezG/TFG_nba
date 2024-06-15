package com.example.tfg

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide

class Splash_activity : AppCompatActivity() {

    var SPLASH_TIME_OUT : Long = 0
    lateinit var gif : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        gif = findViewById(R.id.imagenGif)

        val numeroAleatorio = (1..5).random()

        when (numeroAleatorio)
        {
            1 -> Glide.with( this ).load( R.drawable.lebron ).into( gif )
            2 -> Glide.with( this ).load( R.drawable.wemby ).into( gif )
            3 -> Glide.with( this ).load( R.drawable.lebronking ).into( gif )
            4 -> Glide.with( this ).load( R.drawable.vince ).into( gif )
            5 -> Glide.with( this ).load( R.drawable.antetokounmpo ).into( gif )
        }

        Handler().postDelayed({
            val intent = Intent(this@Splash_activity, InicioSesion::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)

    }
}