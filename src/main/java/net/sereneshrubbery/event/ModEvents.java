package net.sereneshrubbery.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sereneshrubbery.ModBlocks;

public class ModEvents {

    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);

            if (!stack.isOf(Items.BONE_MEAL)) {
                return ActionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            Block nextStage = getNextBlanketflowerStage(block);
            if (nextStage == null) {
                return ActionResult.PASS;
            }

            //? if <1.21.4 {
            if (!world.isClient) {
            //?} else {
            /*if (!world.isClient()) {
            *///?}
                world.setBlockState(pos, nextStage.getDefaultState());

                if (!player.isCreative()) {
                    stack.decrement(1);
                }

                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        10, 0.3, 0.3, 0.3, 0.0);
                }
            }

            world.playSound(player, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);

            return ActionResult.SUCCESS;
        });
    }

    private static Block getNextBlanketflowerStage(Block current) {
        if (current == ModBlocks.BLANKETFLOWER_1_FLOWER) {
            return ModBlocks.BLANKETFLOWER_DOUBLE;
        } else if (current == ModBlocks.BLANKETFLOWER_DOUBLE) {
            return ModBlocks.BLANKETFLOWER_TRIPLE;
        } else if (current == ModBlocks.BLANKETFLOWER_TRIPLE) {
            return ModBlocks.BLANKETFLOWER_QUAD;
        }
        return null;
    }
}
