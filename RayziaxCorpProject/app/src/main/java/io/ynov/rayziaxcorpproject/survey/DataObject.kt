package io.ynov.rayziaxcorpproject.survey
import android.content.Context
import io.ynov.rayziaxcorpproject.R

object DataObject {

    const val CORRECT_ANSWERS : String = "total_questions"
    const val MAX_ANSWERS : String = "correct_answers"
    fun getQuestions(context:Context): ArrayList<DataQuiz>{
        val lstQuestion = ArrayList<DataQuiz>()

        val q1 = DataQuiz(
            context.getString(R.string.question_one),R.drawable.marianne,
            context.getString(R.string.question_one_answer_one),
            context.getString(R.string.question_one_answer_two),
            context.getString(R.string.question_one_answer_three),
            context.getString(R.string.question_one_answer_four),1
        )

        val q2 = DataQuiz(
            context.getString(R.string.question_two),R.drawable.louisvilegros,
            context.getString(R.string.question_two_answer_one),
            context.getString(R.string.question_two_answer_two),
            context.getString(R.string.question_two_answer_three),
            context.getString(R.string.question_two_answer_four),4
        )
        val q3 = DataQuiz(
            context.getString(R.string.question_three),R.drawable.sonicthehedgdog,
            context.getString(R.string.question_three_answer_one),
            context.getString(R.string.question_three_answer_two),
            context.getString(R.string.question_three_answer_three),
            context.getString(R.string.question_three_answer_four),2
        )
        val q4 = DataQuiz(
            context.getString(R.string.question_four),R.drawable.julsign,
            context.getString(R.string.question_four_answer_one),
            context.getString(R.string.question_four_answer_two),
            context.getString(R.string.question_four_answer_three),
            context.getString(R.string.question_four_answer_four),3
        )
        lstQuestion.add(q1)
        lstQuestion.add(q2)
        lstQuestion.add(q3)
        lstQuestion.add(q4)
        return lstQuestion
    }

}