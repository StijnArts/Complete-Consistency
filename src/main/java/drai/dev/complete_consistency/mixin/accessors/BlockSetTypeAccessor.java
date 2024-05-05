package drai.dev.complete_consistency.mixin.accessors;

import net.minecraft.sounds.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({BlockSetType.class})
public interface BlockSetTypeAccessor {
    @Invoker("<init>")
    static BlockSetType invokeConstructor(String name) {
        throw new AssertionError();
    }
    @Invoker("<init>")
    static BlockSetType invokeConstructor(String name,
                                                 boolean canOpenByHand,
                                                 boolean canOpenByWindCharge,
                                                 boolean canButtonBeActivatedByArrows,
                                                 BlockSetType.PressurePlateSensitivity pressurePlateSensitivity,
                                                 SoundType soundType,
                                                 SoundEvent doorClose,
                                                 SoundEvent doorOpen,
                                                 SoundEvent trapdoorClose,
                                                 SoundEvent trapdoorOpen,
                                                 SoundEvent pressurePlateClickOff,
                                                 SoundEvent pressurePlateClickOn,
                                                 SoundEvent buttonClickOff,
                                                 SoundEvent buttonClickOn) {
        throw new AssertionError();
    }

    @Invoker("register")
    public static BlockSetType invokeRegister(BlockSetType type) {
        throw new AssertionError();
    }
}
