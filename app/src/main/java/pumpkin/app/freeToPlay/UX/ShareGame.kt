package pumpkin.app.freeToPlay.UX

import android.content.Context
import android.content.Intent
import android.widget.Toast

class ShareGame {
    companion object{
        fun share(url: String, context: Context){
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val uriString = "Hey! look at this game! $url"
            intent.putExtra(Intent.EXTRA_TEXT, uriString)
            try {
                context.startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "something went wrong $e", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}