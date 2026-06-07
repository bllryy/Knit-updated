package xyz.meowing.knit.api

import xyz.meowing.knit.api.KnitClient.client
import net.minecraft.client.player.LocalPlayer
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack

object KnitPlayer {
    @JvmStatic
    val player: LocalPlayer? get() = client.player

    @JvmStatic
    val name: String? get() = player?.name?.string

    @JvmStatic
    val armor: Array<ItemStack?>
        get() {
            val p = player ?: return arrayOf(null, null, null, null)
            // Order mirrors the old combined-inventory slots 36..39 = feet, legs, chest, head.
            return arrayOf(
                p.getItemBySlot(EquipmentSlot.FEET),
                p.getItemBySlot(EquipmentSlot.LEGS),
                p.getItemBySlot(EquipmentSlot.CHEST),
                p.getItemBySlot(EquipmentSlot.HEAD)
            )
        }

    @JvmStatic
    val heldItem: ItemStack? get() = player?.mainHandItem
}
