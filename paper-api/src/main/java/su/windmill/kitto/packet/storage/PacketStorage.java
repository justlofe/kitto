package su.windmill.kitto.packet.storage;

import java.io.File;

public interface PacketStorage {

    /**
     * This folder used for storing configuration files, may be deleted
     * on packet deletion if user will choose so
     */
    File configurationFolder();

    /**
     * Folder for all files other than configs
     */
    File dataFolder();

}
