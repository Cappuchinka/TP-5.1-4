package ru.kapuchinka.kinosklad.view.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    var pref: SharedPreferences? = null
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pref = requireContext().getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        val logoutButton : Button = binding.buttonLogout

        logoutButton.setOnClickListener{
            deleteToken()
            it.findNavController().navigate(R.id.action_navigation_profile_to_authFragment)
        }

        return binding.root
    }

    fun deleteToken() {
        val editor = pref?.edit()
        editor?.remove("token")
        editor?.apply()
    }
}