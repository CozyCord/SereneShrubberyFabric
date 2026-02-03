package net.sereneshrubbery.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.sereneshrubbery.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(GrassBlock.class)
public class GrassBlockMixin {

    @Unique
    private static final int SPAWN_CHANCE = 6;

    @Inject(method = "grow", at = @At("RETURN"))
    private void sereneShrubbery$addWildFlowers(ServerWorld world, Random random, BlockPos pos, BlockState state, CallbackInfo ci) {
        Optional<RegistryKey<Biome>> biomeKey = world.getBiome(pos).getKey();
        if (biomeKey.isEmpty()) {
            return;
        }

        List<Block> possibleFlowers = sereneShrubbery$getPossibleFlowersForBiome(biomeKey.get());
        if (possibleFlowers.isEmpty()) {
            return;
        }

        if (random.nextInt(SPAWN_CHANCE) != 0) {
            return;
        }

        BlockPos flowerPos = sereneShrubbery$findFlowerPosition(world, pos, random);
        if (flowerPos == null) {
            return;
        }

        Block flower = possibleFlowers.get(random.nextInt(possibleFlowers.size()));
        world.setBlockState(flowerPos, flower.getDefaultState());
    }

    @Unique
    private static List<Block> sereneShrubbery$getPossibleFlowersForBiome(RegistryKey<Biome> biomeKey) {
        List<Block> flowers = new ArrayList<>();

        if (biomeKey == BiomeKeys.DARK_FOREST || biomeKey == BiomeKeys.FLOWER_FOREST ||
            biomeKey == BiomeKeys.FOREST || biomeKey == BiomeKeys.PLAINS) {
            flowers.add(ModBlocks.RED_PANSIES);
        }

        if (biomeKey == BiomeKeys.BIRCH_FOREST || biomeKey == BiomeKeys.FLOWER_FOREST ||
            biomeKey == BiomeKeys.FOREST || biomeKey == BiomeKeys.MEADOW ||
            biomeKey == BiomeKeys.OLD_GROWTH_BIRCH_FOREST || biomeKey == BiomeKeys.PLAINS) {
            flowers.add(ModBlocks.WHITE_PANSIES);
        }

        if (biomeKey == BiomeKeys.BIRCH_FOREST || biomeKey == BiomeKeys.FLOWER_FOREST ||
            biomeKey == BiomeKeys.FOREST || biomeKey == BiomeKeys.OLD_GROWTH_BIRCH_FOREST ||
            biomeKey == BiomeKeys.PLAINS || biomeKey == BiomeKeys.SUNFLOWER_PLAINS) {
            flowers.add(ModBlocks.YELLOW_PANSIES);
        }

        if (biomeKey == BiomeKeys.DARK_FOREST || biomeKey == BiomeKeys.FLOWER_FOREST ||
            biomeKey == BiomeKeys.FOREST || biomeKey == BiomeKeys.MEADOW ||
            biomeKey == BiomeKeys.PLAINS) {
            flowers.add(ModBlocks.PURPLE_PANSIES);
        }

        if (biomeKey == BiomeKeys.BIRCH_FOREST || biomeKey == BiomeKeys.CHERRY_GROVE ||
            biomeKey == BiomeKeys.DARK_FOREST || biomeKey == BiomeKeys.FLOWER_FOREST ||
            biomeKey == BiomeKeys.FOREST || biomeKey == BiomeKeys.OLD_GROWTH_BIRCH_FOREST) {
            flowers.add(ModBlocks.TWINFLOWER);
        }

        return flowers;
    }

    @Unique
    private static BlockPos sereneShrubbery$findFlowerPosition(ServerWorld world, BlockPos grassPos, Random random) {
        List<BlockPos> validPositions = new ArrayList<>();

        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                BlockPos checkPos = grassPos.add(dx, 0, dz);

                if (world.getBlockState(checkPos).isOf(Blocks.GRASS_BLOCK)) {
                    BlockPos abovePos = checkPos.up();
                    BlockState aboveState = world.getBlockState(abovePos);

                    //? if >=1.20.3 {
                    if (aboveState.isOf(Blocks.SHORT_GRASS) || aboveState.isOf(Blocks.FERN)) {
                    //?} else {
                    /*if (aboveState.isOf(Blocks.GRASS) || aboveState.isOf(Blocks.FERN)) {
                    *///?}
                        validPositions.add(abovePos);
                    }
                }
            }
        }

        if (validPositions.isEmpty()) {
            return null;
        }

        return validPositions.get(random.nextInt(validPositions.size()));
    }
}
