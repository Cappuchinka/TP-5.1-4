package ru.kapuchinka.kinosklad.ui.feedback

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R

class AddFeedBackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_feed_back, container, false)

        val publicFeedBackButton : Button = view.findViewById(R.id.button_public_feedback)

        publicFeedBackButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_addFeedBackFragment_to_filmPageFragment)
        }

        return view
    }

}