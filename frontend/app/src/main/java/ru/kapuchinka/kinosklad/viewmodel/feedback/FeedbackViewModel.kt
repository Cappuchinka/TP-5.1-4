package ru.kapuchinka.kinosklad.viewmodel.feedback

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kapuchinka.kinosklad.api.model.feedback.Feedbacks
import ru.kapuchinka.kinosklad.repository.feedback.FeedbackRepository

class FeedbackViewModel() : ViewModel() {
    var repository = FeedbackRepository()
    var feedbacks: MutableLiveData<Feedbacks> = MutableLiveData()

    fun getFeedbackByFilmId(film_id: Int) {
        viewModelScope.launch {
            feedbacks.value = repository.getFeedbacksByFilmId(film_id)
        }
    }
}