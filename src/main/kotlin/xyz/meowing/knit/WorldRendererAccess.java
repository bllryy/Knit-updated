package xyz.meowing.knit;

import xyz.meowing.knit.api.render.world.RenderContext;

public interface WorldRendererAccess {
    RenderContext knit$getContext();
}