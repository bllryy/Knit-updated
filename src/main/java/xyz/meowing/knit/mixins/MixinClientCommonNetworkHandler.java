package xyz.meowing.knit.mixins;

import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.network.protocol.common.ClientboundPingPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.meowing.knit.Knit;
import xyz.meowing.knit.internal.events.TickEvent;

@Mixin(ClientCommonPacketListenerImpl.class)
public class MixinClientCommonNetworkHandler {
    @Inject(method = "handlePing", at = @At("HEAD"))
    private void zen$onPingStart(ClientboundPingPacket packet, CallbackInfo ci) {
       Knit.getEventBus().post(new TickEvent.Server.Start());
    }

    @Inject(method = "handlePing", at = @At("TAIL"))
    private void zen$onPingEnd(ClientboundPingPacket packet, CallbackInfo ci) {
        Knit.getEventBus().post(new TickEvent.Server.End());
    }
}
