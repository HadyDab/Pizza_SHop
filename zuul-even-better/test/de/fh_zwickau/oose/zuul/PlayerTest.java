package de.fh_zwickau.oose.zuul;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.fh_zwickau.oose.zuul.model.Player;
import de.fh_zwickau.oose.zuul.model.Room;

class PlayerTest {

	Room mainEntrance = new Room("Outside");
	Room corridor = new Room("Hall");
	Room kitchen = new Room("Kitchen");
	Room livingRoom = new Room("Living Room");
	Room bathRoom = new Room("BathRoom");
	Player player1 = new Player();

	@Test
	void testWalk_into_One_Room() {
		mainEntrance.setExit("north", corridor);
		corridor.setExit("south", mainEntrance);
		corridor.setExit("west", kitchen);
		kitchen.setExit("east", corridor);

		// player1.setCurrentRoom(mainEntrance);
		// player1.walk("north");

		assertEquals(player1.getCurrentRoom(), corridor);

	}

	@Test
	void testWalk_into_multiple_Rooms() {

		mainEntrance.setExit("north", corridor);
		corridor.setExit("south", mainEntrance);
		corridor.setExit("west", kitchen);
		kitchen.setExit("east", corridor);

		player1.setCurrentRoom(mainEntrance);
		// player1.walk("north");
		// player1.walk("west");

		assertEquals(player1.getCurrentRoom(), kitchen);

	}

}
