package it.zerono.mods.zerotest.client;

import it.zerono.mods.zerotest.common.block.TestBlockBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import it.zerono.mods.zerotest.common.CommonProxy;

public class ClientProxy extends CommonProxy {

    @Override
    public TestBlockBase register(TestBlockBase block) {

        super.register(block);

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                0, new ModelResourceLocation(block.getRegistryName(), "inventory"));

        return block;
    }
}
