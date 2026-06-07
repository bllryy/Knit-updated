package xyz.meowing.knit.api.input

import xyz.meowing.knit.api.render.KnitResolution
import xyz.meowing.knit.api.render.KnitResolution.windowHandle
import xyz.meowing.knit.api.KnitClient.client
import org.lwjgl.glfw.GLFW
import kotlin.math.max

/**
 * @author: Deftu
 */
object KnitMouse {
    object Raw {
        // MouseHandler.xpos()/ypos() report the cursor in LOGICAL window (screen) coordinates,
        // which is exactly the space Vexel now lays out and renders in (KnitResolution.windowWidth
        // == Window.getScreenWidth()). So no scaling is needed — they line up directly.
        val x: Double
            get() {
                return client.mouseHandler.xpos()
            }

        val y: Double
            get() {
                return client.mouseHandler.ypos()
            }
    }

    object Scaled {
        val x: Double
            get() = Raw.x * KnitResolution.scaledWidth / max(1, KnitResolution.windowWidth)
        val y: Double
            get() = Raw.y * KnitResolution.scaledWidth / max(1, KnitResolution.windowWidth)
    }

    var isCursorGrabbed: Boolean
        get() {
            return client.mouseHandler.isMouseGrabbed
        }
        set(value) {
            if (value) client.mouseHandler.grabMouse() else client.mouseHandler.releaseMouse()
        }

    fun isMouseButton(code: Int): Boolean {
        return code in GLFW.GLFW_MOUSE_BUTTON_1..GLFW.GLFW_MOUSE_BUTTON_8
    }

    fun isPressed(code: Int): Boolean {
        if (!isMouseButton(code)) return false

        val state = GLFW.glfwGetMouseButton(windowHandle, code)
        return state == GLFW.GLFW_PRESS || state == GLFW.GLFW_REPEAT
    }
}