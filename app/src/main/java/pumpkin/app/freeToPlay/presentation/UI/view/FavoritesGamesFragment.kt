package pumpkin.app.freeToPlay.presentation.UI.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pumpkin.app.freeToPlay.R
import pumpkin.app.freeToPlay.presentation.UI.viewModel.MainViewModel
import pumpkin.app.freeToPlay.presentation.UI.viewModel.ViewFactory
import pumpkin.app.freeToPlay.presentation.UI.model.BundleMaker
import pumpkin.app.freeToPlay.presentation.UI.model.Game
import pumpkin.app.freeToPlay.presentation.UI.model.GameEntity
import pumpkin.app.freeToPlay.databinding.FragmentFavoritesGamesBinding
import pumpkin.app.freeToPlay.data.DataSource
import pumpkin.app.freeToPlay.presentation.UI.ItemSwipeRemove
import pumpkin.app.freeToPlay.data.repository.RepoImplementer
import pumpkin.app.freeToPlay.data.valueObject.AppDataBase
import pumpkin.app.freeToPlay.data.valueObject.Resourse


class FavoritesGamesFragment : Fragment(R.layout.fragment_favorites_games),
    RecyclerAdapter.onItemRecyclerClicked {
    private lateinit var binding: FragmentFavoritesGamesBinding
    private val viewmodel by viewModels<MainViewModel>() {
        ViewFactory(RepoImplementer(DataSource(AppDataBase.getDataBase(requireActivity().applicationContext))))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesGamesBinding.bind(view)
        binding.emptyLoti.setAnimation(R.raw.empty)
        setupRecycler()
    }


    fun setupRecycler() {
        viewmodel.getFavorites().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                //if a result is Loading...
                is Resourse.Loading -> {
                    loading()
                }
                //if result is a Success
                is Resourse.Success -> {
                    success(result)

                }
                //if result is a Failure...
                is Resourse.Failure -> {
                    failure()
                }
            }
        })

    }

    fun loading() {
        binding.loadingUI.visibility = View.VISIBLE
    }

    fun success(result: Resourse.Success<List<GameEntity>>) {
        val recyclerUI = binding.recyclerFavorites
        recyclerUI.layoutManager = LinearLayoutManager(requireContext())
        binding.loadingUI.visibility = View.GONE
        val listaFavorites: MutableList<Game> = result.data.map {
            Game(it.id,
                it.title,
                it.image,
                it.description,
                it.url,
                it.genre,
                it.publisher,
                it.developer,
                it.buildDate,
                it.moreInfo
            )
        }.toMutableList()
        if (listaFavorites.isEmpty()) binding.empty.visibility =
            View.VISIBLE else binding.empty.visibility = View.GONE

        var adapter = RecyclerAdapter(requireContext(), listaFavorites, this)
        recyclerUI.adapter = adapter

        //Drag to delete
        val itemSwipeRemove = object : ItemSwipeRemove() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = listaFavorites[position].id

                Toast.makeText(requireContext(),
                    "${listaFavorites[position].title}, was deleted from Favorites List",
                    Toast.LENGTH_LONG).show()

                listaFavorites.removeAt(position)
                viewmodel.deletefavorite(item)
                recyclerUI.adapter?.notifyItemRemoved(position)
                adapter = RecyclerAdapter(requireContext(),
                    listaFavorites,
                    this@FavoritesGamesFragment)
                recyclerUI.adapter = adapter
                if (listaFavorites.isEmpty()) binding.empty.visibility =
                    View.VISIBLE else binding.empty.visibility = View.GONE
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemSwipeRemove)
        itemTouchHelper.attachToRecyclerView(recyclerUI)
    }

    fun failure() {
        binding.loadingUI.visibility = View.GONE
        Toast.makeText(requireContext(), "we have a problem", Toast.LENGTH_LONG).show()
    }

    override fun getDataFromClick(item: Game) {
        val arguments = BundleMaker.MakeBundle(item, "favorites")
        findNavController().navigate(R.id.action_favoritesGamesFragment_to_gameDetailFragment,
            arguments)
    }


}