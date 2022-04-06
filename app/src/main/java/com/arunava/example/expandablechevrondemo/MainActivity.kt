package com.arunava.example.expandablechevrondemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.arunava.example.expandablechevrondemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var openedPanel = Panels.TOP
    private val autoTransition = AutoTransition().apply { duration = ANIM_DURATION }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.arrow.setOnClickListener {
            when (openedPanel) {
                Panels.TOP -> {
                    openedPanel = Panels.BOTTOM
                    TransitionManager.beginDelayedTransition(binding.topPanel, autoTransition)
                    binding.contentFrame.isVisible = false
                    binding.collapsedGroup.isVisible = true
                    rotateArrowBy(180f)
                }
                Panels.BOTTOM -> {
                    openedPanel = Panels.TOP
                    TransitionManager.beginDelayedTransition(binding.topPanel, autoTransition)
                    binding.contentFrame.isVisible = true
                    binding.collapsedGroup.isVisible = false
                    rotateArrowBy(-180f)
                }
            }
        }
    }

    private fun rotateArrowBy(value: Float) {
        binding.arrow.animate()
            .rotationBy(value)
            .setDuration(ANIM_DURATION)
            .start()
    }
}

enum class Panels {
    TOP, BOTTOM
}

const val ANIM_DURATION = 200L