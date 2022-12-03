data class WeatherResponseX(
    val base: String,
    val clouds: CloudsX,
    val cod: Int,
    val coord: CoordX,
    val dt: Int,
    val id: Int,
    val main: MainX,
    val name: String,
    val sys: SysX,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: WindX
)