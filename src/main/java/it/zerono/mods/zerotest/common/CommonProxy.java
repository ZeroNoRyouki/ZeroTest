package it.zerono.mods.zerotest.common;

import it.zerono.mods.zerotest.common.block.TestBlockBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import it.zerono.mods.zerotest.References;
import it.zerono.mods.zerotest.common.init.Blocks;

public class CommonProxy {

    public TestBlockBase register(TestBlockBase block) {

        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        return block;
    }

    public void register(Class<? extends TileEntity> tileEntityClass) {
        GameRegistry.registerTileEntity(tileEntityClass, References.MOD_ID + tileEntityClass.getSimpleName());
    }

    public void onPreInit(FMLPreInitializationEvent event) {
        // register blocks
        Blocks.initialize();
    }

    public void onInit(FMLInitializationEvent event) {

    }

    public void onPostInit(FMLPostInitializationEvent event) {
    }
}
