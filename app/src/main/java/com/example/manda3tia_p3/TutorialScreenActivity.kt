package com.example.manda3tia_p3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.manda3tia_p3.Tutorial.FragmentTutorial1
import com.example.manda3tia_p3.Tutorial.FragmentTutorial2
import com.example.manda3tia_p3.Tutorial.FragmentTutorial3
import com.example.manda3tia_p3.Tutorial.TutorialFragmentAdapter
import com.example.manda3tia_p3.databinding.ActivityTutorialScreenBinding

class TutorialScreenActivity : AppCompatActivity() {
   private lateinit var binding: ActivityTutorialScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTutorialScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fragmentsList = listOf(
            FragmentTutorial1(),
            FragmentTutorial2(),
            FragmentTutorial3()
        )
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialMessageViewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)
    }
}