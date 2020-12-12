package misterx.diamondgen;


import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class Util {
    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static final PlayerEntity player = MinecraftClient.getInstance().player;

    public static int distanceToPlayer(BlockPos posOre) {
        assert player != null;
        BlockPos posPlayer = player.getBlockPos();
        int x = Math.abs(Math.abs(posOre.getX())-Math.abs(posPlayer.getX()));
        int z = Math.abs(Math.abs(posOre.getZ())-Math.abs(posPlayer.getZ()));
        return x + z;
    }
    public static void reload() {
        int renderdistance = client.options.viewDistance;

        int playerChunkX = (int) (Math.round(player.getX()) >> 4);
        int playerChunkZ = (int) (Math.round(player.getZ()) >> 4);
        for(int i = playerChunkX - renderdistance;i < playerChunkX + renderdistance; i++) {
            for(int j = playerChunkZ - renderdistance;j < playerChunkZ + renderdistance; j++) {
                DiamondGen.gen.getStartingPos(i << 4,j<<4,client.world);
            }
        }
    }
}
