package com.example.alberto.jogodavelhaalberto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun jogar(view: View){
        val jogador1:String=editTextJogador1.text.toString()
        val jogador2:String=editTextJogador2.text.toString()

        val intentCall: Intent =Intent(this,MainTela2::class.java)
        val parametros:Bundle = Bundle()
        parametros.putString("jogador1",jogador1)
        parametros.putString("jogador2",jogador2)
        intentCall.putExtras(parametros)
        startActivity(intentCall)

    }
}