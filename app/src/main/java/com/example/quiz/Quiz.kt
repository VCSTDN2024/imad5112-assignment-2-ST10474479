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
    private lateinit var questionTextView: TextView
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var feedbackTextView: TextView

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

        questionTextView = findViewById(R.id.Question_textView)
        trueBtn = findViewById(R.id.true_btn)
        falseBtn = findViewById(R.id.false_btn)
        nextBtn = findViewById(R.id.next_btn)
        feedbackTextView = findViewById(R.id.Feedback_textView)

        displayQuestion()

        trueBtn.setOnClickListener { checkAnswer(true) }
        falseBtn.setOnClickListener { checkAnswer(false) }

        nextBtn.setOnClickListener {
            currentQuestionIndex ++
            if (currentQuestionIndex < questions.size){
                displayQuestion()
                feedbackTextView.text = ""
                trueBtn.isEnabled = true
                falseBtn.isEnabled = true
            } else  {
                val intent = Intent(this, Score::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        }
        nextBtn.isEnabled = false
    }
    private fun displayQuestion(){
        questionTextView.text = questions[currentQuestionIndex]

    }
    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = answers[currentQuestionIndex]

        if (userAnswer == correctAnswer){
            feedbackTextView.text = "Correct"
            feedbackTextView.setTextColor(Color.GREEN)
            score ++
        } else {
            feedbackTextView.text = "Incorrect"
            feedbackTextView.setTextColor(Color.RED)
        }
        trueBtn.isEnabled = false
        falseBtn.isEnabled = false
        nextBtn.isEnabled = true
    }
}