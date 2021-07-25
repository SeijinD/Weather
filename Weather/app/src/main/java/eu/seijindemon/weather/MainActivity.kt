package eu.seijindemon.weather

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.seijindemon.weather.data.repository.WeatherRepository
import eu.seijindemon.weather.data.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val weatherRepository: WeatherRepository by lazy { WeatherRepository() }
    private val weatherViewModel: WeatherViewModel by lazy { WeatherViewModel(weatherRepository) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_enter.setOnClickListener {
            val cityName = tie_city_name.text.toString()

            if(cityName.isNotBlank()) {
                weatherViewModel.getWeather(cityName)

                weatherViewModel.weather.observe(this, { weather ->
                    tv_city_name.text = cityName
                    tv_description.text = weather.description
                    tv_temperature.text  = weather.temperature
                    tv_wind.text = weather.wind

                    val forecast = weather.forecast
                    tv_forecast1.text = "${forecast[0].temperature} / ${forecast[0].wind}"
                    tv_forecast2.text = "${forecast[1].temperature} / ${forecast[1].wind}"
                    tv_forecast3.text = "${forecast[2].temperature} / ${forecast[2].wind}"
                })



            }
        }
    }
}