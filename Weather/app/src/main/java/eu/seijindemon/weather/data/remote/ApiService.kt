package eu.seijindemon.weather.data.remote

import eu.seijindemon.weather.data.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("weather/{city}")
    suspend fun getWeather(@Path("city") city: String): Response<Weather>

}