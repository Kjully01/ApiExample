package br.com.apipokemon.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.apipokemon.data.model.PokeResponse
import br.com.apipokemon.databinding.FragmentHomeBinding
import br.com.apipokemon.view.adapter.PokeAdapter
import br.com.apipokemon.viewModel.PokeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterPoke : PokeAdapter
    private lateinit var viewModel: PokeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PokeViewModel::class.java)
        viewModel.getPoke()

        observer()
        startAdapter()
    }

    private fun startAdapter() {
        adapterPoke = PokeAdapter()
        binding.rvPoke.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterPoke
        }
    }

    private fun setDataAdapter(animalsList: List<PokeResponse>){
        adapterPoke.setData(animalsList)
    }

    private fun observer() {
        viewModel.apply {
            pokeSuccess.observe(viewLifecycleOwner, Observer{ animalResponse ->
                val pokeList = animalResponse.pokeResponse
                setDataAdapter(pokeList)
            })
            error.observe(
                viewLifecycleOwner, Observer {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}