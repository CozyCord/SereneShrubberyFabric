package net.sereneshrubbery.block;

//? if >=1.21 {
import com.mojang.serialization.MapCodec;
//?}
//? if >=1.21 {
import net.minecraft.block.AbstractBlock;
//?}
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.sereneshrubbery.ModBlocks;
import net.sereneshrubbery.event.ModHybridBreeding;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ModFlowerBlock extends PlantBlock implements Fertilizable {
    //? if >=1.21 {
    public static final MapCodec<ModFlowerBlock> CODEC = createCodec(ModFlowerBlock::new);
    //?}
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 14.0, 14.0);

    //? if >=1.21 {
    public ModFlowerBlock(Settings settings) {
        super(settings
            .sounds(BlockSoundGroup.GRASS)
            .noCollision()
            .breakInstantly()
            .ticksRandomly()
            //? if >=1.21.2 {
            /*.dropsNothing()
            *///?}
        );
    }
    //?} else {
    /*public ModFlowerBlock() {
        super(Settings.copy(Blocks.POPPY)
            .sounds(BlockSoundGroup.GRASS)
            .noCollision()
            .breakInstantly()
            .ticksRandomly());
    }
    *///?}

    @Override
    //? if >=1.21.2 {
    /*protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    *///?} else {
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    //?}
        return SHAPE;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.GRASS_BLOCK) ||
               floor.isOf(Blocks.DIRT) ||
               floor.isOf(Blocks.COARSE_DIRT) ||
               floor.isOf(Blocks.PODZOL) ||
               floor.isOf(Blocks.FARMLAND) ||
               floor.isOf(Blocks.ROOTED_DIRT) ||
               floor.isOf(Blocks.MUD) ||
               floor.isOf(Blocks.MUDDY_MANGROVE_ROOTS) ||
               floor.isOf(Blocks.MOSS_BLOCK);
    }

    @Override
    //? if >=1.21.2 {
    /*protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    *///?} else {
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    //?}
        ModHybridBreeding.tryBreedOnRandomTick(world, pos, state.getBlock());
    }

    //? if >=1.21 {
    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }
    //?}

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        //? if <1.21.4 {
        if (!world.isClient && !player.isCreative()) {
        //?} else {
        /*if (!world.isClient() && !player.isCreative()) {
        *///?}
            dropStack(world, pos, new ItemStack(this.asItem()));
        }
    }

    @Override
    //? if >=1.20.2 {
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
    //?} else {
    /*public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
    *///?}
        return ModBlocks.isHybridFlower(state.getBlock());
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return ModBlocks.isHybridFlower(state.getBlock()) && findEmptyAdjacentSpace(world, pos) != null;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos emptyPos = findEmptyAdjacentSpace(world, pos);
        if (emptyPos != null) {
            world.setBlockState(emptyPos, state.getBlock().getDefaultState());

            world.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
                emptyPos.getX() + 0.5, emptyPos.getY() + 0.5, emptyPos.getZ() + 0.5,
                10, 0.3, 0.3, 0.3, 0.0);

            world.playSound(null, emptyPos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0f, 1.2f);
        }
    }

    private BlockPos findEmptyAdjacentSpace(World world, BlockPos centerPos) {
        BlockPos[] positions = {
            centerPos.north(), centerPos.south(), centerPos.east(), centerPos.west(),
            centerPos.north().east(), centerPos.north().west(),
            centerPos.south().east(), centerPos.south().west()
        };

        List<BlockPos> validPositions = new ArrayList<>();

        for (BlockPos checkPos : positions) {
            BlockState checkState = world.getBlockState(checkPos);
            BlockState below = world.getBlockState(checkPos.down());

            if (checkState.isAir() && canPlantOnTop(below, world, checkPos.down())) {
                validPositions.add(checkPos);
            }
        }

        if (validPositions.isEmpty()) {
            return null;
        }

        return validPositions.get(world.getRandom().nextInt(validPositions.size()));
    }
}
