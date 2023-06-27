package com.example.project_app_demo

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import com.example.projectappdemo.MainActivity
import com.example.projectappdemo.R
import com.example.projectappdemo.SignupActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var loginbtn: Button
    private lateinit var image: ImageView
    private lateinit var logoText: TextView
    private lateinit var sloganText: TextView
    private lateinit var username: TextInputLayout
    private lateinit var password: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)
        button = findViewById(R.id.signup_screen)
        image = findViewById(R.id.logo_image)
        logoText = findViewById(R.id.logo_text)
        sloganText = findViewById(R.id.slogan)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginbtn = findViewById(R.id.go_button)

        val intent = Intent(this@LoginActivity, SignupActivity::class.java)
        val intent1 = Intent(this@LoginActivity, MainActivity::class.java)


        button.setOnClickListener {
            val pairs = listOf(
                androidx.core.util.Pair<View, String>(image, "logo_image"),
                androidx.core.util.Pair<View, String>(logoText, "logo_text"),
                androidx.core.util.Pair<View, String>(sloganText, "logo_dec"),
                androidx.core.util.Pair<View, String>(username, "username_tran"),
                androidx.core.util.Pair<View, String>(password, "password_tran"),
                androidx.core.util.Pair<View, String>(loginbtn, "button_tran"),
                androidx.core.util.Pair<View, String>(button, "login_signup_tran")
            )

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@LoginActivity,
                *pairs.toTypedArray()
            )

            startActivity(intent, options.toBundle())
        }

        loginbtn.setOnClickListener {
            startActivity(intent1)
        }


    }
}