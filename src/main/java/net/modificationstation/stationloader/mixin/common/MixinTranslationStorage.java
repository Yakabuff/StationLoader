package net.modificationstation.stationloader.mixin.common;

import net.minecraft.client.resource.language.TranslationStorage;
import net.modificationstation.stationloader.api.common.lang.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TranslationStorage.class)
public class MixinTranslationStorage {

    @Shadow private static TranslationStorage instance;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void afterLangLoaded(CallbackInfo ci) {
        instance = (TranslationStorage) (Object) this;
        I18n.INSTANCE.changeLang("en_US");
        instance = null;
    }
}
