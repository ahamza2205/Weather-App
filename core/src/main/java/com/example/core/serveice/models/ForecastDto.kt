import android.os.Parcelable
import com.example.core.serveice.models.Current
import com.example.core.serveice.models.Location
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeather(
    val country: String,
    val city: String,
    val temperature: Int,
    val condition: String,
    val iconUrl: String,
    val localTime: String,
    val humidity: Int,
    val wind_kph: Int
) : Parcelable

@Parcelize
data class Forecast(
    val forecastDays: List<ForecastDay>
) : Parcelable

@Parcelize
data class ForecastDay(
    val date: String,
    val conditionText: String,
    val conditionIcon: String,
    val minTemp: Float,
    val maxTemp: Float
) : Parcelable

@Parcelize
data class CurrentWeatherDto(
    val location: Location? = null,
    val current: Current? = null
) : Parcelable

@Parcelize
data class ForecastDto(
    val forecast: ForecastData? = null
) : Parcelable

@Parcelize
data class ForecastData(
    val forecastday: List<ForecastdayItem>? = null
) : Parcelable

@Parcelize
data class ForecastdayItem(
    val date: String? = null,
    val day: Day? = null
) : Parcelable

@Parcelize
data class Day(
    val mintemp_c: Float? = null,
    val maxtemp_c: Float? = null,
    val condition: Condition? = null
) : Parcelable

@Parcelize
data class Condition(
    val text: String? = null,
    val icon: String? = null
) : Parcelable

@Parcelize
data class Location(
    val name: String? = null,
    val country: String? = null,
    val localtime: String? = null
) : Parcelable

@Parcelize
data class Current(
    val temp_c: Float? = null,
    val condition: Condition? = null,
    val humidity: Int? = null,
    val wind_kph: Float? = null
) : Parcelable