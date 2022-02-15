package pumpkin.app.freeToPlay.presentation.UI.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("thumbnail")
    val image: String = "",
    @SerializedName("short_description")
    val description: String = "",
    @SerializedName("game_url")
    val url: String = "",
    @SerializedName("genre")
    val genre: String = "",
    @SerializedName("publisher")
    val publisher: String = "",
    @SerializedName("developer")
    val developer: String = "",
    @SerializedName("release_date")
    val buildDate: String = "",
    @SerializedName("freetogame_profile_url")
    val moreInfo: String = ""
)

@Entity
data class GameEntity(
    @PrimaryKey()
    val id: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "thumbnail")
    val image: String = "",
    @ColumnInfo(name = "short_description")
    val description: String = "",
    @ColumnInfo(name = "game_url")
    val url: String = "",
    @ColumnInfo(name = "genre")
    val genre: String = "",
    @ColumnInfo(name = "publisher")
    val publisher: String = "",
    @ColumnInfo(name = "developer")
    val developer: String = "",
    @ColumnInfo(name = "release_date")
    val buildDate: String = "",
    @ColumnInfo(name = "freetogame_profile_url")
    val moreInfo: String = ""
)


