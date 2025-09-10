package com.aniflux.app.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.aniflux.app.MainActivity
import com.aniflux.app.R

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_MS = 1400L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val lottie = findViewById<LottieAnimationView>(R.id.lottie_splash)
        lottie.playAnimation()

        // Start any minimal prefetch work via repository here (non-blocking).
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, SPLASH_MS)
    }
}
