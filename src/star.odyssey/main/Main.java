package star.odyssey.main;

import star.odyssey.game.Client;

import java.io.IOException;

// Main class to run the Star Odyssey game.
public class Main {
    public static void main(String[] args) throws IOException {
        Client controller = new Client(); // Creating the game client.
        controller.execute();
        // Starting the client execution.
    }
}