package su.windmill.kitto.packet;

import su.windmill.kitto.packet.storage.PacketStorage;

public abstract class Packet implements PacketBase {

    private boolean initialized;

    private PacketMetadata metadata;
    private PacketStorage storage;

    public Packet() {

    }

    public final void init(PacketMetadata metadata, PacketStorage storage) {
        if(initialized) return;
        this.metadata = metadata;
        this.storage = storage;
        initialized = true;
    }

    @Override
    public PacketMetadata metadata() {
        return metadata;
    }

    @Override
    public PacketStorage storage() {
        return storage;
    }

}
