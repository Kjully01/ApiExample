package br.com.apipokemon.data.api

import br.com.apipokemon.data.model.PokeListResponse
import retrofit2.Response
import retrofit2.http.GET

interface RoutesApi {

    @GET("/api/v2/pokemon/?limit=20")
    suspend fun getPoke(): Response<PokeListResponse>
}