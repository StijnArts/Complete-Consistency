package drai.dev.complete_consistency.mixin.accessors;

import net.minecraft.world.level.block.state.properties.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;
@Mixin({WoodType.class})
public interface WoodTypeAccessor {
    @Invoker("<init>")
    static WoodType invokeConstructor(String name, BlockSetType blockSetType) {
        throw new AssertionError();
    }

    @Invoker("register")
    static WoodType invokeRegister(WoodType type) {
        throw new AssertionError();
    }
}
