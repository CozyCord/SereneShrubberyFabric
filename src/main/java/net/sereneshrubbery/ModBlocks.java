package net.sereneshrubbery;

//? if >=1.21.4 {
/*import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
*///?}
//? if >=1.21 {
import net.minecraft.block.AbstractBlock;
//?}
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sereneshrubbery.block.BloomBasketBlock;
import net.sereneshrubbery.block.ButterflyBushBlock;
import net.sereneshrubbery.block.CrownCactusBlock;
import net.sereneshrubbery.block.ModFlowerBlock;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    private static final List<Block> ALL_BLOCKS = new ArrayList<>();

    // Pansies (10)
    public static final Block RED_PANSIES = registerFlower("red_pansies");
    public static final Block WHITE_PANSIES = registerFlower("white_pansies");
    public static final Block YELLOW_PANSIES = registerFlower("yellow_pansies");
    public static final Block ORANGE_PANSIES = registerFlower("orange_pansies");
    public static final Block PINK_PANSIES = registerFlower("pink_pansies");
    public static final Block PURPLE_PANSIES = registerFlower("purple_pansies");
    public static final Block BLUE_FROST_PANSIES = registerFlower("blue_frost_pansies");
    public static final Block PANOLA_PINK_PANSIES = registerFlower("panola_pink_pansies");
    public static final Block SUNRISE_PANSIES = registerFlower("sunrise_pansies");
    public static final Block HALLOWEEN_PANSIES = registerFlower("halloween_pansies");

    // Bloom Baskets (11)
    public static final Block BLOOM_BASKET = registerBloomBasket("bloom_basket");
    public static final Block BLOOM_BASKET_RED_PANSIES = registerBloomBasket("bloom_basket_red_pansies");
    public static final Block BLOOM_BASKET_WHITE_PANSIES = registerBloomBasket("bloom_basket_white_pansies");
    public static final Block BLOOM_BASKET_YELLOW_PANSIES = registerBloomBasket("bloom_basket_yellow_pansies");
    public static final Block BLOOM_BASKET_ORANGE_PANSIES = registerBloomBasket("bloom_basket_orange_pansies");
    public static final Block BLOOM_BASKET_PINK_PANSIES = registerBloomBasket("bloom_basket_pink_pansies");
    public static final Block BLOOM_BASKET_PURPLE_PANSIES = registerBloomBasket("bloom_basket_purple_pansies");
    public static final Block BLOOM_BASKET_FROST_PANSIES = registerBloomBasket("bloom_basket_frost_pansies");
    public static final Block BLOOM_BASKET_SUNRISE_PANSIES = registerBloomBasket("bloom_basket_sunrise_pansies");
    public static final Block BLOOM_BASKET_HALLOWEEN_PANSIES = registerBloomBasket("bloom_basket_halloween_pansies");
    public static final Block BLOOM_BASKET_PANOLA_PANSIES = registerBloomBasket("bloom_basket_panola_pansies");

    // Hydrangeas (6)
    public static final Block HYDRANGEA = registerFlower("hydrangea");
    public static final Block PINK_HYDRANGEA = registerFlower("pink_hydrangea");
    public static final Block PURPLE_HYDRANGEA = registerFlower("purple_hydrangea");
    public static final Block RED_HYDRANGEA = registerFlower("red_hydrangea");
    public static final Block WHITE_HYDRANGEA = registerFlower("white_hydrangea");
    public static final Block HALLOWEEN_HYDRANGEA = registerFlower("halloween_hydrangea");

    // Foxgloves (7)
    public static final Block WHITE_FOXGLOVE = registerFlower("white_foxglove");
    public static final Block PURPLE_FOXGLOVE = registerFlower("purple_foxglove");
    public static final Block PEACH_FOXGLOVE = registerFlower("peach_foxglove");
    public static final Block SUNSET_FOXGLOVE = registerFlower("sunset_foxglove");
    public static final Block HALLOWEEN_FOXGLOVE = registerFlower("halloween_foxglove");
    public static final Block CANDY_MOUNTAIN_FOXGLOVE = registerFlower("candy_mountain_foxglove");
    public static final Block LAVENDER_FOXGLOVE = registerFlower("lavender_foxglove");

    // Lupines (6)
    public static final Block LUPINE_PINK = registerFlower("lupine_pink");
    public static final Block LUPINE_WHITE = registerFlower("lupine_white");
    public static final Block PURPLE_LUPINE = registerFlower("purple_lupine");
    public static final Block GOLDEN_LUPINE = registerFlower("golden_lupine");
    public static final Block SKY_BLUE_LUPINE = registerFlower("sky_blue_lupine");
    public static final Block MANHATTAN_LIGHTS_LUPINE = registerFlower("manhattan_lights_lupine");

    // Liverworts (3)
    public static final Block BLUE_LIVERWORT = registerFlower("blue_liverwort");
    public static final Block PURPLE_LIVERWORT = registerFlower("purple_liverwort");
    public static final Block WHITE_LIVERWORT = registerFlower("white_liverwort");

    // Crown Cacti (2)
    public static final Block ORANGE_CROWN_CACTUS = registerCactus("orange_crown_cactus");
    public static final Block PINK_CROWN_CACTUS = registerCactus("pink_crown_cactus");

    // Blanketflowers (4)
    public static final Block BLANKETFLOWER_1_FLOWER = registerFlower("blanketflower_1_flower");
    public static final Block BLANKETFLOWER_DOUBLE = registerFlower("blanketflower_double");
    public static final Block BLANKETFLOWER_TRIPLE = registerFlower("blanketflower_triple");
    public static final Block BLANKETFLOWER_QUAD = registerFlower("blanketflower_quad");

    // Others (3)
    public static final Block FIREWEED = registerFlower("fireweed");
    public static final Block BUTTERFLY_BUSH = registerButterflyBush("butterfly_bush");
    public static final Block TWINFLOWER = registerFlower("twinflower");

    private static Block registerFlower(String name) {
        //? if >=1.21.4 {
        /*Identifier id = SereneId.of(name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block block = new ModFlowerBlock(AbstractBlock.Settings.create().registryKey(key));
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, key, block);
        *///?} elif >=1.21 {
        Block block = new ModFlowerBlock(AbstractBlock.Settings.copy(Blocks.POPPY));
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, SereneId.of(name), block);
        //?} else {
        /*Block block = new ModFlowerBlock();
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, SereneId.of(name), block);
        *///?}
    }

    private static Block registerBloomBasket(String name) {
        //? if >=1.21.4 {
        /*Identifier id = SereneId.of(name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block block = new BloomBasketBlock(AbstractBlock.Settings.create().registryKey(key));
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, key, block);
        *///?} elif >=1.21 {
        Block block = new BloomBasketBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS));
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, SereneId.of(name), block);
        //?} else {
        /*Block block = new BloomBasketBlock();
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, SereneId.of(name), block);
        *///?}
    }

    private static Block registerCactus(String name) {
        //? if >=1.21.4 {
        /*Identifier id = SereneId.of(name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block block = new CrownCactusBlock(AbstractBlock.Settings.create().registryKey(key));
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, key, block);
        *///?} elif >=1.21 {
        Block block = new CrownCactusBlock(AbstractBlock.Settings.copy(Blocks.CACTUS));
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, SereneId.of(name), block);
        //?} else {
        /*Block block = new CrownCactusBlock();
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, SereneId.of(name), block);
        *///?}
    }

    private static Block registerButterflyBush(String name) {
        //? if >=1.21.4 {
        /*Identifier id = SereneId.of(name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        Block block = new ButterflyBushBlock(AbstractBlock.Settings.create().registryKey(key));
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, key, block);
        *///?} elif >=1.21 {
        Block block = new ButterflyBushBlock(AbstractBlock.Settings.copy(Blocks.POPPY));
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, SereneId.of(name), block);
        //?} else {
        /*Block block = new ButterflyBushBlock();
        ALL_BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, SereneId.of(name), block);
        *///?}
    }

    public static List<Block> getAllBlocks() {
        return ALL_BLOCKS;
    }

    public static List<Block> getAllFlowers() {
        List<Block> flowers = new ArrayList<>();
        // Pansies
        flowers.add(RED_PANSIES);
        flowers.add(WHITE_PANSIES);
        flowers.add(YELLOW_PANSIES);
        flowers.add(ORANGE_PANSIES);
        flowers.add(PINK_PANSIES);
        flowers.add(PURPLE_PANSIES);
        flowers.add(BLUE_FROST_PANSIES);
        flowers.add(PANOLA_PINK_PANSIES);
        flowers.add(SUNRISE_PANSIES);
        flowers.add(HALLOWEEN_PANSIES);
        // Hydrangeas
        flowers.add(HYDRANGEA);
        flowers.add(PINK_HYDRANGEA);
        flowers.add(PURPLE_HYDRANGEA);
        flowers.add(RED_HYDRANGEA);
        flowers.add(WHITE_HYDRANGEA);
        flowers.add(HALLOWEEN_HYDRANGEA);
        // Foxgloves
        flowers.add(WHITE_FOXGLOVE);
        flowers.add(PURPLE_FOXGLOVE);
        flowers.add(PEACH_FOXGLOVE);
        flowers.add(SUNSET_FOXGLOVE);
        flowers.add(HALLOWEEN_FOXGLOVE);
        flowers.add(CANDY_MOUNTAIN_FOXGLOVE);
        flowers.add(LAVENDER_FOXGLOVE);
        // Lupines
        flowers.add(LUPINE_PINK);
        flowers.add(LUPINE_WHITE);
        flowers.add(PURPLE_LUPINE);
        flowers.add(GOLDEN_LUPINE);
        flowers.add(SKY_BLUE_LUPINE);
        flowers.add(MANHATTAN_LIGHTS_LUPINE);
        // Liverworts
        flowers.add(BLUE_LIVERWORT);
        flowers.add(PURPLE_LIVERWORT);
        flowers.add(WHITE_LIVERWORT);
        // Blanketflowers
        flowers.add(BLANKETFLOWER_1_FLOWER);
        flowers.add(BLANKETFLOWER_DOUBLE);
        flowers.add(BLANKETFLOWER_TRIPLE);
        flowers.add(BLANKETFLOWER_QUAD);
        // Others
        flowers.add(FIREWEED);
        flowers.add(BUTTERFLY_BUSH);
        flowers.add(TWINFLOWER);
        return flowers;
    }

    public static void register() {
        SereneShrubbery.LOGGER.info("Registering {} blocks", ALL_BLOCKS.size());
    }
}
