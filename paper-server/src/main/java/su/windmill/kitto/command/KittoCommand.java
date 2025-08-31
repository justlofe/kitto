package su.windmill.kitto.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.adventure.AdventureComponent;
import net.kyori.adventure.key.Key;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import su.windmill.kitto.Kitto;
import su.windmill.kitto.packet.PacketBase;

public class KittoCommand {

    private static final Component HELP = Component
        .literal("help for kitto command:\n")
        .append("kitto install <packet-name>  - installs packet\n")
        .append("kitto update [packet-name]   - updates specified packet or all packets\n")
        .append("kitto remove <packet-name>   - deletes packet\n")
        .append("kitto info <packet-name>     - prints info about packet");

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("kitto")
                .requires(Commands.hasPermission(3))
                .executes(KittoCommand::displayHelp)
                .then(
                    Commands.literal("install").then(
                        Commands.argument("packet-name", StringArgumentType.string())
                            .executes(KittoCommand::installPacket)
                    )
                )
                .then(
                    Commands.literal("update").then(
                        Commands.argument("packet-name", StringArgumentType.string())
                            .executes(KittoCommand::updateSinglePacket)
                    ).executes(KittoCommand::updateAllPackets)
                )
                .then(
                    Commands.literal("remove").then(
                        Commands.argument("packet-name", StringArgumentType.string())
                            .executes(KittoCommand::removePacket)
                    )
                )
                .then(
                    Commands.literal("info").then(
                        Commands.argument("packet-name", StringArgumentType.string())
                            .executes(KittoCommand::printInfo)
                    )
                )
        );
    }

    private static int displayHelp(CommandContext<CommandSourceStack> stack) {
        stack.getSource().sendSuccess(() -> HELP, false);
        return 1;
    }

    private static int installPacket(CommandContext<CommandSourceStack> stack) {
        return 1;
    }

    private static int removePacket(CommandContext<CommandSourceStack> stack) {
        return 1;
    }

    private static int printInfo(CommandContext<CommandSourceStack> stack) {
        String packet = stack.getArgument("packet-name", String.class);
        PacketBase packetBase = Kitto.packetManager().getPacket(Key.key(packet));
        stack.getSource().sendSuccess(() ->
            new AdventureComponent(packetBase.metadata().createInfo()),
            false
        );
        return 1;
    }

    private static int updateSinglePacket(CommandContext<CommandSourceStack> stack) {
        return 1;
    }

    private static int updateAllPackets(CommandContext<CommandSourceStack> stack) {
        return 1;
    }


}
