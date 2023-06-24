package ru.kapuchinka.kinosklad.viewmodel.feedback

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kapuchinka.kinosklad.api.model.feedback.AddFeedback
import ru.kapuchinka.kinosklad.api.model.feedback.Feedbacks
import ru.kapuchinka.kinosklad.repository.feedback.FeedbackRepository
import ru.kapuchinka.kinosklad.repository.user.UserRepository

class FeedbackViewModel() : ViewModel() {
    var feedbackRepository = FeedbackRepository()
    var userRepository = UserRepository()
    var feedbacks: MutableLiveData<Feedbacks> = MutableLiveData()
    var nickname: MutableLiveData<String> = MutableLiveData()

    fun getFeedbackByFilmId(film_id: Int) {
        viewModelScope.launch {
            feedbacks.value = feedbackRepository.getFeedbacksByFilmId(film_id)
        }
    }

    fun getUserByToken(token: String) {
        viewModelScope.launch {
            nickname.value = userRepository.getUserByToken(token).body()?.nickname
        }
    }

    fun addFeedback(feedback: AddFeedback) {
        viewModelScope.launch {
            feedbackRepository.addFeedback(feedback)
        }
    }
}