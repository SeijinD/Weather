package eu.seijindemon.weather.data.repository

import eu.seijindemon.weather.data.model.Weather
import eu.seijindemon.weather.data.remote.ApiService
import eu.seijindemon.weather.data.remote.RetrofitInstance
import retrofit2.Response

class WeatherRepository () {

    suspend fun getWeather(city: String): Response<Weather> {
        return RetrofitInstance.apiService.getWeather(city)
    }

}