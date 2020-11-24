package com.example.ifarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_tela_email.*
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select

class TelaEmail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_email)

        val emailAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        list_view_email.adapter = emailAdapter

        emailAdapter.add("Item 1")
        emailAdapter.add("Item 2")

        database.use {
            select("email").exec {
                val parser = rowParser() {id: Int, email: String ->
                    Email(id,email)
                }

                var listaEmails = parseList(parser)

                emailAdapter.clear()

                val emailsTexto = listaEmails.map { it.email }
                emailAdapter.addAll(emailsTexto)
            }
        }

        list_view_email.setOnItemClickListener { parent, view, position, id ->
            Log.d("debug", "${position}, ${id}")
            //val intent = Intent(this, MapsActivity::class.java)
            //startActivity(intent)
            val intent = Intent(this, CarregaFoto::class.java)
            startActivity(intent)
        }
    }
}