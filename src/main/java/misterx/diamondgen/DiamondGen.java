package misterx.diamondgen;

import misterx.diamondgen.render.RenderMain;
import misterx.diamondgen.render.RenderQueue;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;

public class DiamondGen implements ModInitializer {
    public static int range = 100;
    public static boolean active = true;
    public static String ver = "1.16";

    public static boolean isOpaque() {
        return opaque;
    }

    public static void setOpaque(boolean opaque) {
        DiamondGen.opaque = opaque;
    }

    private static boolean opaque = false;

    public static StartGen gen = new StartGen(0);
    @Override
    public void onInitialize() {
        clear(0);
        RenderQueue.get().add("hand", RenderMain.get()::renderFinders);
    }

    public static void clear(long seed) {
        gen = new StartGen(seed);
        if (MinecraftClient.getInstance().player == null)
            return;
        Util.reload();
    }
}
