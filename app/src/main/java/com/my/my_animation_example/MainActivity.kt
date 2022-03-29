package com.my.my_animation_example

import android.animation.*
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val view by lazy { findViewById<FrameLayout>(R.id.container) }
    private val someObjectForLinear by lazy { findViewById<ImageView>(R.id.some_object_for_linear) }
    private val objectForRound by lazy { findViewById<ImageView>(R.id.some_object_for_round) }
    private val objectWithListOfImage by lazy { findViewById<ImageView>(R.id.some_list_of_image) }

    private val cardFront by lazy { findViewById<ImageView>(R.id.card_front) }
    private val cardBack by lazy { findViewById<ImageView>(R.id.card_back) }

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

        cardFront.setOnClickListener {
            flipCard(applicationContext, visibleView = cardBack, invisibleView = cardFront)
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

    private fun flipCard(context: Context, visibleView: View, invisibleView: View) {
        try {
            visibleView.isVisible = true
            val scale = context.resources.displayMetrics.density
            val cameraDist = 8000 * scale
            visibleView.cameraDistance = cameraDist
            invisibleView.cameraDistance = cameraDist
            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_out
                ) as AnimatorSet
            flipOutAnimatorSet.setTarget(invisibleView)
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_in
                ) as AnimatorSet
            flipInAnimatorSet.setTarget(visibleView)
            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()
            flipInAnimatorSet.doOnEnd {
                invisibleView.isVisible = false
            }
        } catch (e: Exception) {
            print(e.message)
        }
    }
}