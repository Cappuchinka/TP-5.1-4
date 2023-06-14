package ru.kapuchinka.kinosklad.view.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.api.model.user.LoginUser
import ru.kapuchinka.kinosklad.databinding.FragmentLoginBinding
import ru.kapuchinka.kinosklad.viewmodel.profile.ProfileViewModel


class LoginFragment : Fragment() {
    private lateinit var pref: SharedPreferences
    private lateinit var binding: FragmentLoginBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pref = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val loginButton : Button = binding.buttonLogin
        var token: String

        loginButton.setOnClickListener {
            val email = binding.authEmail.text.toString().trim()
            val password = binding.authPassword.text.toString().trim()

            if (email == "") {
                Toast.makeText(requireContext(), "Введите email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password == "") {
                Toast.makeText(requireContext(), "Введите пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginData = LoginUser(email=email, password=password)
            profileViewModel.auth(loginData)
            val tokenViewModel = profileViewModel.myGetToken()
            tokenViewModel.observe(viewLifecycleOwner) { tokenView ->
                token = tokenView.token
                saveToken(token)
                it.findNavController().navigate(R.id.action_loginFragment_to_navigation_profile)
            }
        }
        return binding.root
    }

    fun saveToken(token: String) {
        val editor = pref.edit()
        editor?.putString("token", token)
        editor?.apply()
    }
}