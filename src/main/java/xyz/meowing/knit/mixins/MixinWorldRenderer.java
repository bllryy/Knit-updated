package xyz.meowing.knit.mixins;

import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import xyz.meowing.knit.WorldRendererAccess;
import xyz.meowing.knit.api.render.world.RenderContext;

/**
 * TODO(26.1.2): The full world-render event hooks (~15 injection points into the heavily-refactored
 * LevelRenderer) are not yet ported. prisma uses fabric-api's WorldRenderEvents, not Knit's
 * world-render system, so this is stubbed to a no-op mixin to unblock the build. Restore the real
 * injections (translate the deftu 1.21.9 branch to 26.1.2 mojmap, verified against a LevelRenderer
 * decompile) if Vexel or a consumer needs Knit's WorldRenderEvent.
 */
@Mixin(LevelRenderer.class)
public abstract class MixinWorldRenderer implements WorldRendererAccess {
    @Override
    public RenderContext knit$getContext() {
        throw new IllegalStateException("Knit world-render events are not yet ported to 26.1.2");
    }
}
