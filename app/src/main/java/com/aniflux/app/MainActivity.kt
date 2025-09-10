package com.aniflux.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.eightbitlab.blurview.BlurView
import com.eightbitlab.blurview.RenderScriptBlur

class MainActivity : AppCompatActivity() {
    private lateinit var blurView: BlurView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blurView = findViewById(R.id.blurView)

        // the view we want to blur the background of (root of layout)
        val rootView = findViewById<View>(R.id.root)
        // radius in dp (float)
        val radius = 12f

        // Setup blur (uses RenderScriptBlur internally)
        blurView.setupWith(rootView)
            .setFrameClearDrawable(rootView.background)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(radius)
            .setHasFixedTransformationMatrix(true)
    }
}
