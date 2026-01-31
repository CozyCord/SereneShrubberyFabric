package net.sereneshrubbery;

import net.minecraft.util.Identifier;

public class SereneId {
    public static Identifier of(String path) {
        //? if >=1.21 {
        return Identifier.of(SereneShrubbery.MOD_ID, path);
        //?} else {
        /*return new Identifier(SereneShrubbery.MOD_ID, path);
        *///?}
    }

    public static Identifier of(String namespace, String path) {
        //? if >=1.21 {
        return Identifier.of(namespace, path);
        //?} else {
        /*return new Identifier(namespace, path);
        *///?}
    }
}
