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

class Score : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        //retrieves UI elements for displaying score, feedback, and buttons
        val Score_textView = findViewById<TextView>(R.id.Score_textView)
        val Feedback_textView = findViewById<TextView>(R.id.Feedback_textView)
        val Review_btn = findViewById<Button>(R.id.Review_btn)
        val Exit_btn = findViewById<Button>(R.id.Exit_btn)

        //receives the score from Quiz activity and displays it
        val score = intent.getIntExtra("score", 0)
        Score_textView.text = "Your score: $score/6"

        //sets feedback based on score
        val feedback = if (score >= 3){
            "Wow amazing job!"
        } else{
            "Oopsie dont worry keep practicing!"
        }
        Feedback_textView.text = feedback

        //Navigates to reviewscore, passing the questions and answers
        Review_btn.setOnClickListener {

            val intent = Intent(this, ReviewScore ::class.java)
            intent.putExtra("questions", Quiz.questions)
            intent.putExtra("answers", Quiz.answers)
            startActivity(intent)
        }
        //closes the app
        Exit_btn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }

    }
}
