package com.example.instantweather.ui.searchdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import com.example.instantweather.R
import com.example.instantweather.databinding.FragmentSearchDetailBinding
import com.example.instantweather.ui.MainActivity
import com.example.instantweather.utils.convertKelvinToCelsius
import timber.log.Timber

class SearchDetailFragment : Fragment() {

    private lateinit var viewModel: SearchDetailViewModel
    private lateinit var binding: FragmentSearchDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchDetailBinding.inflate(layoutInflater)
        val searchDetailFragmentArgs by navArgs<SearchDetailFragmentArgs>()
        val weather = searchDetailFragmentArgs.searchWeatherResult
        val location = searchDetailFragmentArgs.location
        if (weather != null && location != null){
            val kelvinValue = weather.networkWeatherCondition.temp
            weather.networkWeatherCondition.temp = convertKelvinToCelsius(kelvinValue)
            binding.weather = weather
            binding.weatherCondition = weather.networkWeatherDescription.first()
            binding.location.text = location
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchDetailViewModel::class.java)
        binding.fabClose.setOnClickListener{
            (activity as MainActivity).onBackPressed()
        }
    }

}
