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

        //Finds the button in the layout with the ID and assigns it to the variable
        val welcome = findViewById<TextView>(R.id.Welcome)
        val descriptionOfGame = findViewById<TextView>(R.id.Descriptionofgame)
        val play_Button = findViewById<Button>(R.id.Play_button)
        val exit_Button = findViewById<Button>(R.id.Exit_button)

        welcome.text = "Welcome To Tech Knowledge Quiz"
        descriptionOfGame.text = "Play to test your knowledge with these fun flashcards"

        //When tapped, the block inside executes
        play_Button.setOnClickListener {

            //Connects this screen to the next
            val intent = Intent(this, Quiz::class.java)
            startActivity(intent)
        }

        //This sets an event listener on the button, meaning it will respond when clicked
        exit_Button.setOnClickListener {
           //closes the current activity, removing it from the apps navigation stack
            finishAffinity()
            //terminates the application, the 0 indicates a normal exit with no errors
            exitProcess(0)
        }
    }

}