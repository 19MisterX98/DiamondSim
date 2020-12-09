package misterx.diamondgen.mixin;

import misterx.diamondgen.testmixin;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.security.auth.callback.Callback;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;


@Mixin(Biome.class)
public class MixinBiome {
    @Inject(method = "generateFeatureStep", at = @At("HEAD"))
    private void onGenerateFeatueStep(StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, ChunkRegion region, long populationSeed, ChunkRandom random, BlockPos pos, CallbackInfo ci) {
        testmixin.test(pos.getX(), pos.getZ());
    }
    @Inject(locals = LocalCapture.CAPTURE_FAILHARD ,method = "generateFeatureStep", at = @At(value = "INVOKE",target = "Lnet/minecraft/world/gen/feature/ConfiguredFeature;generate(Lnet/minecraft/world/StructureWorldAccess;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;)Z"))
    private void getdec(StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, ChunkRegion region, long populationSeed, ChunkRandom random, BlockPos pos, CallbackInfo ci, List list, int i, int j, int k){
        if(j == 6 && k == 9) {
            //System.out.println("Server initiates diamond gen");
        }
    }
}


