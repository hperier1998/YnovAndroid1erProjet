package io.ynov.rayziaxcorpproject.survey

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import io.ynov.rayziaxcorpproject.R
import io.ynov.rayziaxcorpproject.databinding.QuizLayoutBinding


class QuizActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var mQuizBinding: QuizLayoutBinding
    private lateinit var mDatasQuizes: ArrayList<DataQuiz>
    private lateinit var mCurrentQuestion:DataQuiz
    private var mCurrentPositionQuestion:Int = 0
    private var mCorrectAnswers:Int = 0
    private var mSelectedOptionPosition:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mQuizBinding = QuizLayoutBinding.inflate(layoutInflater)
        mDatasQuizes = DataObject.getQuestions(this);
        setContentView(mQuizBinding.root)
        setQuestion()
        mQuizBinding.quizAnswerOne.setOnClickListener(this)
        mQuizBinding.quizAnswerTwo.setOnClickListener(this)
        mQuizBinding.quizAnswerThree.setOnClickListener(this)
        mQuizBinding.quizAnswerFour.setOnClickListener(this)
        mQuizBinding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.quiz_answer_one -> {

                selectedOptionView(mQuizBinding.quizAnswerOne, 1)
            }

            R.id.quiz_answer_two -> {

                selectedOptionView(mQuizBinding.quizAnswerTwo, 2)
            }

            R.id.quiz_answer_three -> {

                selectedOptionView(mQuizBinding.quizAnswerThree, 3)
            }

            R.id.quiz_answer_four -> {

                selectedOptionView(mQuizBinding.quizAnswerFour, 4)
            }

            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPositionQuestion++

                    when {

                        mCurrentPositionQuestion < mDatasQuizes!!.size -> {

                            setQuestion()
                        }
                        else -> {
                            startActivity(Intent(this@QuizActivity, ResultActivity::class.java))
                            finish()
                        }
                    }
                } else {
                    val question = mDatasQuizes?.get(mCurrentPositionQuestion)

                    // Vérifie si la réponse est fausse
                    if (question!!.correctResponse != mSelectedOptionPosition) { // si la question est fausse alors
                        // Passe en rouge si la réponse est fausse
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else {
                        // si non on incrémente de 1 le nombre de réponse correcte
                        mCorrectAnswers++
                    }

                    // Donne la couleur verte si c'est une réponse juste
                    answerView(question.correctResponse, R.drawable.correct_option_border_bg)

                    // Si on attend le dernier index du tableau alors ...
                    if (mCurrentPositionQuestion == (mDatasQuizes!!.size - 1)) {
                        mQuizBinding.btnSubmit.text = this.getString(R.string.finish_desc)
                    } else {
                        mQuizBinding.btnSubmit.text = this.getString(R.string.nextques_desc)
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition  = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizActivity,
            R.drawable.selected_option_border_bg
        )
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        options.add(0, mQuizBinding.quizAnswerOne)
        options.add(1, mQuizBinding.quizAnswerTwo)
        options.add(2, mQuizBinding.quizAnswerThree)
        options.add(3, mQuizBinding.quizAnswerFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizActivity,
                R.drawable.default_option_border_bg
            )
        }
    }


    private fun setQuestion(){
        mCurrentQuestion = mDatasQuizes!!.get(mCurrentPositionQuestion)
        setComponents()
        defaultOptionsView()
    }

    private fun setComponents(){
        mQuizBinding.btnSubmit.text = this.getString(R.string.text_btn_validate)
        mQuizBinding.quizQuestion.text = mCurrentQuestion.quote
        mQuizBinding.quizImage.setImageResource(mCurrentQuestion.image)

        // Affiche les différents choix de réponse à la question
        mQuizBinding.quizAnswerOne.text = mCurrentQuestion.option1
        mQuizBinding.quizAnswerTwo.text = mCurrentQuestion.option2
        mQuizBinding.quizAnswerThree.text = mCurrentQuestion.option3
        mQuizBinding.quizAnswerFour.text = mCurrentQuestion.option4

        //Réinitialise la position de la scrollbar
        mQuizBinding.scrollView.scrollTo(0,0)
    }

    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                mQuizBinding.quizAnswerOne.background = ContextCompat.getDrawable(
                    this@QuizActivity,
                    drawableView
                )
            }
            2 -> {
                mQuizBinding.quizAnswerTwo.background = ContextCompat.getDrawable(
                    this@QuizActivity,
                    drawableView
                )
            }
            3 -> {
                mQuizBinding.quizAnswerThree.background = ContextCompat.getDrawable(
                    this@QuizActivity,
                    drawableView
                )
            }
            4 -> {
                mQuizBinding.quizAnswerFour.background = ContextCompat.getDrawable(
                    this@QuizActivity,
                    drawableView
                )
            }
        }
    }
}