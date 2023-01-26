package io.ynov.rayziaxcorpproject

import android.content.Context

/**
 * Hadrien PERIER
 * This object is used to store all the values of the questions (question, image, answer options, and correct answer).
 * It bases itself on the strings.xml content.
 */
object DataObj {
    const val USER_NAME: String = "user_name"

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    /**
     * This function creates all the questions and adds them to a list of the data class QuestionFormation
     *
     */
    fun getQuestions(context: Context): ArrayList<QuestionFormat> {
        val questionsList = ArrayList<QuestionFormat>()

        // Question 1
        val que1 = QuestionFormat(
            1, context.getString(R.string.question_one),
            R.drawable.actress,
            context.getString(R.string.question_one_answer_one), context.getString(R.string.question_one_answer_two),
            context.getString(R.string.question_one_answer_three), context.getString(R.string.question_one_answer_four),
            1
        )

        questionsList.add(que1)

        // Question 2
        val que2 = QuestionFormat(
            2, context.getString(R.string.question_two),
            R.drawable.squares,
            context.getString(R.string.question_two_answer_one), context.getString(R.string.question_two_answer_two),
            context.getString(R.string.question_two_answer_three), context.getString(R.string.question_two_answer_four),
            3
        )

        questionsList.add(que2)

        // Question 3
        val que3 = QuestionFormat(
            3, context.getString(R.string.question_three),
            R.drawable.egypt,
            context.getString(R.string.question_three_answer_one), context.getString(R.string.question_three_answer_two),
            context.getString(R.string.question_three_answer_three), context.getString(R.string.question_three_answer_four),
            2
        )

        questionsList.add(que3)

        // Question 4
        val que4 = QuestionFormat(
            4, context.getString(R.string.question_four),
            R.drawable.flag_fiji,
            context.getString(R.string.question_four_answer_one), context.getString(R.string.question_four_answer_two),
            context.getString(R.string.question_four_answer_three), context.getString(R.string.question_four_answer_four),
            2
        )

        questionsList.add(que4)

        // Question 5
        val que5 = QuestionFormat(
            5, context.getString(R.string.question_five),
            R.drawable.thumbsup,
            context.getString(R.string.question_five_answer_one), context.getString(R.string.question_five_answer_two),
            context.getString(R.string.question_five_answer_three), context.getString(R.string.question_five_answer_four),
            1
        )

        questionsList.add(que5)

        // Question 6
        val que6 = QuestionFormat(
            6, context.getString(R.string.question_six),
            R.drawable.grading,
            context.getString(R.string.question_six_answer_one), context.getString(R.string.question_six_answer_two),
            context.getString(R.string.question_six_answer_three), context.getString(R.string.question_six_answer_four),
            1
        )

        questionsList.add(que6)

        return questionsList
    }
}