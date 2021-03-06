package it.zerono.mods.zerotest;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import it.zerono.mods.zerotest.common.CommonProxy;
import org.apache.logging.log4j.Logger;

@Mod(modid = References.MOD_ID, acceptedMinecraftVersions = References.MOD_MCVERSION,
        dependencies = References.MOD_DEPENDENCIES, version = References.MOD_VERSION)
public class ZeroTest {

    public static Logger LOGGER;

    public static ZeroTest getInstance() {

        return ZeroTest.s_instance;
    }

    public static CommonProxy getProxy() {

        return ZeroTest.s_proxy;
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {

        LOGGER = event.getModLog();
        ZeroTest.getProxy().onPreInit(event);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        ZeroTest.getProxy().onInit(event);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        ZeroTest.getProxy().onPostInit(event);
    }

    public static final CreativeTabs TEST_TAB = new CreativeTabs("zerotest.common.creativetab") {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return Items.SKULL;
        }
    };

    @Mod.Instance
    private static ZeroTest s_instance;

    @SidedProxy(serverSide = References.PROXY_COMMON, clientSide = References.PROXY_CLIENT)
    private static CommonProxy s_proxy;
}
