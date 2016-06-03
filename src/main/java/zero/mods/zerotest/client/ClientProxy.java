package zero.mods.zerotest.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import zero.mods.zerocore.api.multiblock.MultiblockClientTickHandler;
import zero.mods.zerotest.common.CommonProxy;
import zero.mods.zerotest.common.block.TestBlockBase;

public class ClientProxy extends CommonProxy {

    @Override
    public TestBlockBase register(TestBlockBase block) {

        super.register(block);

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                0, new ModelResourceLocation(block.getRegistryName(), "inventory"));

        return block;
    }

    @Override
    public void onInit(FMLInitializationEvent event) {

        super.onInit(event);
        MinecraftForge.EVENT_BUS.register(new MultiblockClientTickHandler());
    }
}
