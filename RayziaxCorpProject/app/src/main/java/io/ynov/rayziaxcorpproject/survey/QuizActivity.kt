package io.ynov.rayziaxcorpproject.survey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.ynov.rayziaxcorpproject.databinding.QuizLayoutBinding


class QuizActivity : AppCompatActivity(){
    private lateinit var binding: QuizLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = QuizLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val question1 :DataQuiz = DataQuiz("Hello question ?",1,"Hello","Hola","Bonjour","PD",2)
        binding.quizQuestion.text = question1.quote
        binding.quizAnswerOne.text = question1.option1
        binding.quizAnswerTwo.text = question1.option2
        binding.quizAnswerThree.text = question1.option3
        binding.quizAnswerFour.text = question1.option4
    }

}