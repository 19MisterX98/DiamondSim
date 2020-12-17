package misterx.diamondgen;


import misterx.diamondgen.render.Color;
import misterx.diamondgen.render.Cube;
import misterx.diamondgen.render.Renderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.dimension.DimensionType;


import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

public class SimOreGen {

    public List<Renderer> renderers = new ArrayList<>();
    public ClientWorld world = MinecraftClient.getInstance().world;


    public void render() {
        if(!DiamondGen.active){
            return;
        }
        try {
            if (isOverworld(DiamondGen.gen.world.getDimension())) {
                this.renderers.forEach(renderer -> {
                    if (Util.distanceToPlayer(renderer.getPos()) < DiamondGen.range) {
                        if (DiamondGen.isOpaque()) {
                            if (Util.isOpaque(renderer.getPos())) {
                                renderer.render();
                            }
                        } else {
                            renderer.render();
                        }
                    }
                });
            }else if(isNether(DiamondGen.gen.world.getDimension())) {
                DiamondGen.gen.simDebrisGen.renderers.forEach(renderer -> {
                    if (Util.distanceToPlayer(renderer.getPos()) < DiamondGen.range) {
                        if (DiamondGen.isOpaque()) {
                            if (Util.isOpaque(renderer.getPos())) {
                                renderer.render();
                            }
                        } else {
                            renderer.render();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean generate(Random random, BlockPos blockPos, ClientWorld world,int size) {
        float f = random.nextFloat() * 3.1415927F;
        float g = (float)size / 8.0F;
        int i = MathHelper.ceil(((float)size / 16.0F * 2.0F + 1.0F) / 2.0F);
        double d = (double)blockPos.getX() + Math.sin((double)f) * (double)g;
        double e = (double)blockPos.getX() - Math.sin((double)f) * (double)g;
        double h = (double)blockPos.getZ() + Math.cos((double)f) * (double)g;
        double j = (double)blockPos.getZ() - Math.cos((double)f) * (double)g;
        double l = (double)(blockPos.getY() + random.nextInt(3) - 2);
        double m = (double)(blockPos.getY() + random.nextInt(3) - 2);
        int n = blockPos.getX() - MathHelper.ceil(g) - i;
        int o = blockPos.getY() - 2 - i;
        int p = blockPos.getZ() - MathHelper.ceil(g) - i;
        int q = 2 * (MathHelper.ceil(g) + i);
        int r = 2 * (2 + i);
        for(int s = n; s <= n + q; ++s) {
            for(int t = p; t <= p + q; ++t) {
                //if (o <= structureWorldAccess.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, s, t)) {
                    return generateVeinPart(random, d, e, h, j, l, m, n, o, p, q, r, world, size);
                //}
            }
        }

        return false;
    }

    protected boolean generateVeinPart(Random random, double startX, double endX, double startZ, double endZ, double startY, double endY, int x, int y, int z, int size, int i,ClientWorld world,int veinSize) {
        int j = 0;
        BitSet bitSet = new BitSet(size * i * size);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int k = veinSize;
        double[] ds = new double[k * 4];

        int n;
        double p;
        double q;
        double r;
        double s;
        for(n = 0; n < k; ++n) {
            float f = (float)n / (float)k;
            p = MathHelper.lerp(f, startX, endX);
            q = MathHelper.lerp(f, startY, endY);
            r = MathHelper.lerp(f, startZ, endZ);
            s = random.nextDouble() * (double)k / 16.0D;
            double m = ((double)(MathHelper.sin(3.1415927F * f) + 1.0F) * s + 1.0D) / 2.0D;
            ds[n * 4] = p;
            ds[n * 4 + 1] = q;
            ds[n * 4 + 2] = r;
            ds[n * 4 + 3] = m;
        }

        for(n = 0; n < k - 1; ++n) {
            if (ds[n * 4 + 3] > 0.0D) {
                for(int o = n + 1; o < k; ++o) {
                    if (ds[o * 4 + 3] > 0.0D) {
                        p = ds[n * 4 ] - ds[o * 4];
                        q = ds[n * 4 + 1] - ds[o * 4 + 1];
                        r = ds[n * 4 + 2] - ds[o * 4 + 2];
                        s = ds[n * 4 + 3] - ds[o * 4 + 3];
                        if (s * s > p * p + q * q + r * r) {
                            if (s > 0.0D) {
                                ds[o * 4 + 3] = -1.0D;
                            } else {
                                ds[n * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        for(n = 0; n < k; ++n) {
            double u = ds[n * 4 + 3];
            if (u >= 0.0D) {
                double v = ds[n * 4];
                double w = ds[n * 4 + 1];
                double aa = ds[n * 4 + 2];
                int ab = Math.max(MathHelper.floor(v - u), x);
                int ac = Math.max(MathHelper.floor(w - u), y);
                int ad = Math.max(MathHelper.floor(aa - u), z);
                int ae = Math.max(MathHelper.floor(v + u), ab);
                int af = Math.max(MathHelper.floor(w + u), ac);
                int ag = Math.max(MathHelper.floor(aa + u), ad);

                for(int ah = ab; ah <= ae; ++ah) {
                    double ai = ((double)ah + 0.5D - v) / u;
                    if (ai * ai < 1.0D) {
                        for(int aj = ac; aj <= af; ++aj) {
                            double ak = ((double)aj + 0.5D - w) / u;
                            if (ai * ai + ak * ak < 1.0D) {
                                for(int al = ad; al <= ag; ++al) {
                                    double am = ((double)al + 0.5D - aa) / u;
                                    if (ai * ai + ak * ak + am * am < 1.0D) {
                                        int an = ah - x + (aj - y) * size + (al - z) * size * i;
                                        if (!bitSet.get(an)) {
                                            bitSet.set(an);
                                            mutable.set(ah, aj, al);
                                            if (mutable.getY() > 0) {
                                                this.renderers.add(new Cube(mutable, new Color(255, 0, 0)));
                                                ++j;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return j > 0;
    }
    private boolean isOverworld(DimensionType dimension) {
        return ((DimensionTypeCaller)dimension).getInfiniburn().getPath().endsWith("overworld");
    }

    private boolean isNether(DimensionType dimension) {
        return ((DimensionTypeCaller)dimension).getInfiniburn().getPath().endsWith("nether");
    }

    public interface DimensionTypeCaller {
        Identifier getInfiniburn();
    }
}
