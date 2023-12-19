package com.dreammkr.pokedex.ui.view

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.dreammkr.pokedex.R
import com.dreammkr.pokedex.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var detector: GestureDetectorCompat
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detector = GestureDetectorCompat(this, DiaryGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (detector.onTouchEvent(event!!)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    inner class DiaryGestureListener : GestureDetector.SimpleOnGestureListener() {

        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val diffX = e1!!.x.minus(e2.x)
            return if (abs(diffX) > SWIPE_THRESHOLD &&
                abs(velocityX) > SWIPE_VELOCITY_THRESHOLD
            ) {
                if (diffX > 0) {
                    // right swipe
                    this@MainActivity.onSwipeRight()
                } else {
                    // left swipe.
                    this@MainActivity.onLeftSwipe()
                }
                true
            } else {
                super.onFling(e1, e2, velocityX, velocityY)
            }
        }
    }

    internal fun onLeftSwipe() {
        navController = findNavController(R.id.fragmentContainerView)
        Navigation.setViewNavController(binding.root, navController)
        if (navController.currentDestination?.id == R.id.catchFragment) {
            navController.navigate(R.id.action_catchFragment_to_detailFragment)
        }
    }

    internal fun onSwipeRight() {
        navController = findNavController(R.id.fragmentContainerView)
        Navigation.setViewNavController(binding.root, navController)
        if (navController.currentDestination?.id == R.id.detailFragment) {
            navController.navigate(R.id.action_detailFragment_to_catchFragment)
        }
    }

}