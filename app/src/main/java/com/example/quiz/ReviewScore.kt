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

class ReviewScore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review_score)

        //Finds UI components for score review and for restart/exit
        val ReviewScore_textView = findViewById<TextView>(R.id.ReviewScore_textView)
        val Restart_btn = findViewById<Button>(R.id.Restart_btn)
        val Exit_btn = findViewById<Button>(R.id.Exit_btn)

        //retrives quiz questions and answers passed via Intent
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        //loops through questions & answers formats them into reviewText
        //if data is missing, display an error message
        val reviewText = StringBuilder()
        if (questions != null && answers != null && questions.size == answers.size) {
            for (i in questions.indices) {
                reviewText.append("${i + 1}. ${questions[i]} \n")
                reviewText.append("   Answer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            ReviewScore_textView.text = reviewText.toString()
        } else {
            ReviewScore_textView.text = "Failed to retrieve review data"
        }
        //when clicked, navigates back to Quiz activity, restarting the quiz
        Restart_btn.setOnClickListener {
            startActivity(Intent(this, Quiz::class.java))
        }
            //closes all the activites and exits the app
        Exit_btn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}
