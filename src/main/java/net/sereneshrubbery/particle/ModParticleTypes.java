package net.sereneshrubbery.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
//? if >=1.21 {
import net.minecraft.particle.SimpleParticleType;
//?} else {
/*import net.minecraft.particle.DefaultParticleType;
*///?}
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.sereneshrubbery.SereneId;
import net.sereneshrubbery.SereneShrubbery;

public class ModParticleTypes {
    //? if >=1.21 {
    public static final SimpleParticleType BUTTERFLY_BUSH_PARTICLE = FabricParticleTypes.simple();
    //?} else {
    /*public static final DefaultParticleType BUTTERFLY_BUSH_PARTICLE = FabricParticleTypes.simple();
    *///?}

    public static void register() {
        Registry.register(Registries.PARTICLE_TYPE, SereneId.of("butterfly_bush_particle"), BUTTERFLY_BUSH_PARTICLE);
        SereneShrubbery.LOGGER.info("Registered particle types");
    }
}
