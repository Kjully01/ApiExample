package br.com.apipokemon.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.apipokemon.data.model.PokeListResponse
import br.com.apipokemon.data.repository.PokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokeViewModel: ViewModel() {

    private val repository: PokeRepository = PokeRepository()

    private val _pokeSuccess: MutableLiveData<PokeListResponse> = MutableLiveData()
    val pokeSuccess: LiveData<PokeListResponse> = _pokeSuccess

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getPoke(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPoke()
                .catch { err ->
                    _error.postValue(err.message)
                }.collect {
                    _pokeSuccess.postValue(it)
                }
        }
    }

}