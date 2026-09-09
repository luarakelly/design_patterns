package observer.weather_station_simulator;

public class TVDisplay implements WeatherObserver {

    @Override
    public void update(double temperature) {
        System.out.println(
                "TV display: Weather update! It is "
                        + String.format("%.1f", temperature) + "°C outside.");
    }
}
