package zero.mods.zerotest.common;

import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zero.mods.zerotest.References;
import zero.mods.zerotest.common.block.TestBlockBase;

public class CommonProxy {

    public TestBlockBase register(TestBlockBase block) {

        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        return block;
    }

    public void register(Class<? extends TileEntity> tileEntityClass) {

        GameRegistry.registerTileEntity(tileEntityClass, References.MOD_ID + tileEntityClass.getSimpleName());
    }
}
