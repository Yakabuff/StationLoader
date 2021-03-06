package net.modificationstation.stationloader.api.common.recipe;

import net.minecraft.item.ItemInstance;
import net.modificationstation.stationloader.api.common.util.HasHandler;

public interface CraftingRegistry extends HasHandler<CraftingRegistry> {

    CraftingRegistry INSTANCE = new CraftingRegistry() {

        private CraftingRegistry handler;

        @Override
        public void setHandler(CraftingRegistry handler) {
            this.handler = handler;
        }

        @Override
        public void addShapedRecipe(ItemInstance itemInstance, Object... o) {
            if (handler == null)
                throw new RuntimeException("Accessed StationLoader too early!");
            else
                handler.addShapedRecipe(itemInstance, o);
        }

        @Override
        public void addShapelessRecipe(ItemInstance itemInstance, Object... o) {
            if (handler == null)
                throw new RuntimeException("Accessed StationLoader too early!");
            else
                handler.addShapelessRecipe(itemInstance, o);
        }
    };

    void addShapedRecipe(ItemInstance itemInstance, Object... o);

    void addShapelessRecipe(ItemInstance itemInstance, Object... o);
}
