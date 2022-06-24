package br.com.apipokemon.data.model

import com.google.gson.annotations.SerializedName

class PokeResponse (
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)