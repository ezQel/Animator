package com.ezqel.animator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.activity_vector.*

class VectorActivity : AppCompatActivity() {
    lateinit var animatedVectorDrawableCompat: AnimatedVectorDrawableCompat

    private var isChecked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vector)

        check_to_clear.setOnClickListener{
            if (isChecked)
                checkToClear()
            else
                clearTOCheck()
            isChecked = !isChecked
        }
    }

    private fun clearTOCheck() {
        check_to_clear.setImageResource(R.drawable.avd_clear_to_check)
        animatedVectorDrawableCompat = check_to_clear.drawable as AnimatedVectorDrawableCompat
        animatedVectorDrawableCompat.start()
    }

    private fun checkToClear(){
        check_to_clear.setImageResource(R.drawable.avd_check_to_clear)
        animatedVectorDrawableCompat = check_to_clear.drawable as AnimatedVectorDrawableCompat
        animatedVectorDrawableCompat.start()

    }
}
