package observer.weather_station_simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherStation extends Thread {
    private static final double MIN_TEMPERATURE = -20;
    private static final double MAX_TEMPERATURE = 40;

    private final List<WeatherObserver> observers = new ArrayList<>();
    private final Random random = new Random();

    private double temperature;

    public WeatherStation() {
        temperature = MIN_TEMPERATURE
                + random.nextDouble() * (MAX_TEMPERATURE - MIN_TEMPERATURE);

        System.out.println("Initial temperature: "
                + String.format("%.1f", temperature) + "°C");
    }

    public void registerObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    private void updateTemperature() {
        int change = random.nextBoolean() ? 1 : -1;

        temperature += change;

        if (temperature > MAX_TEMPERATURE) {
            temperature = MAX_TEMPERATURE;
        }

        if (temperature < MIN_TEMPERATURE) {
            temperature = MIN_TEMPERATURE;
        }
    }

    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(temperature);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                int delay = 1000 + random.nextInt(4001);
                Thread.sleep(delay);

                updateTemperature();

                System.out.println("\nWeather station: "
                        + String.format("%.1f", temperature) + "°C");

                notifyObservers();

            } catch (InterruptedException e) {
                System.out.println("Weather station stopped.");
                break;
            }
        }
    }
}
