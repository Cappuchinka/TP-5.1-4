package ru.kapuchinka.kinosklad.view.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.api.model.user.RegisterUser
import ru.kapuchinka.kinosklad.databinding.FragmentRegistrationBinding
import ru.kapuchinka.kinosklad.viewmodel.profile.ProfileViewModel

class RegistrationFragment : Fragment() {
    private lateinit var pref: SharedPreferences
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentRegistrationBinding
    private val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")


    override fun onAttach(context: Context) {
        super.onAttach(context)
        pref = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val regButton : Button = binding.buttonReg
        var token: String

        regButton.setOnClickListener {
            val email = binding.regEmail.text.toString().trim()
            val nickname = binding.regNick.text.toString().trim()
            val password = binding.regPassword.text.toString().trim()
            val passwordRepeat = binding.regPasswordRepeat.text.toString().trim()

            if (email == "") {
                Toast.makeText(requireContext(), "Введите email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isEmailValid(email)) {
                Toast.makeText(requireContext(), "Введите email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nickname == "") {
                Toast.makeText(requireContext(), "Введите ник", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nickname.length < 4) {
                Toast.makeText(requireContext(), "Никнейм должен быть больше 4 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password == "") {
                Toast.makeText(requireContext(), "Введите пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(requireContext(), "Пароль не может быть меньше 6 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passwordRepeat == "") {
                Toast.makeText(requireContext(), "Повторите пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.equals(passwordRepeat)) {
                val registerData = RegisterUser(nickname=nickname, email=email, password=password)

                profileViewModel.register(registerData)
                val tokenViewModel = profileViewModel.myGetToken()
                tokenViewModel.observe(viewLifecycleOwner) { tokenView ->
                    token = tokenView.token
                    saveToken(token)
                    it.findNavController().navigate(R.id.action_registrationFragment_to_navigation_profile)
                }
            } else {
                Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun saveToken(token: String) {
        val editor = pref.edit()
        editor?.putString("token", token)
        editor?.apply()
    }

    private fun isEmailValid(email: String): Boolean {
        return emailRegex.matches(email)
    }
}

