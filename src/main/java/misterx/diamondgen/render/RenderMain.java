package misterx.diamondgen.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import misterx.diamondgen.DiamondGen;
import misterx.diamondgen.SimOreGen;
import net.minecraft.client.util.math.MatrixStack;


public class RenderMain {
    public static RenderMain get() {
        return INSTANCE;
    }

    private final static RenderMain INSTANCE = new RenderMain();

    public void renderFinders(MatrixStack matrixStack) {

        RenderSystem.pushMatrix();
        RenderSystem.multMatrix(matrixStack.peek().getModel());

        GlStateManager.disableTexture();

        //Makes it render through blocks.
        GlStateManager.disableDepthTest();
        DiamondGen.gen.simOreGen.render();

        RenderSystem.popMatrix();
    }
}
