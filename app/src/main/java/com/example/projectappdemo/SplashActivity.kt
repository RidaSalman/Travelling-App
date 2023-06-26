package com.example.projectappdemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import com.example.project_app_demo.LoginActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_SCREEN = 5000

    lateinit var topanim: Animation
    lateinit var bottomanim: Animation
    lateinit var image: ImageView
    lateinit var logo: TextView
    lateinit var slogan: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        val topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomanim = AnimationUtils.loadAnimation(this, R.anim.bottam_animation)

        image = findViewById(R.id.imageView)
        logo = findViewById(R.id.textView)
        slogan = findViewById(R.id.textView3)

        image.startAnimation(topanim)
        logo.startAnimation(bottomanim)
        slogan.startAnimation(bottomanim)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)

            // Create the transition pairs
            val imagePair = Pair.create<View, String>(image, ViewCompat.getTransitionName(image))
            val logoPair = Pair.create<View, String>(logo, ViewCompat.getTransitionName(logo))

            // Create the options and add the transition pairs to the bundle
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@SplashActivity,
                imagePair,
                logoPair
            )

            // Set the transition names as extras in the intent
            val bundle = options.toBundle()
            if (bundle != null) {
                bundle.putString(ViewCompat.getTransitionName(image), "logo_image")
            }
            if (bundle != null) {
                bundle.putString(ViewCompat.getTransitionName(logo), "logo_text")
            }

            // Start the activity with the bundled options
            ActivityCompat.startActivity(this@SplashActivity, intent, bundle)
            finish()
        }, SPLASH_SCREEN.toLong())
    }
}
