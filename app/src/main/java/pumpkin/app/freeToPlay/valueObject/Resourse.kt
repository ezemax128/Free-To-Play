package pumpkin.app.freeToPlay.valueObject

import pumpkin.app.freeToPlay.data.model.Game
import java.lang.Exception

//this sealed class return a Resourse that can be a (Loading, Success or Failure state)
sealed class Resourse<out T>{
    class Loading<out T>: Resourse<T>()
    data class Success<out T>(val data: T): Resourse<T>()
    data class Failure<out T>(val exception: Exception): Resourse<T>()
}
