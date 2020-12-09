package misterx.diamondgen.mixin;

import misterx.diamondgen.testmixin;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(OreFeature.class)
public class MixinOreFeature {
    @Inject(at = @At("HEAD"),method = "generate")
    private void onGenerate(StructureWorldAccess structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, OreFeatureConfig oreFeatureConfig, CallbackInfoReturnable<Boolean> cir) {
        if(oreFeatureConfig.state == Blocks.DIAMOND_ORE.getDefaultState()) {
            //System.out.println("Diamondpos");
            //System.out.println(blockPos.toShortString());
        }
    }
}
