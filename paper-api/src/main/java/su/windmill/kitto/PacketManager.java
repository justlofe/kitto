package su.windmill.kitto;

import net.kyori.adventure.key.Key;
import su.windmill.kitto.packet.PacketBase;
import java.util.stream.Stream;

public interface PacketManager {

    PacketBase getPacket(Key key);

    PacketBase[] getPackets();
    Stream<PacketBase> packetsStream();

}
