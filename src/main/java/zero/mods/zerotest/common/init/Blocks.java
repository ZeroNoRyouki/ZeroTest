package zero.mods.zerotest.common.init;

import zero.mods.zerotest.ZeroTest;
import zero.mods.zerotest.common.CommonProxy;
import zero.mods.zerotest.common.block.ctx.OrnatedEmeraldBlock;
import zero.mods.zerotest.common.multiblock.mightyfurnace.*;

public final class Blocks {

    // ZeroCore multiblock API test
    public static final MightyFurnaceBlockWall mightyFurnaceWall;
    public static final MightyFurnaceBlockPort mightyFurnacePowerPort;
    public static final MightyFurnaceBlockPort mightyFurnaceInputPort;
    public static final MightyFurnaceBlockPort mightyFurnaceOutputPort;

    // connected textures test
    public static final OrnatedEmeraldBlock ornatedEmeraldBlock;

    public static void initialize() {
    }

    static {

        CommonProxy proxy = ZeroTest.getProxy();

        // register blocks

        // - ZeroCore multiblock API test
        mightyFurnaceWall = (MightyFurnaceBlockWall)proxy.register(new MightyFurnaceBlockWall("mightyFurnaceWall"));
        mightyFurnacePowerPort = (MightyFurnaceBlockPort)proxy.register(new MightyFurnaceBlockPort("mightyFurnacePowerPort", MightyFurnaceBlockType.Power));
        mightyFurnaceInputPort = (MightyFurnaceBlockPort)proxy.register(new MightyFurnaceBlockPort("mightyFurnaceInputPort", MightyFurnaceBlockType.Input));
        mightyFurnaceOutputPort = (MightyFurnaceBlockPort)proxy.register(new MightyFurnaceBlockPort("mightyFurnaceOutputPort", MightyFurnaceBlockType.Output));

        // - connected textures test
        ornatedEmeraldBlock = (OrnatedEmeraldBlock)proxy.register(new OrnatedEmeraldBlock("ornatedEmeraldBlock"));

        // register tile entities

        proxy.register(MightyFurnaceTileEntity.class);
        proxy.register(MightyFurnacePowerTileEntity.class);
        proxy.register(MightyFurnaceIOPortTileEntity.class);
    }

    private Blocks() {
    }
}