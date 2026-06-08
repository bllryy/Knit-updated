package xyz.meowing.knit.mixins;

import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import xyz.meowing.knit.WorldRendererAccess;
import xyz.meowing.knit.api.render.world.RenderContext;

@Mixin(LevelRenderer.class)
public abstract class MixinWorldRenderer implements WorldRendererAccess {
    @Override
    public RenderContext knit$getContext() {
        throw new IllegalStateException("WorldRenderer is not rendering");
    }
}
