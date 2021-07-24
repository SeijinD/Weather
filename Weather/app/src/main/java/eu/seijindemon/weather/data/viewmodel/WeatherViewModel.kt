package eu.seijindemon.weather.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.seijindemon.weather.data.model.Weather
import eu.seijindemon.weather.data.repository.WeatherRepository
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class WeatherViewModel (private val repository: WeatherRepository): ViewModel() {

    val weather = MutableLiveData<Weather>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getWeather(city: String) {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = repository.getWeather(city)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        weather.value = it
                    }
                } else {
                    Log.d("Response", response.errorBody().toString())
                }
            }
        }
    }

}

class WaifuViewModelFactory(private val repository: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}