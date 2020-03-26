package com.anesabml.hospital.core.utils

object Validator {

    /*
        TODO: In reality, this will have more complex logic including, but not limited to, actual
        authentication of the username and password.
     */
    fun isPasswordValid(text: String?): Boolean {
        return text != null && text.length >= 8
    }

}