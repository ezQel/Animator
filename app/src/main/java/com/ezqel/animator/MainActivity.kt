package com.ezqel.animator

import android.animation.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Animator.AnimatorListener {
    private var pvhAnimator:ObjectAnimator? = null
    private var scalexPropertyValuesHolder:PropertyValuesHolder? = null
    private var scaleYPropertyValuesHolder:PropertyValuesHolder? = null
    private var rotateXPropertyValuesHolder:PropertyValuesHolder? = null
    private var vpa:ViewPropertyAnimator? = null
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
            rotateText()
        }
        scale_button.setOnClickListener{
            scaleText()
        }
        translate_button.setOnClickListener{
            translateText()
        }
        vp_a.setOnClickListener{
            animateWithVPA()
        }
        pv_holder.setOnClickListener{
            animateWithPVH()
        }
        open_other_activity.setOnClickListener{
            startActivity(Intent(this, DrawablesActivity::class.java))
        }
        goto_anim_vector.setOnClickListener{
            startActivity(Intent(this, VectorActivity::class.java))
        }
    }

    private fun translateText(){
        translateAnimator = ObjectAnimator.ofFloat(target_image, "translationX", 0.0f,200.0f)
        translateAnimator?.apply {
            duration = 2000
            repeatCount =3
            repeatMode = ObjectAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }
    }
    private fun scaleText() {
        scaleAnimator = ObjectAnimator.ofFloat(target_image, "scaleX", 1.0f,2.0f)
        scaleAnimator?.apply {
            duration = 2000
            repeatCount = 3
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }
    }

    private fun animateAlpha(){
        alphaAnimation = ObjectAnimator.ofFloat(target_image, "Alpha", 1.0f, 0.0f)
        alphaAnimation?.apply {
            duration = 2000
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = 3
            addListener(this@MainActivity)
            start()
        }
    }
    private fun rotateText(){
        rotateAnimation= ObjectAnimator.ofFloat(target_image, "rotation", 0.0f, 360.0f)
        rotateAnimation?.apply {
            repeatCount = 3
            repeatMode = ObjectAnimator.REVERSE
            duration = 2000
            addListener(this@MainActivity)
            start()
        }
    }

    private fun setFromXml(){
        xmlAnimatorSet = AnimatorInflater.loadAnimator(this, R.animator.set)
        xmlAnimatorSet?.apply {
            setTarget(target_image)
            start()
        }
    }

    fun setFromCode(view:View){
        rootSet = AnimatorSet() //root set for all animations below
        childSet = AnimatorSet() // child set with scale animations

        scaleX = ObjectAnimator.ofFloat(target_image, "scaleX",1.0f, 2.0f)
        scaleX?.duration = 500
//        scaleX?.interpolator = DecelerateInterpolator()
//        scaleX?.interpolator = AccelerateDecelerateInterpolator()
//        scaleX?.interpolator = BounceInterpolator()

        scaleY = ObjectAnimator.ofFloat(target_image, "scaleY",1.0f, 2.0f)
        scaleY?.duration =500
//        scaleX?.interpolator = DecelerateInterpolator()
//        scaleY?.interpolator = AccelerateDecelerateInterpolator()
//        scaleY?.interpolator = BounceInterpolator()

//        childSet?.interpolator = AccelerateInterpolator()
//        childSet?.interpolator = BounceInterpolator()
        childSet?.interpolator = CycleInterpolator(5f)
        rotateX = ObjectAnimator.ofFloat(target_image, "rotationX",0.0f, 360.0f)
        rotateX?.duration=500

//        rootSet?.playSequentially(rotateX, childSet)
//        childSet?.playTogether(scaleX, scaleY)

        rootSet?.play(childSet)?.after(rotateX)
        childSet?.play(scaleX)?.with(scaleY)
        rootSet?.start()
    }

    private fun animateWithVPA(){
        vpa = target_image.animate()
        vpa?.duration = 500
        vpa?.rotationX(360f)
        vpa?.scaleX(2f)
        vpa?.scaleY(2f)
        vpa?.interpolator = OvershootInterpolator()
        vpa?.start()
    }

    private fun animateWithPVH(){
        scalexPropertyValuesHolder = PropertyValuesHolder.ofFloat( "scaleX", 2.0f)
        scaleYPropertyValuesHolder = PropertyValuesHolder.ofFloat( "scaleY", 2.0f)
        rotateXPropertyValuesHolder = PropertyValuesHolder.ofFloat("rotationX", 360f)
        pvhAnimator = ObjectAnimator.ofPropertyValuesHolder(target_image, scalexPropertyValuesHolder,scaleYPropertyValuesHolder,rotateXPropertyValuesHolder)
        pvhAnimator?.apply {
            duration = 500
            interpolator = BounceInterpolator()
            start()
            addListener(this@MainActivity)
        }
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
        if (animator==pvhAnimator)
            Toast.makeText(this,"PVHA Animation Started", Toast.LENGTH_SHORT).show()
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
        if (animator==pvhAnimator)
            Toast.makeText(this,"PVHA Animation Ended", Toast.LENGTH_SHORT).show()

    }
}
