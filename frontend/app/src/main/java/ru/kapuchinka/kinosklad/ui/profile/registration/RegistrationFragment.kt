package ru.kapuchinka.kinosklad.ui.profile.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R

class RegistrationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        val regButton : Button = view.findViewById(R.id.button_reg)

        regButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_registrationFragment_to_navigation_profile)
        }

        return view
    }
}

