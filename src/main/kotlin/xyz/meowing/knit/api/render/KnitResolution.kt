package xyz.meowing.knit.api.render

import xyz.meowing.knit.api.KnitClient.client

object KnitResolution {
    object Window {
        val width: Int get() = client.window.getWidth()
        val height: Int get() = client.window.getHeight()
    }

    object Viewport {
        val width: Int get() = client.window.getWidth()
        val height: Int get() = client.window.getHeight()
    }

    object Scaled {
        val width: Int get() = client.window.getGuiScaledWidth()
        val height: Int get() = client.window.getGuiScaledHeight()
        val scaleFactor: Double get() = client.window.getGuiScale().toDouble()
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
