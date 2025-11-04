package com.rafael.proffy.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityRegisterStepTwoBinding
import com.rafael.proffy.ui.login.LoginActivity

class RegisterStepTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterStepTwoBinding

    private var firstName: String? = null
    private var lastName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStepTwoBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firstName = intent.getStringExtra("firstName")
        lastName = intent.getStringExtra("lastName")

        binding.buttonGoBack.setOnClickListener {
            goBack()
        }

        binding.buttonNext.setOnClickListener {
            handleRegister()
        }
    }

    private fun goBack() {
        finish()
    }

    private fun validateEmail(): Boolean {
        val email = binding.textInputLayoutEmail.editText?.text.toString().trim()

        return if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputLayoutEmail.error = "Por favor, insira um e-mail válido."
            false
        } else {
            binding.textInputLayoutEmail.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.textInputLayoutPassword.editText?.text.toString().trim()

        return if (password.length < 6) {
            binding.textInputLayoutPassword.error = "Senha deve conter no mínimo 6 caracteres."
            false
        } else {
            binding.textInputLayoutPassword.error = null
            true
        }
    }

    private fun handleRegister() {
        val isEmailValid = validateEmail()
        val isPasswordValid = validatePassword()

        if (isEmailValid && isPasswordValid) {
            Toast.makeText(this, "Cadastro concluído!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}