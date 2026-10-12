package com.example.a25172012025_mad_pr6

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity(), Animation.AnimationListener {
    lateinit var guniAnimation: AnimationDrawable
    lateinit var twinAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val uvpceImageView = findViewById<ImageView>(R.id.uvpce_imageview)
        uvpceImageView.setBackgroundResource(R.drawable.guni_animation_list)
        guniAnimation = uvpceImageView.background as AnimationDrawable

        twinAnimation = AnimationUtils.loadAnimation(this, R.anim.twin_animation)
        twinAnimation.setAnimationListener(this)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val uvpceImageView = findViewById<ImageView>(R.id.uvpce_imageview)
        if (hasFocus) {
            guniAnimation.start()
            uvpceImageView.startAnimation(twinAnimation)
        } else {
            guniAnimation.stop()
        }
    }

    override fun onAnimationStart(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        Intent(this, MainActivity::class.java).also { startActivity(it) }
        overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
        finish()
    }

    override fun onAnimationRepeat(animation: Animation?) {
    }
}
