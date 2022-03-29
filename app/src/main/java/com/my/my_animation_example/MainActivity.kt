package com.my.my_animation_example

import android.animation.*
import android.graphics.Color
import android.graphics.Path
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private val view by lazy { findViewById<FrameLayout>(R.id.container) }
    private val someObjectForLinear by lazy { findViewById<ImageView>(R.id.some_object_for_linear) }
    private val objectForRound by lazy { findViewById<ImageView>(R.id.some_object_for_round) }
    private val objectWithListOfImage by lazy { findViewById<ImageView>(R.id.some_list_of_image) }
    private val moveByPath by lazy { findViewById<ImageView>(R.id.move_by_path) }

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

        applyObjectAnimByCircle()

        objectWithListOfImage.setBackgroundResource(R.drawable.list_of_some_drawables)
        val listOfAnim = objectWithListOfImage.background as AnimationDrawable
        objectWithListOfImage.setOnClickListener {
            listOfAnim.start()
        }

        moveByPath.setOnClickListener {
            moveViewByCustomPath()
        }
    }

    private fun applyChangeBackgroundColorAnimation() {
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

    private fun applyChangeLinearTranslationAnimation() {
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

    private fun applyObjectAnimByCircle() {
        val path = Path()
        path.addCircle(100f, 400f, 200f, Path.Direction.CW)

        objectForRound.setOnClickListener {
            Log.d("ww", "CLICK CIRCLE")
        }

        ObjectAnimator.ofFloat(objectForRound, "translationX", "translationY", path).apply {
            duration = 15000
            start()
        }
    }

    private fun moveViewByCustomPath() {
        val path = Path().apply {
            arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true)
        }
        ObjectAnimator.ofFloat(moveByPath, View.X, View.Y, path).apply {
            duration = 2000
            start()
        }
    }
}