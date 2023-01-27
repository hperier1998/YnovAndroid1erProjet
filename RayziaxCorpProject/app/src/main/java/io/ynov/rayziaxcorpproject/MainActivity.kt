package io.ynov.rayziaxcorpproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Toast
import android.widget.TextView
import android.widget.Button

/**
 * Hadrien PERIER
 * This class is used for when the app is first launched (what is displayed).
 */
class MainActivity : AppCompatActivity() {

    /**
     * The function onCreate() is called when the activity is created.
     * Uses savedInstanceState to restore the previous state of the activity.
     * If it is null, the activity is started fresh.
     * Does not return any values.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        // Call the parent constructor
        super.onCreate(savedInstanceState)

        // Calls the .xml of activity_main.xml
        setContentView(R.layout.activity_main)

        // Retrieves the various values in the .xml and assign them to a variable
        val userName = findViewById<TextView>(R.id.user_name)
        val startBtn = findViewById<Button>(R.id.home_start_button)

        // This part is to hide the Android status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // Verifies if the etName (the variable used for the user name) is empty or not.
        startBtn.setOnClickListener {

            // If empty
            if (userName.text.toString().isEmpty()) {

                Toast.makeText(this@MainActivity, getString(R.string.toast_message), Toast.LENGTH_SHORT)
                    .show()
            }
            // If not empty
            else {

                // Start the QuizManager
                val intent = Intent(this@MainActivity, QuizActivity::class.java)

                intent.putExtra(DataObj.USER_NAME, userName.text.toString())

                startActivity(intent)
                finish()
            }
        }
    }
}