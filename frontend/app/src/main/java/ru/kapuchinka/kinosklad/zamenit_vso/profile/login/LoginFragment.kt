package ru.kapuchinka.kinosklad.zamenit_vso.profile.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val loginButton : Button = view.findViewById(R.id.button_login)

        loginButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_navigation_profile)
        }
        return view
    }
}