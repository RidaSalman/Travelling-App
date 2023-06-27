package com.example.projectappdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.project_app_demo.LoginActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User

class SignupActivity : AppCompatActivity() {

    // Declaration
    private lateinit var regName: TextInputLayout
    private lateinit var regUsername: TextInputLayout
    private lateinit var regEmail: TextInputLayout
    private lateinit var regPhoneNo: TextInputLayout
    private lateinit var regPassword: TextInputLayout
    private lateinit var regBtn: Button
    private lateinit var regToLoginBtn: Button

    // Create a FirebaseDatabase instance
    private var rootNode: FirebaseDatabase = FirebaseDatabase.getInstance()

    // Get a DatabaseReference
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_signup)

        // Initialization
        regName = findViewById(R.id.reg_name)
        regUsername = findViewById(R.id.reg_username)
        regEmail = findViewById(R.id.reg_email)
        regPhoneNo = findViewById(R.id.reg_phoneno)
        regPassword = findViewById(R.id.reg_password)
        regBtn = findViewById(R.id.reg_btn)
        regToLoginBtn = findViewById(R.id.reg_tologinbtn)

        // Set click listeners or perform other actions with the views
        regBtn.setOnClickListener {
            // Validate registration inputs
            val isValid = validateRegistrationInputs()

            if (isValid) {
                // Handle registration button click
                val name = regName.editText?.text.toString()
                val username = regUsername.editText?.text.toString()
                val email = regEmail.editText?.text.toString()
                val phoneNo = regPhoneNo.editText?.text.toString()
                val password = regPassword.editText?.text.toString()

                reference = FirebaseDatabase.getInstance().getReference("Users")

                val user = User(name, username, email, phoneNo, password)

                reference.child(username).setValue(user).addOnSuccessListener {
                    regName.editText?.text?.clear()
                    regUsername.editText?.text?.clear()
                    regEmail.editText?.text?.clear()
                    regPhoneNo.editText?.text?.clear()
                    regPassword.editText?.text?.clear()

                    Toast.makeText(this@SignupActivity, "Successfully Saved", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this@SignupActivity, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        regToLoginBtn.setOnClickListener {
            // Handle registration to login button click
        }
    }

    private fun validateRegistrationInputs(): Boolean {
        // Get input values
        val name = regName.editText?.text.toString()
        val username = regUsername.editText?.text.toString()
        val email = regEmail.editText?.text.toString()
        val phoneNo = regPhoneNo.editText?.text.toString()
        val password = regPassword.editText?.text.toString()

        // Reset errors
        regName.error = null
        regUsername.error = null
        regEmail.error = null
        regPhoneNo.error = null
        regPassword.error = null

        var isValid = true

        // Validate name
        if (name.isEmpty()) {
            regName.error = "Please enter your name"
            isValid = false
        }

        // Validate username
        if (username.isEmpty()) {
            regUsername.error = "Please enter a username"
            isValid = false
        }

        // Validate email
        if (email.isEmpty()) {
            regEmail.error = "Please enter your email"
            isValid = false
        } else if (!isValidEmail(email)) {
            regEmail.error = "Please enter a valid email address"
            isValid = false
        }

        // Validate phone number
        if (phoneNo.isEmpty()) {
            regPhoneNo.error = "Please enter your phone number"
            isValid = false
        }

        // Validate password
        if (password.isEmpty()) {
            regPassword.error = "Please enter a password"
            isValid = false
        } else if (password.length < 6) {
            regPassword.error = "Password must be at least 6 characters long"
            isValid = false
        }

        return isValid
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}
