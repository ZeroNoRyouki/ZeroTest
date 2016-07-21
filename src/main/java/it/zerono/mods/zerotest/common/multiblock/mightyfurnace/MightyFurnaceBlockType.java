package it.zerono.mods.zerotest.common.multiblock.mightyfurnace;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

public enum MightyFurnaceBlockType implements IStringSerializable {

    Wall,
    Power,
    Input,
    Output;

    public int toMeta() {

        return this.ordinal();
    }

    public static MightyFurnaceBlockType getTypeFromMeta(int meta) {

        MightyFurnaceBlockType[] values = MightyFurnaceBlockType.values();

        return ((meta >= 0) && (meta < values.length)) ? values[meta] : MightyFurnaceBlockType.Wall;
    }

    @Override
    public String getName() {

        return this.toString();
    }

    public static final PropertyEnum TYPE = PropertyEnum.create("type", MightyFurnaceBlockType.class);
}
