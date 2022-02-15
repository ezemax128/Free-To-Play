package pumpkin.app.freeToPlay.presentation.UI.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pumpkin.app.freeToPlay.presentation.UI.model.GameEntity
import pumpkin.app.freeToPlay.data.repository.Repository
import pumpkin.app.freeToPlay.data.valueObject.Resourse
import java.lang.Exception

class MainViewModel(val repository: Repository) : ViewModel() {

    //value for diferent genres
    private val genre = MutableLiveData<String>()


    //set genre requiered
    fun setGenre(_genre: String) {
        genre.value = _genre
    }

    init {
        setGenre("Shooter")
    }

    //this variable gets the game list sort by genre
    val getByGenre = genre.distinctUntilChanged().switchMap { result ->
        liveData(Dispatchers.IO) {
            emit(Resourse.Loading())
            try {
                emit(repository.getListByGenre(result))
            } catch (e: Exception) {
                emit(Resourse.Failure(e))
            }
        }
    }

    //functions to manipulate Room Database

    fun saveGame(gameEntity: GameEntity){
        viewModelScope.launch {
            repository.SaveFavoriteGame(gameEntity)
        }
    }

    fun getFavorites() = liveData(Dispatchers.IO){
        emit(Resourse.Loading())
        try {
            emit(repository.getFavorites())
        }catch (e: Exception){
            emit(Resourse.Failure(e))
        }
    }

    fun deletefavorite(id: String){
        viewModelScope.launch {
            repository.deleteGame(id)
        }
    }

    fun getAllId() = liveData(Dispatchers.IO) {
        try {
            emit(repository.getIdList())
        }catch (e:Exception){
            emit(e)
        }
    }

}