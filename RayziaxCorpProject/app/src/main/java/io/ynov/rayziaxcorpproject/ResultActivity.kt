package io.ynov.rayziaxcorpproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Hadrien PERIER
 * This class is used to manage the result page from the result_main.xml
 * It modifies the view to display the user name and the score
 */
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val userName = findViewById<TextView>(R.id.result_username)
        val userScore = findViewById<TextView>(R.id.result_userscore)
        val btnFinish = findViewById<Button>(R.id.finishBtn)

        val name = intent.getStringExtra(DataObj.USER_NAME)
        userName.text = name

        val totalQuestions = intent.getIntExtra(DataObj.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(DataObj.CORRECT_ANSWERS, 0)

        userScore.text = this.getString(R.string.score_message) + " $correctAnswers / $totalQuestions"

        btnFinish.setOnClickListener {
            startActivity(Intent(this@ResultActivity, WelcomeActivity::class.java))
        }
    }
}