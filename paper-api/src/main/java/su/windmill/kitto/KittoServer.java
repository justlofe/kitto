package su.windmill.kitto;

import su.windmill.kitto.event.EventManager;

public abstract class KittoServer {

    private static KittoServer server;

    public KittoServer() {
    }

    public abstract PacketManager packetManager();
    public abstract EventManager eventManager();

    public static KittoServer getServer() {
        return server;
    }

    public static void setServer(KittoServer server) {
        KittoServer.server = server;
    }

}
