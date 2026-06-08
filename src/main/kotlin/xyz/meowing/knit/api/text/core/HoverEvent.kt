package xyz.meowing.knit.api.text.core

import xyz.meowing.knit.api.text.internal.TextBuilder
import net.minecraft.world.item.ItemStackTemplate

sealed interface HoverEvent {
    data class ShowText(val text: TextBuilder) : HoverEvent
    data class ShowItem(val stack: ItemStackTemplate) : HoverEvent
}