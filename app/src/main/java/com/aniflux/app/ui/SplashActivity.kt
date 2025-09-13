package com.aniflux.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.aniflux.app.MainActivity
import com.aniflux.app.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val animationView = findViewById<LottieAnimationView>(R.id.splashAnimation)

        // When animation finishes, go to MainActivity
        animationView.addAnimatorUpdateListener { animator ->
            if (animator.animatedFraction == 1f) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
