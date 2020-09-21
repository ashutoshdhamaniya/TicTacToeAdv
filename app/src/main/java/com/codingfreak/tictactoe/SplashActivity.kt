package com.codingfreak.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {

    private lateinit var imgLogo : ImageView
    private lateinit var handler : Handler

    private lateinit var topAnim : Animation


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash)

        imgLogo = findViewById(R.id.imgLogo)
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation)

        imgLogo.startAnimation(topAnim)

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this@SplashActivity, SelectActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}