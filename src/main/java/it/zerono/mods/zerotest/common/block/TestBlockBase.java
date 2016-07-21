package it.zerono.mods.zerotest.common.block;

import it.zerono.mods.zerotest.ZeroTest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TestBlockBase extends Block {

    public TestBlockBase(String blockName, Material material) {

        super(material);
        this.setRegistryName(blockName);
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setCreativeTab(ZeroTest.TEST_TAB);
    }
}
