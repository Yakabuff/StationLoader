package net.modificationstation.stationloader.mixin.common;

import net.minecraft.recipe.SmeltingRecipeRegistry;
import net.modificationstation.stationloader.api.common.event.recipe.RecipeRegister;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmeltingRecipeRegistry.class)
public class MixinSmeltingRecipeRegistry {

    @Mutable
    @Shadow @Final private static SmeltingRecipeRegistry INSTANCE;

    @Inject(method = "<init>()V", at = @At("RETURN"))
    private void afterRecipeRegister(CallbackInfo ci) {
        INSTANCE = (SmeltingRecipeRegistry) (Object) this;
        RecipeRegister.EVENT.getInvoker().registerRecipes(RecipeRegister.Type.SMELTING);
        INSTANCE = null;
    }
}
