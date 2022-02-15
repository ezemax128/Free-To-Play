package pumpkin.app.freeToPlay.presentation.UI.view

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pumpkin.app.freeToPlay.R
import pumpkin.app.freeToPlay.databinding.FragmentMyBrandBinding


class myBrandFragment : Fragment(R.layout.fragment_my_brand) {
    private lateinit var binding: FragmentMyBrandBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyBrandBinding.bind(view)
        binding.introLotie.setAnimation(R.raw.calabaza_intro)
        binding.introLotie.playAnimation()
        //go back Controller
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
        }
        timer()
    }

    fun timer() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
                //Nothing here
            }

            override fun onFinish() {
                findNavController().navigate(R.id.action_myBrandFragment_to_homeFragment)
            }

        }.start()
    }


}