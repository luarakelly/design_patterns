package observer.weather_station_simulator;

public class WeatherApp implements WeatherObserver {

    @Override
    public void update(double temperature) {
        System.out.println(
                "Weather app: Temperature changed to "
                        + String.format("%.1f", temperature) + "°C.");
    }
}
