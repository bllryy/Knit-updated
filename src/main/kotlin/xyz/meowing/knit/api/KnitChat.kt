package xyz.meowing.knit.api

import net.minecraft.client.gui.components.ChatComponent
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

    // ChatComponent.addMessage is private in 26.1.2; sendSystemMessage is the public client-side
    // equivalent for showing a local message in chat.
    @JvmStatic
    fun fakeMessage(message: TextBuilder) {
        player?.sendSystemMessage(message.toVanilla())
    }

    @JvmStatic
    fun fakeMessage(message: Component) {
        player?.sendSystemMessage(message)
    }

    @JvmStatic
    fun fakeMessage(message: String) {
        fakeMessage(Component.literal(message))
    }

    // Instance ChatComponent.getWidth() is private; the static getWidth(scale) replicates it.
    private fun chatWidth(): Int = ChatComponent.getWidth(client.options.chatWidth().get())

    @JvmStatic
    fun getChatBreak(): String {
        val chatWidth = chatWidth()
        val textRenderer = client.font
        val dashWidth = textRenderer.width("-")

        val repeatCount = chatWidth / dashWidth
        return "-".repeat(repeatCount)
    }

    @JvmStatic
    fun getCenteredText(text: String): String {
        val chatWidth = chatWidth()
        val textRenderer = client.font
        val textWidth = textRenderer.width(text)
        if (textWidth >= chatWidth) return text
        val spaceWidth = textRenderer.width(" ")

        val padding = ((chatWidth - textWidth) / 2f / spaceWidth).roundToInt()
        return " ".repeat(padding) + text
    }
}
