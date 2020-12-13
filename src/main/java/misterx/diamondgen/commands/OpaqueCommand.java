package misterx.diamondgen.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import misterx.diamondgen.DiamondGen;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

public class OpaqueCommand extends ClientCommand{
    @Override
    public String getName() {
        return "checkOpaque";
    }

    @Override
    public void build(LiteralArgumentBuilder<ServerCommandSource> builder) {
        builder.then(literal("ON").executes(context -> setOpaque(true)))
                .then(literal("OFF").executes(context -> setOpaque(false)));
    }

    private int setOpaque(boolean checkOpaque) {
        DiamondGen.setOpaque(checkOpaque);
        System.out.println(DiamondGen.isOpaque());
        return 0;
    }
}
