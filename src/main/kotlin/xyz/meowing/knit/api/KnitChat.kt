
package xyz.meowing.knit.api

import net.minecraft.network.chat.Component
import xyz.meowing.knit.api.KnitClient.client
import xyz.meowing.knit.api.KnitPlayer.player
import xyz.meowing.knit.api.text.internal.TextBuilder
import kotlin.math.roundToInt

object KnitChat {
    @JvmStatic
    fun sendMessage(message: String) {
        player?.connection?.sendChat(message)
    }

    @JvmStatic
    fun sendCommand(command: String) {
        player?.connection?.sendCommand(command.removePrefix("/"))
    }

    @JvmStatic
    fun fakeMessage(message: TextBuilder) {
        client.gui.getChat().addClientSystemMessage(message.toVanilla())
    }

    @JvmStatic
    fun fakeMessage(message: Component) {
        client.gui.getChat().addClientSystemMessage(message)
    }

    @JvmStatic
    fun fakeMessage(message: String) {
        fakeMessage(Component.literal(message))
    }

    private fun getChatWidth(): Int = 320

    @JvmStatic
    fun getChatBreak(): String {
        val chatWidth = getChatWidth()
        val dashWidth = client.font.width("-")
        return "-".repeat(chatWidth / dashWidth)
    }

    @JvmStatic
    fun getCenteredText(text: String): String {
        val chatWidth = getChatWidth()
        val textWidth = client.font.width(text)
        if (textWidth >= chatWidth) return text
        val spaceWidth = client.font.width(" ")
        val padding = ((chatWidth - textWidth) / 2f / spaceWidth).roundToInt()
        return " ".repeat(padding) + text
    }
}
