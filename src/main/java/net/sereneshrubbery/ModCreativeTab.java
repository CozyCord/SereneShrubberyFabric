package net.sereneshrubbery;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ModCreativeTab {
    public static void register() {
        Registry.register(Registries.ITEM_GROUP, SereneId.of("serene_shrubbery"),
            FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.tabserene_shrubbery"))
                .icon(() -> new ItemStack(ModBlocks.BUTTERFLY_BUSH.asItem()))
                .entries((displayContext, entries) -> {
                    for (Block block : ModBlocks.getAllBlocks()) {
                        addBlockItem(entries, block);
                    }
                })
                .build()
        );
        SereneShrubbery.LOGGER.info("Registered creative tab");
    }

    private static void addBlockItem(ItemGroup.Entries entries, Block block) {
        var item = block.asItem();
        if (item != null && item != Items.AIR) {
            entries.add(new ItemStack(item, 1));
        } else {
            SereneShrubbery.LOGGER.warn("Block {} has no item!", Registries.BLOCK.getId(block));
        }
    }
}
