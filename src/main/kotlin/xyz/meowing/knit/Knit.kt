package xyz.meowing.knit

import net.minecraft.client.Minecraft
import xyz.meowing.knit.api.events.EventBus

object Knit {
    @Deprecated("Use KnitClient.client")
    val client: Minecraft = Minecraft.getInstance()

    @JvmStatic
    val EventBus = EventBus()
}
