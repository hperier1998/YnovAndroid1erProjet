package io.ynov.rayziaxcorpproject

/**
 * Hadrien PERIER
 * This data class defines all the aspect/format of a question for the quiz.
 */
data class QuestionFormat(
    val id: Int,
    val question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
)
