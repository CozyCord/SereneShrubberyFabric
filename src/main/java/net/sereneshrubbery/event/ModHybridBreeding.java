package net.sereneshrubbery.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sereneshrubbery.ModBlocks;
import net.sereneshrubbery.SereneShrubbery;

import java.util.*;

public class ModHybridBreeding {

    private static final Map<Set<Block>, Block> HYBRID_RECIPES = new HashMap<>();
    private static final Map<Set<Block>, Block> HALLOWEEN_RECIPES = new HashMap<>();

    public static void register() {
        initHybridRecipes();

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);

            if (!stack.isOf(Items.BONE_MEAL)) {
                return ActionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            if (!isHybridizableFlower(block)) {
                return ActionResult.PASS;
            }

            //? if <1.21.4 {
            if (!world.isClient) {
            //?} else {
            /*if (!world.isClient()) {
            *///?}
                Block hybrid = tryCreateHybrid(world, pos, block);
                if (hybrid != null) {
                    BlockPos hybridPos = findEmptyAdjacentSpace(world, pos);
                    if (hybridPos != null) {
                        world.setBlockState(hybridPos, hybrid.getDefaultState());

                        if (!player.isCreative()) {
                            stack.decrement(1);
                        }

                        if (world instanceof ServerWorld serverWorld) {
                            serverWorld.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
                                hybridPos.getX() + 0.5, hybridPos.getY() + 0.5, hybridPos.getZ() + 0.5,
                                15, 0.5, 0.5, 0.5, 0.0);

                            if (player instanceof ServerPlayerEntity serverPlayer) {
                                grantHybridAdvancement(serverPlayer, serverWorld);
                            }
                        }

                        world.playSound(null, hybridPos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        return ActionResult.SUCCESS;
                    }
                }
            }

            return ActionResult.PASS;
        });
    }

    private static void initHybridRecipes() {
        // Foxglove hybrids
        addHybridRecipe(ModBlocks.PEACH_FOXGLOVE, ModBlocks.WHITE_FOXGLOVE, ModBlocks.SUNSET_FOXGLOVE);
        addHybridRecipe(ModBlocks.PURPLE_FOXGLOVE, ModBlocks.WHITE_FOXGLOVE, ModBlocks.LAVENDER_FOXGLOVE);
        addHybridRecipe(ModBlocks.PEACH_FOXGLOVE, ModBlocks.PURPLE_FOXGLOVE, ModBlocks.CANDY_MOUNTAIN_FOXGLOVE);

        // Lupine hybrids
        addHybridRecipe(ModBlocks.LUPINE_WHITE, ModBlocks.LUPINE_PINK, ModBlocks.GOLDEN_LUPINE);
        addHybridRecipe(ModBlocks.LUPINE_WHITE, ModBlocks.PURPLE_LUPINE, ModBlocks.SKY_BLUE_LUPINE);
        addHybridRecipe(ModBlocks.LUPINE_PINK, ModBlocks.PURPLE_LUPINE, ModBlocks.MANHATTAN_LIGHTS_LUPINE);

        // Pansy hybrids
        addHybridRecipe(ModBlocks.RED_PANSIES, ModBlocks.YELLOW_PANSIES, ModBlocks.ORANGE_PANSIES);
        addHybridRecipe(ModBlocks.RED_PANSIES, ModBlocks.WHITE_PANSIES, ModBlocks.PINK_PANSIES);
        addHybridRecipe(ModBlocks.WHITE_PANSIES, ModBlocks.PURPLE_PANSIES, ModBlocks.BLUE_FROST_PANSIES);
        addHybridRecipe(ModBlocks.ORANGE_PANSIES, ModBlocks.YELLOW_PANSIES, ModBlocks.SUNRISE_PANSIES);
        addHybridRecipe(ModBlocks.PINK_PANSIES, ModBlocks.PURPLE_PANSIES, ModBlocks.PANOLA_PINK_PANSIES);

        // Hydrangea hybrids
        addHybridRecipe(ModBlocks.RED_HYDRANGEA, ModBlocks.WHITE_HYDRANGEA, ModBlocks.PINK_HYDRANGEA);

        // Halloween hybrids (require light level <= 4)
        addHalloweenRecipe(ModBlocks.PURPLE_PANSIES, ModBlocks.ORANGE_PANSIES, ModBlocks.HALLOWEEN_PANSIES);
        addHalloweenRecipe(ModBlocks.PURPLE_FOXGLOVE, ModBlocks.SUNSET_FOXGLOVE, ModBlocks.HALLOWEEN_FOXGLOVE);
        addHalloweenRecipe(ModBlocks.PURPLE_HYDRANGEA, ModBlocks.RED_HYDRANGEA, ModBlocks.HALLOWEEN_HYDRANGEA);
    }

    private static void addHybridRecipe(Block parent1, Block parent2, Block hybrid) {
        Set<Block> parents = new HashSet<>();
        parents.add(parent1);
        parents.add(parent2);
        HYBRID_RECIPES.put(parents, hybrid);
    }

    private static void addHalloweenRecipe(Block parent1, Block parent2, Block hybrid) {
        Set<Block> parents = new HashSet<>();
        parents.add(parent1);
        parents.add(parent2);
        HALLOWEEN_RECIPES.put(parents, hybrid);
    }

    private static boolean isHybridizableFlower(Block block) {
        return ModBlocks.getAllBlocks().contains(block);
    }

    private static Block tryCreateHybrid(World world, BlockPos pos, Block centerFlower) {
        BlockPos[] adjacentPositions = {
            pos.north(), pos.south(), pos.east(), pos.west(),
            pos.north().east(), pos.north().west(),
            pos.south().east(), pos.south().west()
        };

        for (BlockPos adjPos : adjacentPositions) {
            Block adjacentFlower = world.getBlockState(adjPos).getBlock();

            if (!isHybridizableFlower(adjacentFlower)) {
                continue;
            }

            Set<Block> combination = new HashSet<>();
            combination.add(centerFlower);
            combination.add(adjacentFlower);

            int lightLevel = world.getLightLevel(pos);

            Block halloweenHybrid = HALLOWEEN_RECIPES.get(combination);
            if (halloweenHybrid != null) {
                if (lightLevel <= 4) {
                    if (world.getRandom().nextFloat() < 0.5f) {
                        return halloweenHybrid;
                    }
                }
                continue;
            }

            Block hybrid = HYBRID_RECIPES.get(combination);
            if (hybrid != null) {
                if (world.getRandom().nextFloat() < 0.5f) {
                    return hybrid;
                }
            }
        }

        return null;
    }

    private static BlockPos findEmptyAdjacentSpace(World world, BlockPos centerPos) {
        BlockPos[] positions = {
            centerPos.north(), centerPos.south(), centerPos.east(), centerPos.west(),
            centerPos.north().east(), centerPos.north().west(),
            centerPos.south().east(), centerPos.south().west()
        };

        List<BlockPos> validPositions = new ArrayList<>();

        for (BlockPos pos : positions) {
            BlockState state = world.getBlockState(pos);
            BlockState below = world.getBlockState(pos.down());

            if (state.isAir() && isValidGround(below)) {
                validPositions.add(pos);
            }
        }

        if (validPositions.isEmpty()) {
            return null;
        }

        return validPositions.get(world.getRandom().nextInt(validPositions.size()));
    }

    private static boolean isValidGround(BlockState state) {
        Block block = state.getBlock();
        return block == Blocks.GRASS_BLOCK ||
               block == Blocks.DIRT ||
               block == Blocks.COARSE_DIRT ||
               block == Blocks.PODZOL ||
               block == Blocks.FARMLAND ||
               block == Blocks.ROOTED_DIRT ||
               block == Blocks.MOSS_BLOCK;
    }

    private static void grantHybridAdvancement(ServerPlayerEntity player, ServerWorld serverWorld) {
        try {
            grantAdvancement(player, serverWorld, "hybrid_flora");
            grantAdvancement(player, serverWorld, "gardeners_path");
        } catch (Exception e) {
            SereneShrubbery.LOGGER.debug("Could not grant hybrid advancement: " + e.getMessage());
        }
    }

    private static void grantAdvancement(ServerPlayerEntity player, ServerWorld serverWorld, String advancementName) {
        //? if >=1.21 {
        Identifier id = Identifier.of(SereneShrubbery.MOD_ID, advancementName);
        //?} else {
        /*Identifier id = new Identifier(SereneShrubbery.MOD_ID, advancementName);
        *///?}

        var server = serverWorld.getServer();
        var advancementEntry = server.getAdvancementLoader().get(id);
        if (advancementEntry != null) {
            AdvancementProgress progress = player.getAdvancementTracker().getProgress(advancementEntry);
            if (!progress.isDone()) {
                for (String criterion : progress.getUnobtainedCriteria()) {
                    player.getAdvancementTracker().grantCriterion(advancementEntry, criterion);
                }
            }
        }
    }
}
