package xyz.meowing.knit.api.render.world

class RenderContext {
    fun prepare(vararg args: Any?) {}
    fun setFrustum(frustum: Any?) {}
    fun postStart() {}
    fun postAfterSetup() {}
    fun postBeforeEntities() {}
    fun postAfterEntities() {}
    fun setMatrixStack(stack: Any?) {}
    fun setTranslucentBlockOutline(v: Boolean) {}
    fun prepareBlockOutline(vararg args: Any?) {}
    fun postBeforeBlockOutline(result: Any?): Boolean = false
    fun shouldRenderBlockOutline(): Boolean = true
    fun postBlockOutline(): Boolean = true
    fun postAfterTranslucent() {}
    fun postBeforeDebugRender() {}
    fun postLast() {}
    fun postEnd() {}
    fun postInvalidateRenderState() {}
    fun postRenderWeather(): Boolean = false
    fun postRenderClouds(): Boolean = false
    fun postRenderSky(): Boolean = false
    fun setFrustum(v: Boolean) {}
    fun consumers(): Any? = null
}
