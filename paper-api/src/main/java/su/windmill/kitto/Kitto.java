package su.windmill.kitto;

import su.windmill.kitto.event.EventManager;

public class Kitto {

    private Kitto() {}

    public static KittoServer getServer() {
        return KittoServer.getServer();
    }

    public static PacketManager packetManager() {
        return getServer().packetManager();
    }

    public static EventManager eventManager() {
        return getServer().eventManager();
    }

}
