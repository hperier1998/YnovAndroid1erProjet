package io.ynov.rayziaxcorpproject.survey
import android.content.Context
import io.ynov.rayziaxcorpproject.R

object DataObject {

    fun getQuestions(context:Context): ArrayList<DataQuiz>{
        val lstQuestion = ArrayList<DataQuiz>()

        val q1 = DataQuiz(
            context.getString(R.string.question_one),1,
            context.getString(R.string.question_one_answer_one),
            context.getString(R.string.question_one_answer_two),
            context.getString(R.string.question_one_answer_three),
            context.getString(R.string.question_one_answer_four),1
        )

        val q2 = DataQuiz(
            context.getString(R.string.question_one),2,
            context.getString(R.string.question_one_answer_one),
            context.getString(R.string.question_one_answer_two),
            context.getString(R.string.question_one_answer_three),
            context.getString(R.string.question_one_answer_four),1
        )
        val q3 = DataQuiz(
            context.getString(R.string.question_one),3,
            context.getString(R.string.question_one_answer_one),
            context.getString(R.string.question_one_answer_two),
            context.getString(R.string.question_one_answer_three),
            context.getString(R.string.question_one_answer_four),1
        )
        val q4 = DataQuiz(
            context.getString(R.string.question_one),4,
            context.getString(R.string.question_one_answer_one),
            context.getString(R.string.question_one_answer_two),
            context.getString(R.string.question_one_answer_three),
            context.getString(R.string.question_one_answer_four),1
        )
        lstQuestion.add(q1)
        lstQuestion.add(q2)
        lstQuestion.add(q3)
        lstQuestion.add(q4)
        return lstQuestion
    }

}