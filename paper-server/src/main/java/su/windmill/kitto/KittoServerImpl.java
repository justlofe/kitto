package su.windmill.kitto;

import su.windmill.kitto.event.EventManager;
import su.windmill.kitto.event.EventManagerImpl;
import su.windmill.kitto.packet.PacketManagerImpl;

public class KittoServerImpl extends KittoServer {

    private final PacketManager packetManager;
    private final EventManager eventManager;

    public KittoServerImpl() {
        this.packetManager = new PacketManagerImpl();
        this.eventManager = new EventManagerImpl();
    }

    @Override
    public PacketManager packetManager() {
        return packetManager;
    }

    @Override
    public EventManager eventManager() {
        return eventManager;
    }

}
