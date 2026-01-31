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
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModFlowerBlock extends PlantBlock {
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
            .breakInstantly());
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

    //? if >=1.21 {
    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }
    //?}

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        // Explicitly drop the block item if not in creative mode
        //? if <1.21.4 {
        if (!world.isClient && !player.isCreative()) {
        //?} else {
        /*if (!world.isClient() && !player.isCreative()) {
        *///?}
            dropStack(world, pos, new ItemStack(this.asItem()));
        }
    }
}
