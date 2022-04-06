package com.arunava.example.expandablechevrondemo

import android.os.Bundle
import android.widget.ImageView
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

        binding.topPanelArrow.setOnClickListener {
            onArrowClick()
        }
        binding.bottomPanelArrow.setOnClickListener {
            onArrowClick()
        }
    }

    private fun onArrowClick() {
        when (openedPanel) {
            Panels.TOP -> {
                openedPanel = Panels.BOTTOM
                TransitionManager.beginDelayedTransition(binding.topPanel, autoTransition)
                TransitionManager.beginDelayedTransition(binding.bottomPanel, autoTransition)
                binding.apply {
                    topPanelFixed.isVisible = true
                    topPanelExpandable.isVisible = false
                    bottomPanelFixed.isVisible = false
                    bottomPanelExpandable.isVisible = true
                }
                rotateArrowBy(binding.topPanelArrow, 180f)
                rotateArrowBy(binding.bottomPanelArrow, -180f)
            }
            Panels.BOTTOM -> {
                openedPanel = Panels.TOP
                TransitionManager.beginDelayedTransition(binding.topPanel, autoTransition)
                TransitionManager.beginDelayedTransition(binding.bottomPanel, autoTransition)
                binding.apply {
                    topPanelFixed.isVisible = false
                    topPanelExpandable.isVisible = true
                    bottomPanelFixed.isVisible = true
                    bottomPanelExpandable.isVisible = false
                }
                rotateArrowBy(binding.topPanelArrow, -180f)
                rotateArrowBy(binding.bottomPanelArrow, 180f)
            }
        }
    }

    private fun rotateArrowBy(arrow: ImageView, value: Float) {
        arrow.animate()
            .rotationBy(value)
            .setDuration(ANIM_DURATION)
            .start()
    }
}

enum class Panels {
    TOP, BOTTOM
}

const val ANIM_DURATION = 200L