package io.ynov.rayziaxcorpproject

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import io.ynov.rayziaxcorpproject.databinding.FragmentQuizBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment(), View.OnClickListener {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var quizMainBinding: FragmentQuizBinding

    // Declares and defines the default position in the quiz as 1, meaning the user is at the start (first question)
    private var mCurrentPosition: Int = 1

    private var userName: String? = null

    // Declares the list of the questions in the Quiz
    private var mQuestionsList: ArrayList<QuestionFormat>? = null

    // Declares which answer is selected (option) by the user
    private var mSelectedOptionPosition: Int = 0

    // Declares how many answers are correct
    private var mCorrectAnswers: Int = 0

    // Declares how many questions there is
    private var mTotalAnswers:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the list of the questions from the DataObj
        mQuestionsList = context?.let { DataObj.getQuestions(it) }

        // Retrieve the total number of questions from the DataObj
        mTotalAnswers = context?.let { DataObj.TOTAL_QUESTIONS }

        // Retrieves the userName from the activity set from WelcomeActivity, into the MainActivity
        userName = activity?.intent?.getStringExtra(DataObj.USER_NAME)
    }


    /**
     * The function onCreate() is called when it's time to create the Fragment's view hierarchy
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        quizMainBinding = FragmentQuizBinding.inflate(inflater,container,false)

        // Calls the function to set the questions based on the mQuestionsList
        setQuestion()

        // Set onClickListeners for the answer options and submit button
        quizMainBinding.quizAnswerOne.setOnClickListener(this)
        quizMainBinding.quizAnswerTwo.setOnClickListener(this)
        quizMainBinding.quizAnswerThree.setOnClickListener(this)
        quizMainBinding.quizAnswerFour.setOnClickListener(this)
        quizMainBinding.btnSubmit.setOnClickListener(this)

        // Return the root view of the inflated layout
        return quizMainBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

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
                            // Sets the values in a bundle to add them to the other fragment
                            val bundle = Bundle()
                            bundle.putString(DataObj.USER_NAME, userName)
                            bundle.putString(DataObj.CORRECT_ANSWERS, mCorrectAnswers.toString())
                            bundle.putString(DataObj.TOTAL_QUESTIONS, mTotalAnswers)
                            val endQuizFragment = EndQuizFragment()
                            endQuizFragment.arguments = bundle

                            val fragmentManager = activity?.supportFragmentManager
                            val fragmentTransaction = fragmentManager?.beginTransaction()
                            fragmentTransaction?.replace(R.id.main_frameLayout, endQuizFragment)
                            fragmentTransaction?.commit()
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
        tv.background = context?.let {
            ContextCompat.getDrawable(
                it,
                R.drawable.selected_option_border_bg
            )
        }
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
            option.background = context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.default_option_border_bg
                )
            }
        }

    }

    /**
     * The function defaultOptionsView() is used to show if the answer was right or wrong after SUBMIT
     *
     */
    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                quizMainBinding.quizAnswerOne.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        drawableView
                    )
                }
            }
            2 -> {
                quizMainBinding.quizAnswerTwo.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        drawableView
                    )
                }
            }
            3 -> {
                quizMainBinding.quizAnswerThree.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        drawableView
                    )
                }
            }
            4 -> {
                quizMainBinding.quizAnswerFour.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        drawableView
                    )
                }
            }
        }
    }
}