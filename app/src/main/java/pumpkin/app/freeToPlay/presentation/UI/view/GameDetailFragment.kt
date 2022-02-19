package pumpkin.app.freeToPlay.presentation.UI.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import pumpkin.app.freeToPlay.R
import pumpkin.app.freeToPlay.presentation.UI.viewModel.MainViewModel
import pumpkin.app.freeToPlay.presentation.UI.viewModel.ViewFactory
import pumpkin.app.freeToPlay.presentation.UX.ShareGame
import pumpkin.app.freeToPlay.data.model.GameEntity
import pumpkin.app.freeToPlay.databinding.FragmentGameDetailBinding
import pumpkin.app.freeToPlay.data.DataSource
import pumpkin.app.freeToPlay.data.repository.RepoImplementer
import pumpkin.app.freeToPlay.data.valueObject.AppDataBase

class GameDetailFragment : Fragment(R.layout.fragment_game_detail) {
    private lateinit var binding: FragmentGameDetailBinding
    private val viewModel by viewModels<MainViewModel>() {
        ViewFactory(RepoImplementer(DataSource(AppDataBase.getDataBase(requireActivity().applicationContext))))
    }

    //variables:
    private lateinit var id: String
    private lateinit var name: String
    private lateinit var description: String
    private lateinit var image: String
    private lateinit var developer: String
    private lateinit var genre: String
    private lateinit var publisher: String
    private lateinit var date: String
    private lateinit var info: String
    private lateinit var url: String
    private lateinit var origen: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameDetailBinding.bind(view)
        binding.shareBtnLottie.setAnimation(R.raw.share)
        binding.favoritesLottie.setAnimation(R.raw.starfavorite)
        checker()
        setupView()
        share()
        btnfavorites()
        bindingData()
    }


    fun setupView() {
        requireArguments().let { argument ->
            id = argument.getString("id").toString()
            name = argument.getString("name").toString()
            description = argument.getString("description").toString()
            image = argument.getString("image").toString()
            developer = argument.getString("developer").toString()
            genre = argument.getString("genre").toString()
            publisher = argument.getString("publisher").toString()
            date = argument.getString("date").toString()
            info = argument.getString("info").toString()
            url = argument.getString("url").toString()
            origen = argument.getString("origen").toString()
        }
    }

    fun bindingData() {
        binding.GameNameDetail.text = name
        binding.GameimageDetail.load(image)
        binding.GameDescriptionDetail.text = description
        binding.GameDateDetail.text = date
        binding.GameDeveloperDetail.text = developer
        binding.GameGenreDetail.text = genre
        binding.GameUrlDetail.text = url
        binding.GameMoreDetail.text = info
        binding.GamePublisherDetail.text = publisher
    }

    fun share() {
        binding.shareBtnLottie.setOnClickListener {
           ShareGame.share(url, requireContext())
        }
    }

    fun btnfavorites() {
        val btnFavorites = binding.favoritesLottie
        if (origen == "home") {
            btnFavorites.isVisible = true
            btnFavorites.setOnClickListener {
                btnFavorites.playAnimation()
                viewModel.saveGame(GameEntity(
                    id, name, image, description, url, genre, publisher, developer, date, info
                ))
                btnFavorites.isClickable = false
                Toast.makeText(requireContext(),
                    "$name was saved into Favorites",
                    Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            btnFavorites.isVisible = false
        }
    }

    fun checker(){
        viewModel.getAllId().observe(viewLifecycleOwner, Observer {
           val check: List<String> = it as List<String>
            if (check.contains(id)) binding.favoritesLottie.isVisible = false

        })
    }

}