package zero.mods.zerotest.common;

import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zero.mods.zerocore.api.multiblock.MultiblockEventHandler;
import zero.mods.zerocore.api.multiblock.MultiblockServerTickHandler;
import zero.mods.zerotest.References;
import zero.mods.zerotest.ZeroTest;
import zero.mods.zerotest.common.block.TestBlockBase;
import zero.mods.zerotest.common.init.Blocks;

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

        // register multiblock API event handler
        MinecraftForge.EVENT_BUS.register(new MultiblockEventHandler());

        MinecraftForge.EVENT_BUS.register(new MultiblockServerTickHandler());
    }

    public void onPostInit(FMLPostInitializationEvent event) {
    }
}
