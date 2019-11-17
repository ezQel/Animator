package com.ezqel.animator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import kotlinx.android.synthetic.main.activity_layout_transitions.*

class LayoutTransitionsActivity : AppCompatActivity() {
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
        transition = TransitionInflater.from(this).inflateTransition(R.transition.auto_transition)

        sceneLayout.setOnClickListener{
            sceneTransition()
        }
    }

    private fun sceneTransition() {
        currentScene = if (currentScene === sceneOne){
            TransitionManager.go(sceneTwo, transition)
            sceneTwo
        } else {
            TransitionManager.go(sceneOne, transition)
            sceneOne
        }


    }
}
