package com.Cleber.appfilme


import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.Cleber.appfilme.View.FormaLogin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        window.statusBarColor =Color.parseColor("#FF000000")


        Handler(Looper.getMainLooper()).postDelayed({
          val int = Intent(this,FormaLogin::class.java)
            finish()
            startActivity(int)
        }, 2000)

    }

}