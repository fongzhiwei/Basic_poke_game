package game.time;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that gives time perception on the affected instances.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Soh Meng Jienq
 * @version 2.0
 *
 * @see TimePerception
 */
public class TimePerceptionManager{
    /**
     * A list of polymorph instances (any classes that implements TimePerception,
     * such as, a Charmander implements TimePerception, it will be stored in here)
     */
    private final List<TimePerception> timePerceptionList;

    private int turn;

    private TimePeriod shift; // DAY or NIGHT

    /**
     * A singleton instance
     */
    private static TimePerceptionManager instance;

    /**
     * Get the singleton instance of time perception manager
     * @return TimePerceptionManager singleton instance
     */
    public static TimePerceptionManager getInstance() {
        if (instance == null){
            instance = new TimePerceptionManager();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private TimePerceptionManager() {
        timePerceptionList = new ArrayList<>();
        turn = 0;
    }

    /**
     * Traversing through all instances in the list and execute them
     * By doing this way, it will avoid using `instanceof` all over the place.
     */
    public void run() {
        int last_digit = turn % 10;
        List<TimePerception> originalList = new ArrayList<>();
        originalList.addAll(timePerceptionList);

        if (last_digit >= 5){
            System.out.println("Night Turn " + (last_digit-4));
            for (TimePerception timePerception : originalList) {
                timePerception.nightEffect();
            }
        }
        else{
            System.out.println("Day Turn " + (last_digit+1));
            for (TimePerception timePerception : originalList) {
                timePerception.dayEffect();
            }
        }
        turn += 1;
    }


    /**
     * Add the TimePerception instance to the list
     * @param objInstance any instance that implements TimePerception
     */
    public void append(TimePerception objInstance) {
        timePerceptionList.add(objInstance);
    }

    /**
     * Remove a TimePerception instance from the list
     * @param objInstance object instance
     */
    public void cleanUp(TimePerception objInstance) {
        timePerceptionList.remove(objInstance);
    }

}
