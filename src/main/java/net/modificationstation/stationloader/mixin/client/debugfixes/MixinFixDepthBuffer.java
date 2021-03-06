package net.modificationstation.stationloader.mixin.client.debugfixes;

import net.fabricmc.loader.FabricLoader;
import net.minecraft.client.Minecraft;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Minecraft.class, priority = 999999)
public class MixinFixDepthBuffer {

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;create()V", remap = false), require = 0)
    private void fixDepthBuffer() throws LWJGLException {
        if (FabricLoader.INSTANCE.isDevelopmentEnvironment()) {
            PixelFormat pixelformat = new PixelFormat();
            pixelformat = pixelformat.withDepthBits(24);
            Display.create(pixelformat);
        } else {
            Display.create();
        }
    }
}
