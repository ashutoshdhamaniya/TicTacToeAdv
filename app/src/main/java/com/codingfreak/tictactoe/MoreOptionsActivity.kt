package com.codingfreak.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_more_options.*

class MoreOptionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_more_options)

        normalMode.setOnClickListener {
            val intent = Intent(this@MoreOptionsActivity,NormalActivity::class.java)
            startActivity(intent)
        }

        kidsMode.setOnClickListener {
            val intent = Intent(this@MoreOptionsActivity,KidsActivity::class.java)
            startActivity(intent)
        }

        coupleMode.setOnClickListener {
            val intent = Intent(this@MoreOptionsActivity,CouplesActivity::class.java)
            startActivity(intent)
        }

    }
}