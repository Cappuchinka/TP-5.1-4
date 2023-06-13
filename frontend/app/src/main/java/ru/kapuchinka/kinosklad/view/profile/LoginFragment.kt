package ru.kapuchinka.kinosklad.view.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.api.model.user.LoginUser
import ru.kapuchinka.kinosklad.databinding.FragmentLoginBinding
import ru.kapuchinka.kinosklad.viewmodel.profile.ProfileViewModel


class LoginFragment : Fragment() {
    var pref: SharedPreferences? = null
    private lateinit var binding: FragmentLoginBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pref = requireContext().getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val loginButton : Button = binding.buttonLogin
        var token: String

        loginButton.setOnClickListener {
            val email = binding.authEmail.text.toString()
            val password = binding.authPassword.text.toString()
            val loginData = LoginUser(email, password)
            profileViewModel.login(loginData)
            token = profileViewModel.token.value.toString()
            saveToken(token)
            it.findNavController().navigate(R.id.action_loginFragment_to_navigation_profile)
        }
        return binding.root
    }

    fun saveToken(token: String) {
        val editor = pref?.edit()
        editor?.putString("token", token)
        editor?.apply()
    }
}