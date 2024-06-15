package com.example.tfg

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.tfg.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import android.content.ContentValues.TAG
import android.content.Intent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog

class ViewUser : AppCompatActivity() {

    lateinit var tv_intentos : TextView
    lateinit var tv_aciertos : TextView
    lateinit var tv_porcentaje : TextView
    lateinit var et_nuevaPasswd : EditText
    lateinit var btn_cambiarPasswd : Button
    lateinit var btn_eliminarCuenta : Button
    lateinit var cl_recordatorio : ConstraintLayout
    lateinit var btn_cerrarRecordatorio : Button
    lateinit var email_User : String

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_user)

        tv_intentos = findViewById(R.id.tv_intentos)
        tv_aciertos = findViewById(R.id.tv_aciertos)
        tv_porcentaje = findViewById(R.id.tv_porcentaje)
        et_nuevaPasswd = findViewById(R.id.et_nuevapass)
        btn_cambiarPasswd = findViewById(R.id.btn_cambiarPasswd)
        btn_eliminarCuenta = findViewById(R.id.btn_deleteacc)
        cl_recordatorio = findViewById(R.id.cl_recordatorio)
        btn_cerrarRecordatorio = findViewById(R.id.btn_cerrarRecordatorio)

        cl_recordatorio.isVisible = false

        et_nuevaPasswd.setOnClickListener {
            cl_recordatorio.isVisible = true
        }

        btn_cerrarRecordatorio.setOnClickListener {
            cl_recordatorio.isVisible = false
        }

        btn_cambiarPasswd.setOnClickListener {
            val user = Firebase.auth.currentUser
            val newPassword = et_nuevaPasswd.text.toString()
            user?.let {
                for (profile in it.providerData) {
                    email_User = profile.uid
                }
            }
            val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d).{6,}$")

            if ( regex.matches( et_nuevaPasswd.text.toString() ) )
            {
                user!!.updatePassword( newPassword ).addOnCompleteListener { task ->
                        if (task.isSuccessful)
                        {
                            Log.d(TAG, "User password updated.")
                            Firebase.auth.sendPasswordResetEmail( email_User ).addOnCompleteListener { task ->
                                    if (task.isSuccessful)
                                    {
                                        Log.d(TAG, "Email sent.")
                                        Toast.makeText(this@ViewUser,"Contraseña actualizada",Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }
                        else
                        {
                            Toast.makeText(this@ViewUser,"Error al actualizar la contraseña",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else
            {
                Toast.makeText(this@ViewUser,"La contraseña no cumple las reglas", Toast.LENGTH_SHORT).show()
            }
        }

        btn_eliminarCuenta.setOnClickListener {
            val user = Firebase.auth.currentUser!!
            val dialogView = LayoutInflater.from(this).inflate(R.drawable.custom_dialog_layout, null)
            val alertDialogBuilder = AlertDialog.Builder(this).setView(dialogView)

            val alertDialog = alertDialogBuilder.create()

            dialogView.findViewById<Button>(R.id.buttonCancel).setOnClickListener {
                Toast.makeText(applicationContext, "Cuenta no eliminada", Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()
            }

            dialogView.findViewById<Button>(R.id.buttonAccept).setOnClickListener {
                user.delete().addOnCompleteListener { task ->
                        if (task.isSuccessful)
                        {
                            Log.d(TAG, "User account deleted.")
                            Toast.makeText(applicationContext, "Cuenta eliminada con exito", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, Registro::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(applicationContext, "Error al eliminar cuenta", Toast.LENGTH_SHORT).show()
                        }
                    }
                alertDialog.dismiss()
            }

            alertDialog.show()
        }

    }
}