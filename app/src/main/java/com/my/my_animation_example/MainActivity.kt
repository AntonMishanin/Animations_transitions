package com.my.my_animation_example

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private val view by lazy { findViewById<FrameLayout>(R.id.container) }
    private val someObjectForLinear by lazy { findViewById<ImageView>(R.id.some_object_for_linear) }
    private val objectForRound by lazy { findViewById<ImageView>(R.id.some_object_for_round) }
    private val objectWithListOfImage by lazy { findViewById<ImageView>(R.id.some_list_of_image) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        //if (savedInstanceState == null) {
        //    supportFragmentManager.beginTransaction()
        //        .replace(R.id.container, MainFragment.newInstance())
        //        .commitNow()
        //}

        someObjectForLinear.setOnClickListener {
            crossFade()
        }
    }

    private fun crossFade() {
        val shortAnimationDuration =
            10 * resources.getInteger(android.R.integer.config_shortAnimTime)

        someObjectForLinear.apply {

            alpha = 0f
            visibility = View.VISIBLE

            animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }

        objectForRound.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    objectForRound.visibility = View.GONE
                }
            })
    }
}