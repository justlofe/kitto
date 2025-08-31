package su.windmill.kitto.packet;

import net.kyori.adventure.key.Keyed;
import su.windmill.kitto.packet.storage.PacketStorage;

public interface PacketBase extends Keyed {

    default String getName() {
        return metadata().name();
    }

    PacketMetadata metadata();

    PacketStorage storage();

}
