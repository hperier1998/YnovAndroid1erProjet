package io.ynov.rayziaxcorpproject.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.ynov.rayziaxcorpproject.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        findViewById<Button>(R.id.btnQuit).setOnClickListener{
            finish()
        }
    }
}