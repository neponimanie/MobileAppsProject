package com.example.mobileappsproject
import org.junit.Assert
import org.junit.Test
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue

class CredentialsManagerTest {
    val credentialsmanager = CredentialsManager()
    @Test
    fun isEmailEmpty() {
        val email = ""
        assertFalse(credentialsmanager.emailValid(email))
    }
    @Test fun isEmailWrong() {
        val email = "adisof@aisf"
        assertFalse(credentialsmanager.emailValid(email))
    }
    @Test fun isEmailRight() {
        val email = "something@gmail.com"
        assertTrue(credentialsmanager.emailValid(email))
    }
    @Test fun isPasswordEmpty() {
        val passwd = ""
        assertFalse(credentialsmanager.passwordValid(passwd))
    }
    @Test fun isPasswordFilled() {
        val passwd = "aadpi3421_er"
        assertTrue(credentialsmanager.passwordValid(passwd))
    }
    @Test
    fun successfulRegistration() {
        assertTrue(credentialsmanager.register("example@gmail.com", "1234"))
    }

    @Test
    fun duplicateEmail() {
        assertFalse(credentialsmanager.register("test@te.st", "new_password"))
    }

    @Test
    fun successfulLogin() {
        assertTrue(credentialsmanager.signIn("test@te.st", "1234"))
    }

    @Test
    fun incorrectPassword() {
        assertFalse(credentialsmanager.signIn("test@te.st", "wrong_password"))
    }
}