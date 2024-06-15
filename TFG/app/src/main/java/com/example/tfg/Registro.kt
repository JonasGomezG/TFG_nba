package com.example.tfg

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference


class Registro : AppCompatActivity() {

    lateinit var newEmail : EditText
    lateinit var newPasswd : EditText
    lateinit var crearAcc : Button
    lateinit var irAInicio : Button
    lateinit var reglasPass : Button
    lateinit var reglas : ConstraintLayout
    lateinit var cerrarReglas : Button
    lateinit var mAuth : FirebaseAuth

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val mDatabase = database.getReference("usuarios")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        newEmail = findViewById(R.id.et_newEmail)
        newPasswd = findViewById(R.id.et_newPasswd)
        crearAcc = findViewById(R.id.btn_crearCuenta)
        irAInicio = findViewById(R.id.btn_IrAInicio)
        reglasPass = findViewById(R.id.btn_reglasPass)
        reglas = findViewById(R.id.tv_reglas)
        cerrarReglas = findViewById(R.id.btn_cerrarReglas)

        reglas.isVisible = false
        mAuth = FirebaseAuth.getInstance()
        
        reglasPass.setOnClickListener {
            reglas.isVisible = true
        }

        cerrarReglas.setOnClickListener {
            reglas.isVisible = false
        }

        irAInicio.setOnClickListener {
            inicioView()
        }


        crearAcc.setOnClickListener {
            val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d).{6,}$")
            val regexEmail = Regex("^[a-z]+@[A-Za-z0-9]+\\.com\$")

            if ( !regex.matches( newPasswd.text.toString() ) ) {
                Toast.makeText(this@Registro,"La contraseña no cumple las reglas",Toast.LENGTH_SHORT).show()
            } else if ( !regexEmail.matches( newEmail.text.toString() ) ) {
                Toast.makeText(this@Registro,"El correo tiene un formato no valido, debe acabar en '@gmail.com'",Toast.LENGTH_SHORT).show()
            } else {
                createAccount(newEmail.text.toString(), newPasswd.text.toString())
            }
        }
    }

    private fun createAccount(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful()) {
                Log.d(TAG, "createUserWithEmail:success")

                val correo: String = newEmail.text.toString().trim()
                val formattedEmail = correo.replace(".", ",")
                val newUsuario = Usuario(formattedEmail, correo, 0, 0)

                mDatabase.child(formattedEmail).setValue(newUsuario, object : DatabaseReference.CompletionListener {
                    override fun onComplete(error: DatabaseError?, ref: DatabaseReference) {
                        if (error == null) {
                            Toast.makeText(this@Registro, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                            inicioView()
                        } else {
                            Toast.makeText(this@Registro, "No se ha creado en la colección", Toast.LENGTH_SHORT).show()
                            println("---------------------------------------")
                            println(error.toString())
                            println("---------------------------------------")
                        }
                    }
                })
            } else {
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(this@Registro, "Error al crear el usuario.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inicioView (){
        val intent = Intent(this, InicioSesion::class.java)
        startActivity(intent)
    }

}
