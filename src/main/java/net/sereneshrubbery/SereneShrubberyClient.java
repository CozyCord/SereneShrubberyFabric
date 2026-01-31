package net.sereneshrubbery;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.sereneshrubbery.particle.ButterflyBushParticle;
import net.sereneshrubbery.particle.ModParticleTypes;
//? if <1.21.6 {
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
//?} else {
/*import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
*///?}

public class SereneShrubberyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //? if <1.21.6 {
        ModBlocks.getAllBlocks().forEach(block ->
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout())
        );
        //?} else {
        /*ModBlocks.getAllBlocks().forEach(block ->
            BlockRenderLayerMap.putBlock(block, BlockRenderLayer.CUTOUT)
        );
        *///?}

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.BUTTERFLY_BUSH_PARTICLE, ButterflyBushParticle.Factory::new);

        SereneShrubbery.LOGGER.info("Serene Shrubbery client initialized");
    }
}
