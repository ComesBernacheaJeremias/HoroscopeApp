package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentHoroscopoBinding
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.ui.horoscope.adapter.HoroscopoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopoFragment : Fragment() {
    private val horoscopoViewModel by viewModels<HoroscopoViewModel>()

    private lateinit var horoscopoAdapter: HoroscopoAdapter

    private var _binding: FragmentHoroscopoBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initUIState()
        initRecyclerView()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopoViewModel.horoscope.collect {
                    horoscopoAdapter.updateList(it)
                }
            }
        }
    }

    private fun initRecyclerView() {
        horoscopoAdapter = HoroscopoAdapter(onItemSelected = {
            val type = when(it){
                HoroscopeInfo.Aquario -> HoroscopeModel.Aquario
                HoroscopeInfo.Aries -> HoroscopeModel.Aries
                HoroscopeInfo.Cancer -> HoroscopeModel.Cancer
                HoroscopeInfo.Capricornio -> HoroscopeModel.Capricornio
                HoroscopeInfo.Escorpio -> HoroscopeModel.Escorpio
                HoroscopeInfo.Geminis -> HoroscopeModel.Geminis
                HoroscopeInfo.Leo -> HoroscopeModel.Leo
                HoroscopeInfo.Libra -> HoroscopeModel.Libra
                HoroscopeInfo.Piscis -> HoroscopeModel.Piscis
                HoroscopeInfo.Sagitario -> HoroscopeModel.Sagitario
                HoroscopeInfo.Tauro -> HoroscopeModel.Tauro
                HoroscopeInfo.Virgo -> HoroscopeModel.Virgo
            }
            findNavController().navigate(
                HoroscopoFragmentDirections.actionHoroscopoFragmentToHoroscopeDetailActivity(type)
            )
        })
        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopoAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopoBinding.inflate(layoutInflater, container, false)
        return (binding.root)
    }
}


