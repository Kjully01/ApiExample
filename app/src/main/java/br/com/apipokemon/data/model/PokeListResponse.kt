package br.com.apipokemon.data.model

import com.google.gson.annotations.SerializedName

data class PokeListResponse (
    @SerializedName("results")
    val pokeResponse: List<PokeResponse>
)