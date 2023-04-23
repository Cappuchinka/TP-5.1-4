package ru.kapuchinka.kinosklad.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R

class AuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth, container, false)

        val logButton : Button = view.findViewById(R.id.button_login)
        val regButton : Button = view.findViewById(R.id.button_registration)

        logButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_authFragment_to_loginFragment)
        }

        regButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_authFragment_to_registrationFragment)
        }

        return view
    }

}