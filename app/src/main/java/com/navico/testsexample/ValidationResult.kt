package com.navico.testsexample

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Failure(val pwdRequirementNotSatisfied: PwdRequirementNotSatisfied) : ValidationResult()
}