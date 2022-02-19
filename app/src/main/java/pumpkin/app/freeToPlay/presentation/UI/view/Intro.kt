package pumpkin.app.freeToPlay.presentation.UI.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class Intro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timer()
    }

    fun timer(){
        object: CountDownTimer(3000, 1000){
            override fun onTick(p0: Long) {
                //nothing to do
            }

            override fun onFinish() {
                startActivity(Intent(this@Intro, MainActivity::class.java)).apply {  }
            }

        }.start()
    }
}