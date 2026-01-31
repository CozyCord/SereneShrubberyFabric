package net.sereneshrubbery.block;

//? if >=1.21 {
/*import com.mojang.serialization.MapCodec;
*///?}
//? if >=1.21 {
/*import net.minecraft.block.AbstractBlock;
*///?}
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
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import net.sereneshrubbery.particle.ModParticleTypes;

/**
 * Butterfly Bush block that spawns butterfly particles randomly.
 */
public class ButterflyBushBlock extends PlantBlock {
    //? if >=1.21 {
    /*public static final MapCodec<ButterflyBushBlock> CODEC = createCodec(ButterflyBushBlock::new);
    *///?}

    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 14.0, 14.0);

    //? if >=1.21 {
    /*public ButterflyBushBlock(Settings settings) {
        super(settings
            .sounds(BlockSoundGroup.GRASS)
            .noCollision()
            .breakInstantly()
            //? if >=1.21.2 {
            /^.dropsNothing()
            ^///?}
        );
    }
    *///?} else {
    public ButterflyBushBlock() {
        super(Settings.copy(Blocks.POPPY)
            .sounds(BlockSoundGroup.GRASS)
            .noCollision()
            .breakInstantly());
    }
    //?}

    @Override
    //? if >=1.21.2 {
    /*protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    *///?} else {
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    //?}
        return SHAPE;
    }

    //? if <1.21.2 {
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // Spawn butterfly particles randomly
        if (random.nextInt(5) == 0) {
            double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
            double y = pos.getY() + 0.5 + random.nextDouble() * 0.5;
            double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.8;

            double velocityX = (random.nextDouble() - 0.5) * 0.05;
            double velocityY = random.nextDouble() * 0.05;
            double velocityZ = (random.nextDouble() - 0.5) * 0.05;

            world.addParticle(ModParticleTypes.BUTTERFLY_BUSH_PARTICLE, x, y, z, velocityX, velocityY, velocityZ);
        }
    }
    //?} elif >=1.21.2 && <1.21.4 {
    /*@Override
    protected void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // Spawn butterfly particles randomly
        if (random.nextInt(5) == 0) {
            double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
            double y = pos.getY() + 0.5 + random.nextDouble() * 0.5;
            double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.8;

            double velocityX = (random.nextDouble() - 0.5) * 0.05;
            double velocityY = random.nextDouble() * 0.05;
            double velocityZ = (random.nextDouble() - 0.5) * 0.05;

            world.addParticle(ModParticleTypes.BUTTERFLY_BUSH_PARTICLE, x, y, z, velocityX, velocityY, velocityZ);
        }
    }
    *///?} elif >=1.21.4 && <1.21.9 {
    /*@Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // Spawn butterfly particles randomly
        if (random.nextInt(5) == 0) {
            double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
            double y = pos.getY() + 0.5 + random.nextDouble() * 0.5;
            double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.8;

            double velocityX = (random.nextDouble() - 0.5) * 0.05;
            double velocityY = random.nextDouble() * 0.05;
            double velocityZ = (random.nextDouble() - 0.5) * 0.05;

            world.addParticle(ModParticleTypes.BUTTERFLY_BUSH_PARTICLE, x, y, z, velocityX, velocityY, velocityZ);
        }
    }
    *///?} else {
    /*@Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // Spawn butterfly particles randomly
        if (random.nextInt(5) == 0) {
            double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
            double y = pos.getY() + 0.5 + random.nextDouble() * 0.5;
            double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.8;

            double velocityX = (random.nextDouble() - 0.5) * 0.05;
            double velocityY = random.nextDouble() * 0.05;
            double velocityZ = (random.nextDouble() - 0.5) * 0.05;

            world.addParticleClient(ModParticleTypes.BUTTERFLY_BUSH_PARTICLE, x, y, z, velocityX, velocityY, velocityZ);
        }
    }
    *///?}

    //? if >=1.21 {
    /*@Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }
    *///?}

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
}
