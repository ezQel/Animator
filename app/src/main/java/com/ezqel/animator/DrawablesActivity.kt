package com.ezqel.animator

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_drawables.*

class DrawablesActivity : AppCompatActivity() {
    private lateinit var animationDrawable: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawables)
        target_image.setBackgroundResource(R.drawable.ic_battery_charging_list);
        animationDrawable = target_image.background as AnimationDrawable
        start_button.setOnClickListener {
            startAnimation()
        }
    }

    private fun startAnimation() {
        if (animationDrawable.isRunning)
            animationDrawable.stop()
        else
            animationDrawable.start()
    }

}
