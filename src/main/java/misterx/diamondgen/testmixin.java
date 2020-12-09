package misterx.diamondgen;

import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.seed.ChunkSeeds;
import misterx.diamondgen.render.RenderMain;
import misterx.diamondgen.render.RenderQueue;
import misterx.diamondgen.render.Renderer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import sun.jvm.hotspot.oops.Instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class testmixin implements ModInitializer {


    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        RenderQueue.get().add("hand", RenderMain.get()::renderFinders);
        System.out.println("Hello Fabric world!");
    }

    public static void test(int BlockX, int BlockZ) {
        long Seed = 4161023112907999473L;
        long decSeed = ChunkSeeds.getDecoratorSeed(Seed,BlockX,BlockZ,9,6, MCVersion.v1_16_2);
        //System.out.println(decSeed);
        Random random = new Random();
        random.setSeed(decSeed);
        int x = random.nextInt(16)+BlockX;
        int z = random.nextInt(16)+BlockZ;
        int y = random.nextInt(16);


        BlockPos blockPos = new BlockPos(x,y,z);
        //System.out.println(blockPos.toShortString());
        SimOreGen.get().generate(random,blockPos);
    }
}
