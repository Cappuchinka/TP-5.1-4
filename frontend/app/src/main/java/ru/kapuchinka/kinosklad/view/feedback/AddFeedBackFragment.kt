package ru.kapuchinka.kinosklad.view.feedback

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.yandex.metrica.YandexMetrica
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.api.model.feedback.AddFeedback
import ru.kapuchinka.kinosklad.databinding.FragmentAddFeedBackBinding
import ru.kapuchinka.kinosklad.utils.YandexMetrica.YandexEvents
import ru.kapuchinka.kinosklad.viewmodel.feedback.FeedbackViewModel

class AddFeedBackFragment : Fragment() {
    private lateinit var pref: SharedPreferences
    private lateinit var binding: FragmentAddFeedBackBinding
    private val feedbackViewModel: FeedbackViewModel by viewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        pref = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFeedBackBinding.inflate(inflater, container, false)
        val filmId = arguments?.getInt("filmId", 0)
        val token = arguments?.getString("token", "")
        lateinit var nickname: String

        feedbackViewModel.getUserByToken(token!!)
        feedbackViewModel.nickname.observe(viewLifecycleOwner) {
            nickname = it.toString()
        }


        val publicFeedBackButton : Button = binding.buttonPublicFeedback

        publicFeedBackButton.setOnClickListener {
            val textFeedback = binding.feedbackText.text.toString().trim()
            if (textFeedback == "") {
                Toast.makeText(requireContext(), "Нельзя оставить пустой отзыв", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val feedback = AddFeedback(film_id = filmId!!, nickname = nickname, feedback_text = textFeedback)
            feedbackViewModel.addFeedback(feedback = feedback)

            YandexMetrica.reportEvent(YandexEvents.PUBLIC_FEEDBACK)
            val bundle = Bundle()
            bundle.putInt("filmId", filmId)
            it.findNavController().navigate(R.id.action_addFeedBackFragment_to_filmPageFragment, bundle)
        }

        return binding.root
    }
}