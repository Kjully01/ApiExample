package br.com.apipokemon.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.apipokemon.data.model.PokeResponse
import br.com.apipokemon.databinding.LayoutPokeAdapterBinding
import coil.load

class PokeAdapter : RecyclerView.Adapter<PokeAdapter.ViewHolderPoke>() {

    private var pokeResponseList: MutableList<PokeResponse> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPoke {
        val itemBinding = LayoutPokeAdapterBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolderPoke(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolderPoke, position: Int) {
        holder.onBind(pokeResponseList[position])
    }

    override fun getItemCount(): Int = pokeResponseList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listAux: List<PokeResponse>) {
        pokeResponseList.clear()
        pokeResponseList.addAll(listAux)
        notifyDataSetChanged()
    }

    class ViewHolderPoke(private val binding: LayoutPokeAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun onBind(pokeResponse: PokeResponse){
                binding.apply {
                    val image = "https://img.pokemondb.net/artwork/large/" + pokeResponse.name + ".jpg"
                    imageItem.imageItem.load(image)
                    tvName.text = pokeResponse.name
                }
            }
    }
}