package ru.kapuchinka.kinosklad.zamenit_vso.profile.editprofile.changepassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R

class ChangePasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_change_password, container, false)

        val changePasswordButton : Button = view.findViewById(R.id.button_change_pass)

        changePasswordButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_changePasswordFragment_to_navigation_profile)
        }

        return view
    }
}