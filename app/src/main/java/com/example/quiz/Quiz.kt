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
    //private lateinit declares variable that it will be initialized later on. These UI elements retrieved using the findViewById() function
    private lateinit var questionTextView: TextView
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var feedbackTextView: TextView

    //Defines a static object for storing quiz questions and their correct answers
    //Each question has a corresponding true or false value in the answers array
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
    //tracks the current question
    //eeps count of correct answers
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        //retrieves UI components from the XML layout
        questionTextView = findViewById(R.id.Question_textView)
        trueBtn = findViewById(R.id.true_btn)
        falseBtn = findViewById(R.id.false_btn)
        nextBtn = findViewById(R.id.next_btn)
        feedbackTextView = findViewById(R.id.Feedback_textView)

        //calls displayquestion() to show the first question
        displayQuestion()

        //listens to the button clicks and calls checkanswer with true or false
        trueBtn.setOnClickListener { checkAnswer(true) }
        falseBtn.setOnClickListener { checkAnswer(false) }

        //moves to the next question if there are more questions
        //Resets feedback and re-enables buttons
        //if not more questions moves to the final score
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
        //prevents user from skipping a questions before answering
        nextBtn.isEnabled = false
    }
    //updates the displayed question based on the current question
    private fun displayQuestion(){
        questionTextView.text = questions[currentQuestionIndex]

    }
    //checks if the users answer matches the correct one
    //updates feedback with color indication
    //disables true button and false button after answering
    //enables next button to move forward
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