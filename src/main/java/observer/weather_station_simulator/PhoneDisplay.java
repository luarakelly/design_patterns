package observer.weather_station_simulator;

public class PhoneDisplay implements WeatherObserver {

    @Override
    public void update(double temperature) {
        System.out.println(
                "Phone display: Current temperature is "
                        + String.format("%.1f", temperature) + "°C");
    }
}
