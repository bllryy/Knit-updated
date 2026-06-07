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

import net.fabricmc.loader.api.FabricLoader

object KnitClient {
    private val tabListComparator: Comparator<PlayerInfo> = compareBy(
        { it.gameMode == GameType.SPECTATOR },
        { it.team?.name ?: "" },
        { it.profile.name.lowercase() }
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
        get() = FabricLoader.getInstance().gameDir

    @JvmStatic
    val configDirectory: Path
        get() = FabricLoader.getInstance().configDir

    @JvmStatic
    val tablist: List<PlayerInfo>
        get() = client.connection
            ?.listedOnlinePlayers
            ?.sortedWith(tabListComparator) ?: emptyList()

    @JvmStatic
    val players: List<PlayerInfo>
        get() = tablist.filter { it.profile.id.version() == 4 }

    @JvmStatic
    val scoreboard: Collection<Component>
        get() {
            val scoreboard = world?.scoreboard ?: return emptyList()
            val objective = scoreboard.getDisplayObjective(DisplaySlot.SIDEBAR) ?: return emptyList()
            return scoreboard.listPlayerScores(objective)
                .sortedBy { -it.value() }
                .map {
                    val ownerName = Component.literal(it.owner())
                    val team = scoreboard.getPlayersTeam(it.owner())
                    if (team == null) {
                        ownerName.copy()
                    } else {
                        Component.empty().also { main ->
                            main.append(team.playerPrefix)
                            if (ownerName.string.isNotEmpty()) main.append(ownerName)
                            main.append(team.playerSuffix)
                        }
                    }
                }
        }

    val scoreboardTitle get() = world?.scoreboard?.getDisplayObjective(DisplaySlot.SIDEBAR)?.displayName
}
