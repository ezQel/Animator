package com.ezqel.animator

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Animator.AnimatorListener {

    private var rootSet:AnimatorSet? = null
    private var childSet:AnimatorSet? = null
    private var scaleX:ObjectAnimator? = null
    private var scaleY:ObjectAnimator? = null
    private var rotateX:ObjectAnimator? = null
    private var translateAnimator:ObjectAnimator? = null
    private var scaleAnimator:ObjectAnimator? = null
    private var rotateAnimation:ObjectAnimator? = null
    private var alphaAnimation:ObjectAnimator? = null
    private var xmlAnimatorSet:Animator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        set_from_xml.setOnClickListener{
            setFromXml()
        }
        fadeout_button.setOnClickListener {
            animateAlpha()}
        rotate_button.setOnClickListener{
            rotatetext()
        }
        scale_button.setOnClickListener{
            scaleText()
        }
        translate_button.setOnClickListener{
            translateText()
        }
    }

    private fun translateText(){
        translateAnimator = ObjectAnimator.ofFloat(hello_world, "translationX", 0.0f,200.0f)
        translateAnimator?.apply {
            duration = 2000
            repeatCount =3
            repeatMode = ObjectAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }
    }
    private fun scaleText() {
        scaleAnimator = ObjectAnimator.ofFloat(hello_world, "scaleX", 1.0f,2.0f)
        scaleAnimator?.apply {
            duration = 2000
            repeatCount = 3
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }
    }

    fun animateAlpha(){
        alphaAnimation = ObjectAnimator.ofFloat(hello_world, "Alpha", 1.0f, 0.0f)
        alphaAnimation?.apply {
            duration = 2000
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = 3
            addListener(this@MainActivity)
            start()
        }
    }
    fun rotatetext(){
        rotateAnimation= ObjectAnimator.ofFloat(hello_world, "rotation", 0.0f, 360.0f)
        rotateAnimation?.apply {
            repeatCount = 3
            repeatMode = ObjectAnimator.REVERSE
            duration = 2000
            addListener(this@MainActivity)
            start()

        }
    }

    fun setFromXml(){
        xmlAnimatorSet = AnimatorInflater.loadAnimator(this, R.animator.set)
        xmlAnimatorSet?.apply {
            setTarget(hello_world)
            start()
        }
    }

    fun setFromCode(view:View){
        rootSet = AnimatorSet() //root set for all animations below
        childSet = AnimatorSet() // child set with scale animations

        scaleX = ObjectAnimator.ofFloat(hello_world, "scaleX",1.0f, 2.0f)
        scaleX?.duration =500

        scaleY = ObjectAnimator.ofFloat(hello_world, "scaleY",1.0f, 2.0f)
        scaleY?.duration =500

        rotateX = ObjectAnimator.ofFloat(hello_world, "rotationX",0.0f, 360.0f)
        rotateX?.duration=500

//        rootSet?.playSequentially(rotateX, childSet)
//        childSet?.playTogether(scaleX, scaleY)

        rootSet?.play(childSet)?.after(rotateX)
        childSet?.play(scaleX)?.with(scaleY)
        rootSet?.start()
    }

    override fun onAnimationStart(animator: Animator?){
        if (animator == scaleAnimator)
            Toast.makeText(this,"Scale Animation Started", Toast.LENGTH_SHORT).show()
        if (animator == rotateAnimation)
            Toast.makeText(this,"Rotate Animation Started", Toast.LENGTH_SHORT).show()
        if (animator==alphaAnimation)
            Toast.makeText(this,"Fade Animation Started", Toast.LENGTH_SHORT).show()
        if (animator==translateAnimator)
            Toast.makeText(this,"Translation Animation Started", Toast.LENGTH_SHORT).show()

    }

    override fun onAnimationRepeat(animator: Animator?) {
        if (animator == scaleAnimator)
            Toast.makeText(this,"Sacale Animation repeated", Toast.LENGTH_SHORT).show()
        if (animator == rotateAnimation)
            Toast.makeText(this,"Rotate Animation repeated", Toast.LENGTH_SHORT).show()
        if (animator==alphaAnimation)
            Toast.makeText(this,"Fade Animation repeated", Toast.LENGTH_SHORT).show()
        if (animator==translateAnimator)
            Toast.makeText(this,"Translation Animation repeated", Toast.LENGTH_SHORT).show()
    }



    override fun onAnimationCancel(animator: Animator?) {
        if (animator == scaleAnimator)
            Toast.makeText(this,"Sacale Animation cancelled", Toast.LENGTH_SHORT).show()
        if (animator == rotateAnimation)
            Toast.makeText(this,"Rotate Animation cancelled", Toast.LENGTH_SHORT).show()
        if (animator==alphaAnimation)
            Toast.makeText(this,"Fade Animation cancelled", Toast.LENGTH_SHORT).show()
        if (animator==translateAnimator)
            Toast.makeText(this,"Translation Animation cancelled", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationEnd(animator: Animator?) {
        if (animator == scaleAnimator)
            Toast.makeText(this,"Sacale Animation Ended", Toast.LENGTH_SHORT).show()
        if (animator == rotateAnimation)
            Toast.makeText(this,"Rotate Animation Ended", Toast.LENGTH_SHORT).show()
        if (animator==alphaAnimation)
            Toast.makeText(this,"Fade Animation Ended", Toast.LENGTH_SHORT).show()
        if (animator==translateAnimator)
            Toast.makeText(this,"Translation Animation Ended", Toast.LENGTH_SHORT).show()

    }
}
