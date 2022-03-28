package com.my.my_animation_example

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private val view by lazy { findViewById<FrameLayout>(R.id.container) }
    private val someObjectForLinear by lazy { findViewById<ImageView>(R.id.some_object_for_linear) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        //if (savedInstanceState == null) {
        //    supportFragmentManager.beginTransaction()
        //        .replace(R.id.container, MainFragment.newInstance())
        //        .commitNow()
        //}

        applyChangeBackgroundColorAnimation()

        applyChangeLinearTranslationAnimation()
    }

    private fun applyChangeBackgroundColorAnimation(){
        ValueAnimator.ofObject(ArgbEvaluator(), Color.RED, Color.GREEN).apply {
            //duration in millis of all animation
            duration = 30000
            addUpdateListener { updatedAnimation ->
                Log.d("DD", "updatedAnimation.animatedValue = ${updatedAnimation.animatedValue}")
                view.setBackgroundColor(updatedAnimation.animatedValue as Int)
            }
            start()
        }
    }

    private fun applyChangeLinearTranslationAnimation(){
        someObjectForLinear.setOnClickListener {
            Log.d("qq", "CLICK")
        }

        ValueAnimator.ofFloat(0f, 600f).apply {
            addUpdateListener { updatedAnimation ->
                someObjectForLinear.translationX = updatedAnimation.animatedValue as Float
            }

            duration = 10000
            start()
        }
    }
}