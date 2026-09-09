package observer.weather_station_simulator;

public class Main {

    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        PhoneDisplay phone = new PhoneDisplay();
        TVDisplay tv = new TVDisplay();
        WeatherApp app = new WeatherApp();

        station.registerObserver(phone);
        station.registerObserver(tv);
        station.registerObserver(app);

        station.start();

        try {
            Thread.sleep(10000);

            System.out.println("\n--- Removing TV observer ---\n");
            station.removeObserver(tv);
            Thread.sleep(10000);

            station.interrupt();

        } catch (InterruptedException e) {
            station.interrupt();
            Thread.currentThread().interrupt();
        }
    }
}
