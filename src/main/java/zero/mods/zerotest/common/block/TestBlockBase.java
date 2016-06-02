package zero.mods.zerotest.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import zero.mods.zerotest.References;
import zero.mods.zerotest.ZeroTest;

public class TestBlockBase extends Block {

    public TestBlockBase(String blockName, Material material) {

        super(material);
        this.setRegistryName(blockName);
        this.setUnlocalizedName(References.MOD_ID + ':' + blockName);
        this.setCreativeTab(ZeroTest.TEST_TAB);
    }
}
