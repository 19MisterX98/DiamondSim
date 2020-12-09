package misterx.diamondgen.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ConfiguredFeature.class)
public abstract class MixinCofiguredFeature {

    @Inject(at = @At("HEAD"), method = "generate")
    private void onGenerate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        //System.out.println("configured feature "+pos.toShortString());
    }
}
