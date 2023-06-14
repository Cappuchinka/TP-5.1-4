package ru.kapuchinka.kinosklad.api.service

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.kapuchinka.kinosklad.api.model.feedback.AddFeedback
import ru.kapuchinka.kinosklad.api.model.feedback.Feedbacks

interface FeedbackApi {
    @GET("get_feedback_by_film_id/{film_id}")
    suspend fun getFeedbackByFilmId(@Path("film_id") film_id: Int) : Feedbacks

    @POST("add_feedback/")
    suspend fun addFeedback(@Body feedback: AddFeedback)
}