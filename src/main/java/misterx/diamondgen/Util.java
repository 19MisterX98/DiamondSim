package misterx.diamondgen;


import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class Util {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    public static int distanceToPlayer(BlockPos posOre) {
        if(client.player != null) {
            BlockPos posPlayer = client.player.getBlockPos();

            int x = Math.abs(posPlayer.getX()-posOre.getX());
            int z = Math.abs(posPlayer.getZ()-posOre.getZ());

            return x+z;
        }
        return 1001;
    }
    public static void reload() {
        int renderdistance = client.options.viewDistance;

        int playerChunkX = (int) (Math.round(client.player.getX()) >> 4);
        int playerChunkZ = (int) (Math.round(client.player.getZ()) >> 4);
        for(int i = playerChunkX - renderdistance;i < playerChunkX + renderdistance; i++) {
            for(int j = playerChunkZ - renderdistance;j < playerChunkZ + renderdistance; j++) {
                DiamondGen.gen.getStartingPos(i << 4,j<<4);
            }
        }
    }
     public static boolean isOpaque(BlockPos pos) {
        if(DiamondGen.gen.world != null) {
            return DiamondGen.gen.world.getBlockState(pos).isOpaque();
        }
        return false;
    }
}
