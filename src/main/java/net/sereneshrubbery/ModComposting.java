package net.sereneshrubbery;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;

/**
 * Registers composting chances for all flowers in the mod.
 * Flowers can be composted in composter blocks to produce bone meal.
 */
public class ModComposting {
    // Standard flower composting chance (same as vanilla flowers like dandelion, poppy)
    private static final float FLOWER_COMPOST_CHANCE = 0.65f;
    // Tall flower composting chance (same as vanilla tall flowers)
    private static final float TALL_FLOWER_COMPOST_CHANCE = 0.65f;

    public static void register() {
        // Register all flowers for composting
        CompostingChanceRegistry registry = CompostingChanceRegistry.INSTANCE;

        // Pansies
        registry.add(ModBlocks.RED_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.WHITE_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.YELLOW_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.ORANGE_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PINK_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PURPLE_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.BLUE_FROST_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PANOLA_PINK_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.SUNRISE_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.HALLOWEEN_PANSIES, FLOWER_COMPOST_CHANCE);

        // Hydrangeas
        registry.add(ModBlocks.HYDRANGEA, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PINK_HYDRANGEA, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PURPLE_HYDRANGEA, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.RED_HYDRANGEA, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.WHITE_HYDRANGEA, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.HALLOWEEN_HYDRANGEA, FLOWER_COMPOST_CHANCE);

        // Foxgloves (tall flowers)
        registry.add(ModBlocks.WHITE_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PURPLE_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PEACH_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.SUNSET_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.HALLOWEEN_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.CANDY_MOUNTAIN_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.LAVENDER_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);

        // Lupines
        registry.add(ModBlocks.LUPINE_PINK, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.LUPINE_WHITE, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PURPLE_LUPINE, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.GOLDEN_LUPINE, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.SKY_BLUE_LUPINE, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.MANHATTAN_LIGHTS_LUPINE, FLOWER_COMPOST_CHANCE);

        // Liverworts
        registry.add(ModBlocks.BLUE_LIVERWORT, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PURPLE_LIVERWORT, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.WHITE_LIVERWORT, FLOWER_COMPOST_CHANCE);

        // Blanketflowers
        registry.add(ModBlocks.BLANKETFLOWER_1_FLOWER, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.BLANKETFLOWER_DOUBLE, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.BLANKETFLOWER_TRIPLE, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.BLANKETFLOWER_QUAD, FLOWER_COMPOST_CHANCE);

        // Others
        registry.add(ModBlocks.FIREWEED, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.BUTTERFLY_BUSH, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.TWINFLOWER, FLOWER_COMPOST_CHANCE);

        // Crown Cacti
        registry.add(ModBlocks.ORANGE_CROWN_CACTUS, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PINK_CROWN_CACTUS, FLOWER_COMPOST_CHANCE);

        SereneShrubbery.LOGGER.info("Registered composting for all flowers");
    }
}
