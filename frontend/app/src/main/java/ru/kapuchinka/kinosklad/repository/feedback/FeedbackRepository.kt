package ru.kapuchinka.kinosklad.repository.feedback

import ru.kapuchinka.kinosklad.api.RetrofitInstance
import ru.kapuchinka.kinosklad.api.model.feedback.AddFeedback
import ru.kapuchinka.kinosklad.api.model.feedback.Feedbacks

class FeedbackRepository {
    suspend fun getFeedbacksByFilmId(filmId: Int): Feedbacks {
        return RetrofitInstance.feedbackApi.getFeedbackByFilmId(filmId)
    }

    suspend fun addFeedback(feedback: AddFeedback) {
        return RetrofitInstance.feedbackApi.addFeedback(feedback)
    }
}