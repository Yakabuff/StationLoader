package net.modificationstation.stationloader.mixin.common;

import net.minecraft.item.ItemInstance;
import net.minecraft.recipe.SmeltingRecipeRegistry;
import net.minecraft.tileentity.TileEntityFurnace;
import net.modificationstation.stationloader.api.common.recipe.SmeltingRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TileEntityFurnace.class)
public class MixinTileEntityFurnace {

    @Shadow private ItemInstance[] contents;

    @SuppressWarnings("InvalidMemberReference")
    @Redirect(method = {"canAcceptRecipeOutput()Z", "craftRecipe()V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/SmeltingRecipeRegistry;getResult(I)Lnet/minecraft/item/ItemInstance;"))
    private ItemInstance getResult(SmeltingRecipeRegistry smeltingRecipeRegistry, int i) {
        return SmeltingRegistry.INSTANCE.getResultFor(contents[0]);
    }
}
