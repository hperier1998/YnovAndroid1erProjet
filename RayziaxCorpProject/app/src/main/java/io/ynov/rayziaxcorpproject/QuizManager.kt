package io.ynov.rayziaxcorpproject

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.ynov.rayziaxcorpproject.databinding.QuizMainBinding

/**
 * Hadrien PERIER
 * This class is used for when the user launches the quiz after entering a name.
 * It manages all the quiz aspect, with the display of the questions, image, and answer.
 */
class QuizManager : AppCompatActivity(), View.OnClickListener {

    // Declares and defines the default position in the quiz as 1, meaning the user is at the start (first question)
    private var mCurrentPosition: Int = 1

    // Declares the list of the questions in the Quiz
    private var mQuestionsList: ArrayList<QuestionFormat>? = null

    // Declares which answer is selected (option) by the user
    private var mSelectedOptionPosition: Int = 0

    // Declares which answer is correct
    private var mCorrectAnswers: Int = 0

    // Declares the user's name entered in MainActivity
    private var mUserName: String? = null

    // Creates a binding to quiz_main.xml to be able to access it's View elements
    private lateinit var quizMainBinding: QuizMainBinding

    /**
     * The function onCreate() is called when the activity is created.
     * Uses savedInstanceState to restore the previous state of the activity.
     * If it is null, the activity is started fresh.
     * Does not return any values.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Call the parent constructor
        super.onCreate(savedInstanceState)

        // Create an instance of the binding with inflate
        quizMainBinding = QuizMainBinding.inflate(layoutInflater)

        // Get a reference to the binding with root
        val view = quizMainBinding.root

        // Uses the reference of the binding to make it an active view on the screen
        setContentView(view)

        // Retrieve the user's name from the DataObj
        mUserName = intent.getStringExtra(DataObj.USER_NAME)

        // Retrieve the list of the questions from the DataObj
        mQuestionsList = DataObj.getQuestions(this)

        // Calls the function to set the questions based on the mQuestionsList
        setQuestion()

        quizMainBinding.quizAnswerOne.setOnClickListener(this)
        quizMainBinding.quizAnswerTwo.setOnClickListener(this)
        quizMainBinding.quizAnswerThree.setOnClickListener(this)
        quizMainBinding.quizAnswerFour.setOnClickListener(this)

        quizMainBinding.btnSubmit.setOnClickListener(this)
    }

    /**
     * The function onClick() is used to for the onclick behavior when selecting an answer
     * and submitting it
     *
     */
    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.quiz_answer_one -> {

                selectedOptionView(quizMainBinding.quizAnswerOne, 1)
            }

            R.id.quiz_answer_two -> {

                selectedOptionView(quizMainBinding.quizAnswerTwo, 2)
            }

            R.id.quiz_answer_three -> {

                selectedOptionView(quizMainBinding.quizAnswerThree, 3)
            }

            R.id.quiz_answer_four -> {

                selectedOptionView(quizMainBinding.quizAnswerFour, 4)
            }

            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {

                            val intent =
                                Intent(this@QuizManager, ResultManager::class.java)
                            intent.putExtra(DataObj.USER_NAME, mUserName)
                            intent.putExtra(DataObj.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(DataObj.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    // Check if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        // Draws red for the wrong answer
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else {
                        // If its correct, increase the correct answer count
                        mCorrectAnswers++
                    }

                    // Draws green for the correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    // If it's the last question, show FINISH on the button. Else go to next question
                    if (mCurrentPosition == mQuestionsList!!.size) {
                        quizMainBinding.btnSubmit.text = this.getString(R.string.finish_desc)
                    } else {
                        quizMainBinding.btnSubmit.text = this.getString(R.string.nextques_desc)
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    /**
     * The function setQuestion() is used to get and set the questions' values in
     * the UI components (View from the .xml file).
     *
     */
    private fun setQuestion() {

        // Gets the question from the list with the help of current position.
        val question = mQuestionsList!!.get(mCurrentPosition - 1)

        // Calls the function to set the default view when there is a change (new question loaded, answer changed etc)
        defaultOptionsView()

        quizMainBinding.btnSubmit.text = this.getString(R.string.submit_desc)

        // Sets the information for the progress bar at the top of the view.
        quizMainBinding.progressBar.progress = mCurrentPosition
        quizMainBinding.progressText.text = "$mCurrentPosition" + "/" + quizMainBinding.progressBar.max

        // Displays the question text, followed by the question image
        quizMainBinding.quizQuestion.text = question.question
        quizMainBinding.quizImage.setImageResource(question.image)

        // Displays the different answer choice for the question
        quizMainBinding.quizAnswerOne.text = question.optionOne
        quizMainBinding.quizAnswerTwo.text = question.optionTwo
        quizMainBinding.quizAnswerThree.text = question.optionThree
        quizMainBinding.quizAnswerFour.text = question.optionFour

        // Scroll to the top after every answer submission
        quizMainBinding.scrollView.scrollTo(0,0)
    }

    /**
     * The function selectedOptionView() is used to modify view values depending on the selected choice.
     * For example, the answer that was selected.
     *
     */
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizManager,
            R.drawable.selected_option_border_bg
        )
    }

    /**
     * The function defaultOptionsView() is used to set (update) the default view
     * when there is a change in the view.
     * For example, new question or change in answer selected
     *
     */
    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        options.add(0, quizMainBinding.quizAnswerOne)
        options.add(1, quizMainBinding.quizAnswerTwo)
        options.add(2, quizMainBinding.quizAnswerThree)
        options.add(3, quizMainBinding.quizAnswerFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizManager,
                R.drawable.default_option_border_bg
            )
        }

    }

    /**
     * The function defaultOptionsView() is used to show if the answer was right or wrong after SUBMIT
     *
     */
    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                quizMainBinding.quizAnswerOne.background = ContextCompat.getDrawable(
                    this@QuizManager,
                    drawableView
                )
            }
            2 -> {
                quizMainBinding.quizAnswerTwo.background = ContextCompat.getDrawable(
                    this@QuizManager,
                    drawableView
                )
            }
            3 -> {
                quizMainBinding.quizAnswerThree.background = ContextCompat.getDrawable(
                    this@QuizManager,
                    drawableView
                )
            }
            4 -> {
                quizMainBinding.quizAnswerFour.background = ContextCompat.getDrawable(
                    this@QuizManager,
                    drawableView
                )
            }
        }
    }
}