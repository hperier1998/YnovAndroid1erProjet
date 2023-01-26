package io.ynov.rayziaxcorpproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import io.ynov.rayziaxcorpproject.survey.QuizActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initActivity()
    }

    override fun onStart() {
        super.onStart()
        Log.i(MainActivity::class.simpleName, "CURRENTLY IN onStart()")
    }

    /**
     * Methode initiate component from layout
     */
    private fun initActivity()
    {
        findViewById<Button>(R.id.btnPlay).setOnClickListener{
            val intent = Intent(this@MainActivity,QuizActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}