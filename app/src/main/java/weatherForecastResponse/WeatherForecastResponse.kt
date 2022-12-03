package weatherForecastResponse

data class WeatherForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherListElement>,
    val message: Double
)