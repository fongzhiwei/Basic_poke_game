package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.environment.*;
import game.items.Candy;
import game.items.Pokeball;
import game.items.Pokefruit;
import game.pokemon.Bulbasaur;
import game.pokemon.Charmander;
import game.pokemon.Pokemon;
import game.trade.NurseJoy;

/**
 * The main class to start the game.
 * Created by:
 * @author Riordan D. Alfredo
 *
 * Modified by:
 * @author Fong Zhiwei
 * @author Soh Menh Jienq
 * @author Leong Xin Yun
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(),
                new Floor(), new Tree(), new Lava(), new Puddle(), new Crater(), new Hay(), new Waterfall());

        List<String> map = Arrays.asList(
                ".............................................^^^^^^^^^^^^^^",
                "....,,.....,T....OO............................T...^^^^^^^^",
                "..,,,,...........OOO................................#^^^^^^",
                "..,,,,.......~....O.....................................^^^",
                "...TTTT.....WWW...........#######........................^^",
                "...TTTT......W............#_____#............T............^",
                "...TTTT.....WWW......T....#_____#..........................",
                "...T.......~..............###_###..........................",
                "...~~~~~~~~................................................",
                "....~~~~~..................................................",
                "~~~~~~~....................................................",
                "~~W~~~..T.............................T....................",
                "~~~~~~~~~..................................................");
        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        //Add player - Ash
        Player ash = new Player("Ash", '@', 1);
        world.addPlayer(ash, gameMap.at(32, 10));


        //Add first pokemon - Charmander
        Actor charmander = new Charmander();
        gameMap.at(33, 10).addActor(charmander);

        //Add NurseJoy into the house in the middle of the map
        NurseJoy nurseJoy = new NurseJoy();
        gameMap.at(31,6).addActor(nurseJoy);

        world.run();

    }
}
