package pumpkin.app.freeToPlay.presentation.UI.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pumpkin.app.freeToPlay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }



}