package net.sereneshrubbery;

import net.minecraft.block.Block;
//? if <1.21.4 {
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
//?}
//? if >=1.21.4 {
/*import net.minecraft.item.Items;
*///?}

public class ModItems {
    public static void register() {
        SereneShrubbery.LOGGER.info("Registering block items for {} blocks", ModBlocks.getAllBlocks().size());
        for (Block block : ModBlocks.getAllBlocks()) {
            registerBlockItem(block);
        }
        SereneShrubbery.LOGGER.info("Registered block items");
    }

    private static void registerBlockItem(Block block) {
        //? if >=1.21.4 {
        /*Items.register(block);
        *///?} else {
        Identifier blockId = Registries.BLOCK.getId(block);
        Item item = new BlockItem(block, new Item.Settings());
        Registry.register(Registries.ITEM, blockId, item);
        //?}
    }
}
