package pumpkin.app.freeToPlay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pumpkin.app.freeToPlay.UI.viewModel.MainViewModel
import pumpkin.app.freeToPlay.UI.viewModel.ViewFactory
import pumpkin.app.freeToPlay.data.model.Game
import pumpkin.app.freeToPlay.data.model.Genres
import pumpkin.app.freeToPlay.databinding.ActivityMainBinding
import pumpkin.app.freeToPlay.domain.DataSource
import pumpkin.app.freeToPlay.domain.RepoImplementer
import pumpkin.app.freeToPlay.domain.Repository
import pumpkin.app.freeToPlay.valueObject.Resourse

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }



}