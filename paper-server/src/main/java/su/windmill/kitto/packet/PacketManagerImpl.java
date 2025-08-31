package su.windmill.kitto.packet;

import net.kyori.adventure.key.Key;
import su.windmill.kitto.PacketManager;
import java.util.stream.Stream;

public class PacketManagerImpl implements PacketManager {

    @Override
    public PacketBase getPacket(final Key key) {
        return null;
    }

    @Override
    public PacketBase[] getPackets() {
        return new PacketBase[0];
    }

    @Override
    public Stream<PacketBase> packetsStream() {
        return Stream.empty();
    }

}
