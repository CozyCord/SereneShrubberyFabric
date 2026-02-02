package net.sereneshrubbery.data;

//? if >=1.21.11 {
/*import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.PersistentStateType;
*///?}
import net.minecraft.nbt.NbtCompound;
//? if >=1.21 {
import net.minecraft.registry.RegistryWrapper;
//?}
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;

public class BreedingTimerState extends PersistentState {
    private static final String DATA_NAME = "serene_shrubbery_breeding_timers";

    private final Map<String, Long> breedingTimers;

    public BreedingTimerState() {
        this.breedingTimers = new HashMap<>();
    }

    //? if >=1.21.11 {
    /*public BreedingTimerState(Map<String, Long> timers) {
        this.breedingTimers = new HashMap<>(timers);
    }

    public static final Codec<BreedingTimerState> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            Codec.unboundedMap(Codec.STRING, Codec.LONG).fieldOf("timers").forGetter(state -> state.breedingTimers)
        ).apply(instance, BreedingTimerState::new)
    );

    private static final PersistentStateType<BreedingTimerState> TYPE = new PersistentStateType<>(
        DATA_NAME,
        BreedingTimerState::new,
        CODEC,
        null
    );

    public static BreedingTimerState getServerState(ServerWorld world) {
        BreedingTimerState state = world.getPersistentStateManager().getOrCreate(TYPE);
        state.markDirty();
        return state;
    }
    *///?} elif >=1.21 {
    public static BreedingTimerState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        BreedingTimerState state = new BreedingTimerState();
        NbtCompound timersNbt = nbt.getCompound("timers");
        for (String key : timersNbt.getKeys()) {
            state.breedingTimers.put(key, timersNbt.getLong(key));
        }
        return state;
    }

    private static final Type<BreedingTimerState> TYPE = new Type<>(
        BreedingTimerState::new,
        BreedingTimerState::fromNbt,
        null
    );

    public static BreedingTimerState getServerState(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(TYPE, DATA_NAME);
    }
    //?} else {
    /*public static BreedingTimerState fromNbt(NbtCompound nbt) {
        BreedingTimerState state = new BreedingTimerState();
        NbtCompound timersNbt = nbt.getCompound("timers");
        for (String key : timersNbt.getKeys()) {
            state.breedingTimers.put(key, timersNbt.getLong(key));
        }
        return state;
    }

    public static BreedingTimerState getServerState(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(
            BreedingTimerState::fromNbt,
            BreedingTimerState::new,
            DATA_NAME
        );
    }
    *///?}

    //? if <1.21.11 {
    @Override
    //? if >=1.21 {
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
    //?} else {
    /*public NbtCompound writeNbt(NbtCompound nbt) {
    *///?}
        NbtCompound timersNbt = new NbtCompound();
        for (Map.Entry<String, Long> entry : breedingTimers.entrySet()) {
            timersNbt.putLong(entry.getKey(), entry.getValue());
        }
        nbt.put("timers", timersNbt);
        return nbt;
    }
    //?}

    public Map<String, Long> getBreedingTimers() {
        return breedingTimers;
    }

    public void setTimer(String key, long time) {
        breedingTimers.put(key, time);
        markDirty();
    }

    public Long getTimer(String key) {
        return breedingTimers.get(key);
    }

    public void removeTimer(String key) {
        if (breedingTimers.remove(key) != null) {
            markDirty();
        }
    }

    public boolean hasTimer(String key) {
        return breedingTimers.containsKey(key);
    }
}
