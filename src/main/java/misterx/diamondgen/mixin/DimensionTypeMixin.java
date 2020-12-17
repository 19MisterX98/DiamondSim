package misterx.diamondgen.mixin;

import misterx.diamondgen.SimOreGen;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DimensionType.class)
public class DimensionTypeMixin implements SimOreGen.DimensionTypeCaller {

	@Shadow @Final private Identifier infiniburn;

	@Override
	public Identifier getInfiniburn() {
		return this.infiniburn;
	}

}
