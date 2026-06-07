package xyz.meowing.knit.api.render

import xyz.meowing.knit.api.KnitClient.client

/**
 * @author: Deftu
 */
object KnitResolution {
    object Window {
        // LOGICAL window size (e.g. 1512x982 on a 2x Retina display), NOT the physical framebuffer.
        // This is the coordinate space the GUI lays out and renders in, and the space the cursor
        // (MouseHandler.xpos) reports in. In yarn this was Window.getWidth(); the official 26.1.2
        // mapping for that same logical value is getScreenWidth() — getWidth() now returns the
        // physical framebuffer. Using the physical value here made every GUI lay out at half its
        // intended relative size on Retina. The DPI upscale happens in beginFrame, which sets the
        // GL viewport to the physical framebuffer while NanoVG draws in this logical space.
        val width: Int
            get() {
                return client.window.screenWidth
            }

        val height: Int
            get() {
                return client.window.screenHeight
            }
    }


    object Viewport {
        // PHYSICAL framebuffer size (yarn getFramebufferWidth() -> official getWidth()).
        val width: Int
            get() {
                return client.window.width
            }

        val height: Int
            get() {
                return client.window.height
            }
    }

    object Scaled {
        val width: Int
            get() {
                return client.window.guiScaledWidth
            }

        val height: Int
            get() {
                return client.window.guiScaledHeight
            }

        val scaleFactor: Double
            get() {
                return client.window.guiScale.toDouble()
            }
    }


    @JvmStatic
    val windowWidth: Int get() = Window.width

    @JvmStatic
    val windowHeight: Int get() = Window.height

    @JvmStatic
    val viewportWidth: Int get() = Viewport.width

    @JvmStatic
    val viewportHeight: Int get() = Viewport.height

    @JvmStatic
    val scaledWidth: Int get() = Scaled.width

    @JvmStatic
    val scaledHeight: Int get() = Scaled.height

    @JvmStatic
    val scaleFactor: Double get() = Scaled.scaleFactor

    @JvmStatic
    val window: com.mojang.blaze3d.platform.Window get() = client.window

    @JvmStatic
    val windowHandle: Long get() = client.window.handle()
}
