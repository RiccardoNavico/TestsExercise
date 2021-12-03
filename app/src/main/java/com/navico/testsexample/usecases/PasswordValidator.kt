package com.navico.testsexample.usecases

import com.navico.testsexample.PwdRequirementNotSatisfied
import com.navico.testsexample.ValidationResult

/*
Credit for this regex goes to:
https://stackoverflow.com/questions/16689167/need-regular-expression-for-checking-at-least-3-uppercase-3-lowercase-3-digits
 */
private const val PWD_VALIDATION_SPECIAL_CHAR_REGEX = "^(?=(.*[^A-Za-z0-9]){3})"
private const val PWD_VALIDATION_UPPER_CASE_REGEX = "(?=(.*[A-Z]){3})"
private const val PWD_VALIDATION_NUMBERS_REGEX = "(?=(.*\\d){3}).+"

object PasswordValidator {

    fun checkValid(password: String): ValidationResult = when {
        password.length < 8 -> {
            ValidationResult.Failure(PwdRequirementNotSatisfied.TOO_SHORT)
        }
        !Regex(PWD_VALIDATION_SPECIAL_CHAR_REGEX).containsMatchIn(password) -> {
            ValidationResult.Failure(PwdRequirementNotSatisfied.NOT_ENOUGH_SPECIAL_CHAR)
        }
        !Regex(PWD_VALIDATION_UPPER_CASE_REGEX).containsMatchIn(password) -> {
            ValidationResult.Failure(PwdRequirementNotSatisfied.NOT_ENOUGH_SPECIAL_CHAR)
        }
        !Regex(PWD_VALIDATION_NUMBERS_REGEX).containsMatchIn(password) -> {
            ValidationResult.Failure(PwdRequirementNotSatisfied.NOT_ENOUGH_SPECIAL_CHAR)
        }
        else -> ValidationResult.Success
    }

}