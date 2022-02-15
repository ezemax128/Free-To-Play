package pumpkin.app.freeToPlay.presentation.UI.model

import android.os.Bundle

//this class can make a bundle from a item type Game and return it.
class BundleMaker {
    companion object{

        fun MakeBundle(item: Game, origen: String): Bundle{
            val bundle = Bundle()
            bundle.putString("id", item.id)
            bundle.putString("name", item.title)
            bundle.putString("image", item.image)
            bundle.putString("description", item.description)
            bundle.putString("developer", item.developer)
            bundle.putString("genre", item.genre)
            bundle.putString("publisher", item.publisher)
            bundle.putString("date", item.buildDate)
            bundle.putString("info", item.moreInfo)
            bundle.putString("url", item.url)
            bundle.putString("origen", origen)
            return bundle
        }
    }
}