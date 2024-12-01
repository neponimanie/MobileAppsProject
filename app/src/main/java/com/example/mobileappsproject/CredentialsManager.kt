package com.example.mobileappsproject
import java.util.regex.Pattern

class CredentialsManager {
    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\%\\-\\+]{1,256}" +
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    fun emailValid(mail: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(mail).matches()
    }

    fun passwordValid(pass: String): Boolean {
        return pass.length >= 12 && pass.any { it in "!+-_=@#$%" && pass.count(Char::isDigit) > 0 }
    }
}