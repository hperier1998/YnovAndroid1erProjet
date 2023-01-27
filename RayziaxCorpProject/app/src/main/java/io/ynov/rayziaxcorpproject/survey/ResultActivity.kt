package io.ynov.rayziaxcorpproject.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import io.ynov.rayziaxcorpproject.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val maxAnswer = intent.getIntExtra(DataObject.MAX_ANSWERS, 0)
        val correctAnswers = intent.getIntExtra(DataObject.CORRECT_ANSWERS, 0)

        findViewById<TextView>(R.id.txtShowScore).text = " $correctAnswers / $maxAnswer"
        findViewById<Button>(R.id.btnQuit).setOnClickListener{
            finish()
        }
    }
}