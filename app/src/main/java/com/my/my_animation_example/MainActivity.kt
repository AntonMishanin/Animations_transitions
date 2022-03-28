package com.my.my_animation_example

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.core.graphics.toColor
import com.my.my_animation_example.ui.main.MainFragment
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private val view by lazy { findViewById<FrameLayout>(R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        //if (savedInstanceState == null) {
        //    supportFragmentManager.beginTransaction()
        //        .replace(R.id.container, MainFragment.newInstance())
        //        .commitNow()
        //}

        ValueAnimator.ofObject(ArgbEvaluator(), Color.RED, Color.GREEN).apply {
            //this duration is not about time
            duration = 30000
            addUpdateListener { updatedAnimation ->
                Log.d("DD", "updatedAnimation.animatedValue = ${updatedAnimation.animatedValue}")
                view.setBackgroundColor(updatedAnimation.animatedValue as Int)
            }
            start()
        }
    }
}