package factory.rpg_map_generator;

import java.util.Random;

public class Game {

    public static Map createMap() {
        Random random = new Random();

        int mapType = random.nextInt(2);

        if (mapType == 1) {
            System.out.println("City map");
            return new CityMap();
        } else {
            System.out.println("wilderness map");
            return new WildernessMap();
        }
    }

    public static void main(String[] args) {
        Map map = createMap();

        System.out.println("Generated map:");
        map.display();
    }
}