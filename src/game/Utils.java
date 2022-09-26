package game;

import java.util.Random;

public class Utils {
    public static int nextNum(int low, int high) {
        Random r = new Random();
        return (r.nextInt(high - low) + low);
    }

    public static boolean chance(int percentage){
        int num = nextNum(0, 100);
        return num <= percentage;
    }
}
