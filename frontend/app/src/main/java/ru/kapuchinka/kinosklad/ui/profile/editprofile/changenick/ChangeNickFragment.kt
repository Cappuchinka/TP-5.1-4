package ru.kapuchinka.kinosklad.ui.profile.editprofile.changenick

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R

class ChangeNickFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_change_nick, container, false)

        val changeNickButton : Button = view.findViewById(R.id.button_change_nickname)

        changeNickButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_changeNickFragment_to_navigation_profile)
        }
        return view
    }
}