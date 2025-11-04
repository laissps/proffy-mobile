package com.rafael.proffy.ui.register

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityRegisterStepOneBinding

class RegisterStepOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterStepOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStepOneBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonGoBack.setOnClickListener {
            goBack()
        }

        binding.buttonNext.setOnClickListener {
            handleNextStep()
        }
    }

    private fun goBack() {
        finish()
    }

    private fun validateFirstName(): Boolean {
        val name = binding.textInputLayoutFirstName.editText?.text.toString().trim()

        return if (name.length < 4) {
            binding.textInputLayoutFirstName.error = "Nome deve conter no mínimo 4 caracteres."
            false
        } else {
            binding.textInputLayoutFirstName.error = null // Limpa o erro
            true
        }
    }

    private fun validateLastName(): Boolean {
        // Usamos o ID do layout: text_input_layout_last_name
        val lastName = binding.textInputLayoutLastName.editText?.text.toString().trim()

        return if (lastName.length < 4) {
            binding.textInputLayoutLastName.error = "Sobrenome deve conter no mínimo 4 caracteres."
            false
        } else {
            binding.textInputLayoutLastName.error = null // Limpa o erro
            true
        }
    }


    private fun handleNextStep() {
        val isFirstNameValid = validateFirstName()
        val isLastNameValid = validateLastName()

        if (isFirstNameValid && isLastNameValid) {
            val firstName = binding.textInputEditFirstName.text.toString()
            val lastName = binding.textInputEditLastName.text.toString()

            val intent = Intent(this, RegisterStepTwoActivity::class.java)
            intent.putExtra("firstName", firstName)
            intent.putExtra("lastName", lastName)
            startActivity(intent)
        }
    }
}