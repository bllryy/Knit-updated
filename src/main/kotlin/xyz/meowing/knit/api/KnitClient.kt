
package xyz.meowing.knit.api

import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.player.LocalPlayer
import net.minecraft.client.multiplayer.PlayerInfo
import net.minecraft.network.chat.Component
import net.minecraft.world.level.GameType
import net.minecraft.SharedConstants

import java.nio.file.Path
import xyz.meowing.knit.api.loader.KnitLoader

import net.minecraft.world.scores.DisplaySlot

object KnitClient {
    private val tabListComparator: Comparator<PlayerInfo> = compareBy(
        { it.getGameMode() == GameType.SPECTATOR },
        { it.getTeam()?.name ?: "" },
        { it.getProfile().name.lowercase() }
    )

    @JvmStatic
    val client: Minecraft = Minecraft.getInstance()

    @JvmStatic
    val world: ClientLevel? get() = client.level

    @JvmStatic
    val player: LocalPlayer? get() = KnitPlayer.player

    @JvmStatic
    val isFabric: Boolean get() = KnitLoader.isFabric

    @JvmStatic
    val isForge: Boolean get() = KnitLoader.isForge

    @JvmStatic
    val isNeoForge: Boolean get() = KnitLoader.isNeoForge

    @JvmStatic
    val minecraftVersion: String by lazy {
        SharedConstants.getCurrentVersion().name()
    }

    @JvmStatic
    val gameDirectory: Path
        get() {
            return net.minecraft.client.Minecraft.getInstance().gameDirectory.toPath()
        }

    @JvmStatic
    val configDirectory: Path
        get() {
            return net.minecraft.client.Minecraft.getInstance().gameDirectory.toPath().resolve("config")
        }

    @JvmStatic
    val tablist: List<PlayerInfo>
        get() = client.getConnection()
            ?.getListedOnlinePlayers()
            ?.sortedWith(tabListComparator) ?: emptyList()

    @JvmStatic
    val players: List<PlayerInfo>
        get() = tablist.filter { it.getProfile().id.version() == 4 }

    @JvmStatic
    val scoreboard: Collection<Component>
        get() {
            val scoreboard = world?.scoreboard ?: return emptyList()
            val objective = scoreboard.getDisplayObjective(DisplaySlot.SIDEBAR) ?: return emptyList()
            return scoreboard.listPlayerScores(objective)
                .sortedBy { -it.value() }
                .map {
                    val ownerName = Component.literal(it.owner())
                    val team = scoreboard.getPlayerTeam(it.owner())
                    if (team == null) {
                        ownerName.copy()
                    } else {
                        Component.empty().also { main ->
                            main.append(team.getPlayerPrefix())
                            if (ownerName.string.isNotEmpty()) main.append(ownerName)
                            main.append(team.getPlayerSuffix())
                        }
                    }
                }
        }

    val scoreboardTitle get() = world?.scoreboard?.getDisplayObjective(DisplaySlot.SIDEBAR)?.displayName
}

