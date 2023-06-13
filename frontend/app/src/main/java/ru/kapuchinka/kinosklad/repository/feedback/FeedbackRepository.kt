package ru.kapuchinka.kinosklad.repository.feedback

import ru.kapuchinka.kinosklad.api.RetrofitInstance
import ru.kapuchinka.kinosklad.api.model.feedback.Feedbacks

class FeedbackRepository {
    suspend fun getFeedbacksByFilmId(filmId: Int): Feedbacks {
        return RetrofitInstance.feedbackApi.getFeedbackByFilmId(filmId)
    }
}