package com.ezqel.animator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.view.animation.BounceInterpolator
import kotlinx.android.synthetic.main.activity_layout_transitions.*

class LayoutTransitionsActivity : AppCompatActivity() {
    lateinit var transitionSet: TransitionSet
    lateinit var fadeInTransition: Fade
    lateinit var changeBoundsTransition: ChangeBounds
    lateinit var sceneOne:Scene
    lateinit var sceneTwo:Scene
    lateinit var currentScene:Scene
    lateinit var transition: Transition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_transitions)

        // create scene object for both starting and ending layout
        sceneOne = Scene.getSceneForLayout(sceneLayout, R.layout.scene_one,this)
        sceneTwo = Scene.getSceneForLayout(sceneLayout, R.layout.scene_two,this)

        sceneOne.enter()
        currentScene = sceneOne

        // create a transition object to define transition of choice
        changeBoundsTransition = ChangeBounds()
        changeBoundsTransition.duration = 500
        changeBoundsTransition.interpolator = BounceInterpolator()
        fadeInTransition = Fade(Fade.MODE_IN)
        fadeInTransition.startDelay = 500
        fadeInTransition.duration = 2000
        fadeInTransition.addTarget(R.id.disclaimer)
        fadeInTransition.addTarget(R.id.disclaimer_text)

        transitionSet = TransitionSet()
        transitionSet.ordering = TransitionSet.ORDERING_SEQUENTIAL
        transitionSet.addTransition(changeBoundsTransition)
        transitionSet.addTransition(fadeInTransition)
//        transition = TransitionInflater.from(this).inflateTransition(R.transition.example_0)

        sceneLayout.setOnClickListener{
            sceneTransition()
        }
    }

    private fun sceneTransition() {
        currentScene = if (currentScene === sceneOne){
            TransitionManager.go(sceneTwo, transitionSet)
            sceneTwo
        } else {
            TransitionManager.go(sceneOne, transitionSet)
            sceneOne
        }


    }
}
