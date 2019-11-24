package com.ezqel.animator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_key_frame.*
import kotlinx.android.synthetic.main.keyframe_2_layout.*

class KeyFrameActivity : AppCompatActivity() {
    private var cloneLayout = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_key_frame)
        cloneLayout = R.layout.keyframe_2_layout
        constraint_layout.setOnClickListener{
            keyFrameTransition()
        }
    }

    private fun keyFrameTransition() {
        val transition = ChangeBounds()
        transition.duration = 500
        transition.interpolator = AnticipateOvershootInterpolator()

        val constraints = ConstraintSet()
        constraints.clone(this, cloneLayout)
        constraints.applyTo(constraint_layout)

        TransitionManager.beginDelayedTransition(constraint_layout, transition)
        if (cloneLayout == R.layout.keyframe_2_layout)
            cloneLayout = R.layout.activity_key_frame
        else
            cloneLayout = R.layout.keyframe_2_layout
    }
}
