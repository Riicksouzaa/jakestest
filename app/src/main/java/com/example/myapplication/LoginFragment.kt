package com.example.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.navigation.navigateSafe

class LoginFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding

    // LIFECYCLE EVENTS section start

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attemptLogIn()
        setOnClickListener()

    }

    // LIFECYCLE EVENTS section end

    // USER ACTIONS section start

    private fun attemptLogIn() {
        viewModel.loginSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                navigateToNextScreen()
            } else {
                showError()
            }
        })
    }

    private fun setOnClickListener() = with(binding) {
        loginButton.setOnClickListener {
            val email = loginEmailEt.text.toString()
            val password = loginPwdEt.text.toString()
            viewModel.login(email, password)
        }
    }

    private fun navigateToNextScreen() {
        navigateSafe(R.id.action_loginFragment_to_photoListFragment)
    }

    private fun showError() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Login")
        builder.setMessage("Please provide email and password")
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }

    // USER ACTIONS section end

}