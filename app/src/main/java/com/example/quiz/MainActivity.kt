package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val welcome = findViewById<TextView>(R.id.Welcome)
        val descriptionOfGame = findViewById<TextView>(R.id.Descriptionofgame)
        val play_Button = findViewById<Button>(R.id.Play_button)
        val exit_Button = findViewById<Button>(R.id.Exit_button)

        welcome.text = "Welcome To Tech Knowledge Quiz"
        descriptionOfGame.text = "Play to test your knowledge with these fun flashcards"

        play_Button.setOnClickListener {

            val intent = Intent(this, Quiz::class.java)
            startActivity(intent)
        }

        exit_Button.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }

}