package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.environment.*;
import game.environment.Door;
import game.items.Pokefruit;
import game.pokemon.Charmander;
import game.trade.NurseJoy;
import game.trainer.TrainerGoh;

/**
 * The main class to start the game.
 * Created by:
 * @author Riordan D. Alfredo
 *
 * Modified by:
 * @author Fong Zhiwei
 * @author Soh Meng Jienq
 * @author Leong Xin Yun
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(),
                new Floor(), new Tree(), new Lava(), new Puddle(), new Crater(), new Hay(), new Waterfall());

        List<String> map = Arrays.asList(
                ".............................................^^^^^^^^^^^^^^",
                "....,,.....,T....OOOOO.........................T...^^^^^^^^",
                "..,,,,...........OOO................................#^^^^^^",
                "..,,,,.......~....OOOO..................................^^^",
                "...TTTT.....WWW..............###.........................^^",
                "...TTTT......W...............#.#.............T............^",
                "...TTTT.....WWW......T.....................................",
                "...T.......~.........................................OOO...",
                "...~~~~~~~~..............................OOO...............",
                "....~~~~~........................OOO...............OOO.....",
                "~~~~~~~............................................OOO.....",
                "~~W~~~..T.............................T............OOO.....",
                "~~~~~~~~~..................................................");
        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        List<String> newMap = Arrays.asList(
                "##################",
                "#________________#",
                "#______.._.._____#",
                "#________________#",
                "#________________#",
                "########___#######");
        GameMap pokemonCenter = new GameMap(groundFactory, newMap);
        world.addGameMap(pokemonCenter);

        //Add player and nurse joy in the middle of new map
        NurseJoy newNurseJoy = new NurseJoy();
        pokemonCenter.at(9, 2).addActor(newNurseJoy);

        //Add player - Ash
        Player ash = new Player("Ash", '@', 1);
        world.addPlayer(ash, gameMap.at(32, 10));
        ash.addItemToInventory(new Pokefruit(Element.FIRE));

        Actor goh = new TrainerGoh();
        gameMap.at(25,5).addActor(goh);

        //Add first pokemon - Charmander
        Actor charmander = new Charmander();
        gameMap.at(33, 10).addActor(charmander);

        // Add Door to teleport from game map to new map
        Door gameDoor = new Door(pokemonCenter.at(9, 5), "to Pokemon Center!");
        gameMap.at(30, 5).setGround(gameDoor);

        // Add Exit to teleport from new map to game map
        Door newDoor = new Door(gameMap.at(30, 5), "to Game Map!");
        pokemonCenter.at(9, 5).setGround(newDoor);

        world.run();

    }
}
