package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Quiz : AppCompatActivity() {
    private lateinit var Question_textView: TextView
    private lateinit var true_btn: Button
    private lateinit var false_btn: Button
    private lateinit var next_btn: Button
    private lateinit var Feedback_textView: Button

    companion object{
        val questions = arrayOf(
            "Wi-Fi stands for Wide Fidelity",
            "Google is known for Iphone",
            "USB Stands for Universal Serial Bus",
            "Alexa is the virtual assist amazon created",
            "The primary function of a hard drive is to display photos",
            "The term download means to transfer files from the internet to your device"
        )
        val answers = booleanArrayOf(false, false, true, true, false, true)
    }
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        Question_textView = findViewById(R.id.Question_textView)
        true_btn = findViewById(R.id.true_btn)
        false_btn = findViewById(R.id.false_btn)
        next_btn = findViewById(R.id.next_btn)
        Feedback_textView = findViewById(R.id.Feedback_textView)

        displayQuestion()

        true_btn.setOnClickListener { checkAnswer(true) }
        false_btn.setOnClickListener { checkAnswer(false) }

        next_btn.setOnClickListener {
            currentQuestionIndex ++
            if (currentQuestionIndex < questions.size){
                displayQuestion()
                Feedback_textView.text = ""
                true_btn.isEnabled = true
                false_btn.isEnabled = true
            } else  {
                val intent = Intent(this, score::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        }
        next_btn.isEnabled = false
    }
    private fun displayQuestion(){
        Question_textView.text = questions[currentQuestionIndex]

    }
    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = answers[currentQuestionIndex]

        if (userAnswer == correctAnswer){
            Feedback_textView.text = "Correct"
            Feedback_textView.setTextColor(Color.GREEN)
            score ++
        } else {
            Feedback_textView.text = "Incorrect"
            Feedback_textView.setTextColor(Color.RED)
        }
        true_btn.isEnabled = false
        false_btn.isEnabled = false
        next_btn.isEnabled = true
    }
}