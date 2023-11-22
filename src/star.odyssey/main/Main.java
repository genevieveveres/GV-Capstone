package star.odyssey.main;

import star.odyssey.game.Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client controller = new Client();
        controller.execute();
    }
}
