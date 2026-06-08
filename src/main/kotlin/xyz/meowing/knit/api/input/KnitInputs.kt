package xyz.meowing.knit.api.input

import org.lwjgl.glfw.GLFW
import com.mojang.blaze3d.platform.InputConstants

object KnitInputs {
    fun get(code: Int): KnitInputCode {
        return when {
            KnitKeyboard.isKeyboardButton(code) -> KnitKey(code)
            KnitMouse.isMouseButton(code) -> KnitMouseButton(code)
            else -> throw IllegalArgumentException("Code $code is not a valid key or mouse button")
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getDisplayName(code: Int, scanCode: Int = -1): String {
        val keyName = GLFW.glfwGetKeyName(code, scanCode)
        if (keyName != null) {
            if (keyName.length == 1) return keyName.uppercase()
            return keyName
        }
        val name = (if (code == -1) {
            InputConstants.Type.SCANCODE.getOrCreate(scanCode)
        } else {
            InputConstants.Type.KEYSYM.getOrCreate(code)
        }).displayName.string
        if (name.length == 1) return name.uppercase()
        return name
    }
}