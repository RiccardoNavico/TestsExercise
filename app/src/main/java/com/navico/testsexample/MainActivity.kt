package com.navico.testsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.navico.testsexample.databinding.ActivityMainBinding
import com.navico.testsexample.usecases.PasswordValidator
import android.view.inputmethod.EditorInfo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var pwdValidation: ValidationResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            showPwdResult()
        }
        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            if (pwdValidation != null) {
                binding.tvResult.text = ""
                pwdValidation = null
            }
        }
        binding.etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                showPwdResult()
            }
            false
        }
    }

    private fun showPwdResult() {
        pwdValidation = PasswordValidator.checkValid(binding.etPassword.text.toString())
        if (pwdValidation is ValidationResult.Success) {
            binding.tvResult.setText(R.string.pwd_requirement_success)
        } else {
            when ((pwdValidation as ValidationResult.Failure).pwdRequirementNotSatisfied) {
                PwdRequirementNotSatisfied.TOO_SHORT -> binding.tvResult.setText(R.string.pwd_requirement_too_short)
                PwdRequirementNotSatisfied.NOT_ENOUGH_SPECIAL_CHAR -> binding.tvResult.setText(R.string.pwd_requirement_not_enough_special_char)
                PwdRequirementNotSatisfied.NOT_ENOUGH_UPPER_CASE_CHAR ->
                    binding.tvResult.setText(R.string.pwd_requirement_not_enough_upper_case_char)
                PwdRequirementNotSatisfied.NOT_ENOUGH_NUMBERS -> binding.tvResult.setText(R.string.pwd_requirement_not_enough_numbers)
            }
        }
    }
}