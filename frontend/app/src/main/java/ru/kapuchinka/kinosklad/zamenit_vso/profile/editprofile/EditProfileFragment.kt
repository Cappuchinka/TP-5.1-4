package ru.kapuchinka.kinosklad.zamenit_vso.profile.editprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R

class EditProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        val changeNickButton : Button = view.findViewById(R.id.button_change_nick)
        val changePasswordButton : Button = view.findViewById(R.id.button_change_password)

        changeNickButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_editProfileFragment_to_changeNickFragment)
        }

        changePasswordButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_editProfileFragment_to_changePasswordFragment)
        }

        return view
    }
}