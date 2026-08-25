package src.main.java.factory.rpg_map_generator;

public class Game {

    public static Map createMap() {
        System.out.println("City map:");
        return new CityMap();
    }

    public static void main(String[] args) {
        Map map = createMap();

        System.out.println("Generated map:");
        map.display();
    }
}