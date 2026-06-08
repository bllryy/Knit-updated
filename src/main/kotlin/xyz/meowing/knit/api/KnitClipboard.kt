package xyz.meowing.knit.api

import org.lwjgl.glfw.GLFW
import xyz.meowing.knit.api.KnitClient.client

object KnitClipboard {
    @JvmStatic
    var string: String
        get() = GLFW.glfwGetClipboardString(client.window.handle()) ?: ""
        set(value) { GLFW.glfwSetClipboardString(client.window.handle(), value) }
}
