package game;

import java.util.Random;

/**
 * A class that calculate the percentage of the possibility.
 *
 * @author Soh Meng Jienq <msoh0007@student.monash.edu>
 * @version 1.0
 *
 * @see game.environment.Lava
 * @see game.environment.Puddle
 * @see game.environment.Tree
 * @see game.environment.Waterfall
 * @see game.environment.Crater
 */

public class Utils {

    /**
     * This is a method to generate a random number within a range
     * @param low the lower bound of the range
     * @param high the upper bound of the range
     * @return a random number within the range
     */
    public static int nextNum(int low, int high) {
        Random r = new Random();
        return (r.nextInt(high - low) + low);
    }

    /**
     * This is a method to calculate the possibility percentage.
     * @param percentage the possibility percentage requires from each class
     * @return the return of True or False
     */
    public static boolean chance(int percentage){
        int num = nextNum(0, 100);
        return num < percentage;
    }
}
