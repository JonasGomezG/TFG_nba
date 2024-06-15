package com.example.tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class InicioSesion : AppCompatActivity() {

    lateinit var btn_IniciarSesion : Button
    lateinit var btn_IrARegistro : Button
    lateinit var et_user : EditText
    lateinit var et_pass : EditText
    lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        btn_IniciarSesion = findViewById(R.id.btn_IniciarSesion)
        btn_IrARegistro = findViewById(R.id.btn_IrARegistro)
        et_user = findViewById(R.id.et_user)
        et_pass = findViewById(R.id.et_pass)

        mAuth = FirebaseAuth.getInstance()


        btn_IrARegistro.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        btn_IniciarSesion.setOnClickListener {
            signInWithEmailAndPassword( et_user.text.toString() , et_pass.text.toString() )
        }
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        if ( et_user.text.toString() == "" || et_pass.text.toString() == "" ) {
            Toast.makeText(this@InicioSesion,"Los campos no pueden estar vacios",Toast.LENGTH_SHORT).show()
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@InicioSesion,"Credenciales correctas",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Opciones::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@InicioSesion,"Email o usuario incorrectos",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}