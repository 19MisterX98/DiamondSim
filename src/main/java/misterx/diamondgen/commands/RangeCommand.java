package misterx.diamondgen.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import misterx.diamondgen.DiamondGen;
import net.minecraft.server.command.ServerCommandSource;


import static com.mojang.brigadier.arguments.IntegerArgumentType.*;
import static net.minecraft.server.command.CommandManager.argument;

public class RangeCommand extends ClientCommand {

    @Override
    public String getName() {
        return "range";
    }

    @Override
    public void build(LiteralArgumentBuilder<ServerCommandSource> builder) {
        builder.then(argument("range", integer())
                .executes(ctx -> setSeed(getInteger(ctx,"range"))));
    }

    private static int setSeed(int range) {
        DiamondGen.range = range;
        System.out.println("updated range to: "+ range);
        return 0;
    }
}
