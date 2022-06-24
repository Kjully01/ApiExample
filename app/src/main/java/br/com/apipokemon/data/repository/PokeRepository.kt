package br.com.apipokemon.data.repository

import br.com.apipokemon.data.api.Api
import br.com.apipokemon.data.model.PokeListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PokeRepository {

    private var pokeRoute = Api("https://pokeapi.co/").create()


    suspend fun getPoke(): Flow<PokeListResponse> {
        return flow {
            pokeRoute.getPoke()
                .let { response ->
                    if (response.isSuccessful) {
                        response.body()
                    } else {
                        throw HttpException(response)
                    }
                }?.let {
                    emit(it)
                }
        }
    }

}