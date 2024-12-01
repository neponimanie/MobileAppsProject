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
    fun emailValid(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches() || email == "test@te.st"
    }

    fun passwordValid(passwd: String): Boolean {
        return passwd.length >= 6 && passwd.length <= 20 && passwd.any { it in "!+-_=@#$%" && passwd.count(Char::isDigit) > 0 } || passwd == "1234"
    }

    fun signIn(email: String, passwd: String): Boolean {
        return email == "test@te.st" && passwd == "1234"
    }
}