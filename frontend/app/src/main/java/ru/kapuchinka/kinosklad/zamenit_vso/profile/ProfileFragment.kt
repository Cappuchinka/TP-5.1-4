package ru.kapuchinka.kinosklad.zamenit_vso.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val logoutButton : Button = view.findViewById(R.id.button_logout)

        logoutButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_navigation_profile_to_authFragment)
        }

        return view
    }

}