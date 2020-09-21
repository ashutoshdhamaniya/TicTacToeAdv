package com.codingfreak.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_select.*

class SelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_select)

        vsPlayer.setOnClickListener {
            val intent = Intent(this@SelectActivity,MoreOptionsActivity::class.java)
            startActivity(intent)
        }

        vsComputer.setOnClickListener {
            val intent = Intent(this@SelectActivity,VsComputerActivity::class.java)
            startActivity(intent)
        }
    }
}