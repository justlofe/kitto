package su.windmill.kitto.packet;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import java.util.Optional;

public record PacketMetadata(String name, Optional<String> description, String version, String[] authors) {

    public Component createInfo() {
        StringBuilder builder = new StringBuilder();
        if(authors.length == 1) builder.append(authors[0]);
        else if (authors.length > 1) {
            for (int i = 0; i < authors.length; i++) {
                if(i > 0) builder.append(i == authors.length - 1 ? " and" : ",").append(' ');
                builder.append(authors[i]);
            }
        }
        return Component
            .text("info about " + name + " packet:").appendNewline()
            .append(Component.text("Version: ").append(Component.text(version).color(NamedTextColor.YELLOW))).appendNewline()
            .append(Component.text("Description: ").append(Component.text(description.orElse("no description provided")).color(NamedTextColor.AQUA))).appendNewline()
            .append(Component.text("Authors: ").append(Component.text(builder.toString())));
    }

}
