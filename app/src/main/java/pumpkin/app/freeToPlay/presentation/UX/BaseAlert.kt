package pumpkin.app.freeToPlay.presentation.UX

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import pumpkin.app.freeToPlay.R

class BaseAlert {
    companion object{
        fun alertBase(context: Context, view: View): AlertDialog.Builder {
            val alert = AlertDialog.Builder(context)
            alert.setView(view)
            view.findViewById<LottieAnimationView>(R.id.exitLotie).setAnimation(R.raw.exit)
            alert.setTitle("Game Over?")
            return alert
        }
    }
}