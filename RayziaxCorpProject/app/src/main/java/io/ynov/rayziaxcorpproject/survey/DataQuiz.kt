package io.ynov.rayziaxcorpproject.survey

data class DataQuiz(
    val quote: String,
    var image:Int,
    val option1:String,
    val option2:String,
    val option3:String,
    val option4:String,
    val correctResponse:Int)
