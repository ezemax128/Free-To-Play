package pumpkin.app.freeToPlay.presentation.UI.view

import android.os.Bundle
import android.view.MenuItem
import android.view.MenuItem.OnMenuItemClickListener
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import pumpkin.app.freeToPlay.R
import pumpkin.app.freeToPlay.presentation.UI.viewModel.MainViewModel
import pumpkin.app.freeToPlay.presentation.UI.viewModel.ViewFactory
import pumpkin.app.freeToPlay.presentation.UX.BaseAlert
import pumpkin.app.freeToPlay.data.model.BundleMaker
import pumpkin.app.freeToPlay.data.model.Game
import pumpkin.app.freeToPlay.data.model.Genres
import pumpkin.app.freeToPlay.databinding.FragmentHomeBinding
import pumpkin.app.freeToPlay.data.DataSource
import pumpkin.app.freeToPlay.data.repository.RepoImplementer
import pumpkin.app.freeToPlay.data.valueObject.AppDataBase
import pumpkin.app.freeToPlay.data.valueObject.Resourse


class homeFragment : Fragment(R.layout.fragment_home), RecyclerAdapter.onItemRecyclerClicked {
    private lateinit var binding: FragmentHomeBinding
    private val viewmodel by viewModels<MainViewModel> {
        ViewFactory(RepoImplementer(DataSource(AppDataBase.getDataBase(requireActivity().applicationContext))))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        //-------tool bar--------
        binding.toolbar.inflateMenu(R.menu.home_menu)
        binding.toolbar.setOnMenuItemClickListener(object : OnMenuItemClickListener,
            Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(p0: MenuItem?): Boolean {
                if (p0?.itemId == R.id.favoritesBtn) {
                    findNavController().navigate(R.id.action_homeFragment_to_favoritesGamesFragment)
                }
                return false
            }
        })

        //if the user touch the back button, then show the Aler Dialog.
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val view = layoutInflater.inflate(R.layout.alert_exit, null)
            val alert = BaseAlert.alertBase(requireContext(), view)
            val mesagge = alert.create()
            mesagge.show()
            val btnExit = view.findViewById<Button>(R.id.btnExit)
            val btnCancel = view.findViewById<Button>(R.id.btnDismiss)

            btnExit.setOnClickListener { requireActivity().finishAffinity() }
            btnCancel.setOnClickListener { mesagge.dismiss() }

        }
        binding.loadingLottie.setAnimation(R.raw.loading)
        setupListGenre()

    }


    //this function setting a list of genres from a enum class
    fun setupListGenre() {
        val listUI = binding.ListOfGenres
        val arrayAdapter: ArrayAdapter<*>
        val arrayGenres = mutableListOf<String>()
        for (i in Genres.values()) {
            arrayGenres.add(i._genre)
        }
        arrayAdapter =
            ArrayAdapter(requireContext(),
                R.layout.custom_item_spinner,
                arrayGenres)
        listUI.adapter = arrayAdapter

        listUI.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewmodel.setGenre(arrayGenres[p2])
                viewmodel.getByGenre.observe(requireActivity(), { result ->
                    when (result) {
                        is Resourse.Loading -> loading()
                        is Resourse.Success -> success(result)
                        is Resourse.Failure -> failure(result)
                    }
                })
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //nothing here
            }
        }
    }

    fun loading() {
        binding.loadingUI.visibility = View.VISIBLE
    }


    fun success(result: Resourse.Success<List<Game>>) {
        binding.loadingUI.visibility = View.GONE
        val recyclerUI = binding.MainRecycler
        val listGames = result.data
        val adapterRecycler =
            RecyclerAdapter(requireContext(), listGames, this@homeFragment)
        recyclerUI.layoutManager = LinearLayoutManager(requireContext())
        recyclerUI.adapter = adapterRecycler
        recyclerUI.addItemDecoration(DividerItemDecoration(requireContext(),
            DividerItemDecoration.VERTICAL))
    }


    fun failure(result: Resourse.Failure<List<Game>>) {
        binding.loadingUI.visibility = View.GONE
        Toast.makeText(requireContext(), "we have a problem ${result.exception}", Toast.LENGTH_LONG)
            .show()
    }

    override fun getDataFromClick(item: Game) {
        val bundle = BundleMaker.MakeBundle(item, "home")
        findNavController().navigate(R.id.action_homeFragment_to_gameDetailFragment, bundle)
    }


}