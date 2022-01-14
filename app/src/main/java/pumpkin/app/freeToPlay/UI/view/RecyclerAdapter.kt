package pumpkin.app.freeToPlay.UI.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.invoke
import pumpkin.app.freeToPlay.R
import pumpkin.app.freeToPlay.data.model.Game

class RecyclerAdapter(
    val context: Context,
    val gameList: List<Game>,
    val clickDetector: onItemRecyclerClicked,
) : RecyclerView.Adapter<RecyclerAdapter.GameViewHolder>() {

    interface onItemRecyclerClicked {
        fun getDataFromClick(item: Game)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_row, parent, false))
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(gameList[position])

    }

    override fun getItemCount(): Int = gameList.size

    inner class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val infoView = view
        fun bind(item: Game) {
            itemView.findViewById<ImageView>(R.id.GameImage).load(item.image)
            itemView.findViewById<TextView>(R.id.GameNameTxt).text = item.title
            itemView.findViewById<TextView>(R.id.GameDescriptionTxt).text = item.description
            itemView.findViewById<TextView>(R.id.GameGenreTxt).text = item.genre
            itemView.findViewById<TextView>(R.id.GamePublisherTxt).text = item.publisher
            infoView.setOnClickListener {
                clickDetector.getDataFromClick(item)
            }


        }
    }

}