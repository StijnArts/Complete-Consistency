package drai.dev.complete_consistency;

import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.materials.*;

public class BlockFactory<T extends BlockMaterial> {
    public String blockName;
    public BlockConsumer<T> consumer;

    public BlockFactory(String blockName, BlockConsumer<T> consumer) {
        this.blockName = blockName;
        this.consumer = consumer;
    }
}
