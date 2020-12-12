package misterx.diamondgen.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import misterx.diamondgen.DiamondGen;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static com.mojang.brigadier.arguments.LongArgumentType.*;


public class SeedCommand extends ClientCommand {
    @Override
    public String getName() {
        return "seed";
    }

    @Override
    public void build(LiteralArgumentBuilder<ServerCommandSource> builder) {
        builder.then(argument("seed", longArg())
                .executes(ctx -> setSeed(getLong(ctx,"seed"))));
    }

    private static int setSeed(long seed) {
        System.out.println(seed);
        DiamondGen.clear(seed);
        return 0;
    }
}
