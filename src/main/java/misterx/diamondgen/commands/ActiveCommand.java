package misterx.diamondgen.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import misterx.diamondgen.DiamondGen;
import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ActiveCommand extends ClientCommand{
    @Override
    public String getName() {
        return "active";
    }

    @Override
    public void build(LiteralArgumentBuilder<ServerCommandSource> builder) {
        builder.then(literal("ON").executes(context -> setActive(true)))
                .then(literal("OFF").executes(context -> setActive(false)));
    }

    private static int setActive(boolean active) {
        DiamondGen.active = active;
        System.out.println("DiamondSim is now: " + active);
        return 0;
    }
}
