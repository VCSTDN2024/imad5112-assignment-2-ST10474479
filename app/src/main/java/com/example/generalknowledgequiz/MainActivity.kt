package com.example.generalknowledgequiz

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

    val Welcome = findViewById<TextView>(R.id.Welcome)
    val Descriptionofgame = findViewById<TextView>(R.id.Descriptionofgame)
    val Play_button = findViewById<Button>(R.id.Play_button)
    val Exit_button = findViewById<Button>(R.id.Exit_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Welcome.text = "Welcome To Tech Knowledge Quiz"
        Descriptionofgame.text = "Play to test your knowledge with these fun flashcards"

        Play_button.setOnClickListener {

            val intent = Intent(this, Quiz::class.java)
            startActivity(intent)
        }

        Exit_button.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }

}