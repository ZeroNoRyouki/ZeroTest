package it.zerono.mods.zerotest.common.multiblock.mightyfurnace;

import net.minecraft.util.IStringSerializable;

public enum MightyFurnaceBlockType implements IStringSerializable {

    Wall,
    Power,
    Input,
    Output;

    @Override
    public String getName() {

        return this.toString();
    }
}
