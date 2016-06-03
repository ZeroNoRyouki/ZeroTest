package zero.mods.zerotest.common.multiblock.mightyfurnace;

import com.google.common.collect.Lists;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import zero.mods.zerocore.api.multiblock.IMultiblockPart;
import zero.mods.zerocore.lib.BlockFacings;
import zero.mods.zerocore.lib.PropertyBlockFacings;
import java.util.ArrayList;

public class MightyFurnaceBlockWall extends MightyFurnaceBlockBase {

    public MightyFurnaceBlockWall(String name) {

        super(name, MightyFurnaceBlockType.Wall);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACES, PropertyBlockFacings.All));
    }

    /*
    @Override
    public boolean onBlockActivated(World world, BlockPos position, IBlockState state, EntityPlayer player,
                                    EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

        IMultiblockPart tile = this.getMultiblockPartAt(world, position);

        if (tile instanceof MightyFurnaceTileEntity) {

            MightyFurnaceTileEntity part = (MightyFurnaceTileEntity)tile;
            MightyFurnaceController controller = (MightyFurnaceController)part.getMultiblockController();

            if (null == controller) {
                FMLLog.warning("WALL - got null controller!");
                player.addChatMessage(new TextComponentString(String.format("CONTROLLER IS NULL ON %s",
                        world.isRemote ? "CLIENT" : "SERVER")));
            }

            if ((null != controller) && controller.isAssembled()) {

                FMLLog.warning("WALL - onBlockActivated: controller report machine is assembled!");

                if (!world.isRemote) {

                    world.notifyBlockUpdate(position, state, state, 3);

                    int stored = 0; // controller.getEnergyStored(EnumFacing.EAST);
                    player.addChatMessage(new TextComponentString(String.format("MB - energy stored = %d", stored)));

                    return true;
                }

                world.notifyBlockUpdate(position, state, state, 3);

                / *
                int energy = controller.getEnergyStored(EnumFacing.UP);

                player.addChatMessage(new ChatComponentText(String.format("Energy stored = %d RF", energy)));
                * /
                return true;
            } else {

                FMLLog.warning((null != controller) ? "WALL - onBlockActivated: controller report machine is NOT assembled!" : "null controller!");
            }
        }

        return false;
    }
    */
    /*
    @Override
    public String getBlockNameStateSuffix(ItemStack stack) {
        return null;
    }
    */

    @Override
    public int getMetaFromState(IBlockState state) {

        return 0;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos position) {

        IMultiblockPart part = this.getMultiblockPartAt(world, position);

        if (part instanceof MightyFurnaceTileEntity) {

            MightyFurnaceTileEntity wallTile = (MightyFurnaceTileEntity)part;
            boolean assembled = wallTile.isConnected() && wallTile.getMultiblockController().isAssembled();
            BlockFacings facings = assembled ? wallTile.getOutwardsDir() : BlockFacings.ALL;

            state = state.withProperty(FACES, facings.toProperty());

            // active icon hack

            if (wallTile.getPartPosition().isFace()) {

                MightyFurnaceController controller = (MightyFurnaceController)wallTile.getMultiblockController();
                boolean active = controller.isAssembled() && controller.isActive();

                if (active)
                    state = state.withProperty(FACES, PropertyBlockFacings.Opposite_EW);
            }
        }

        return state;
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, new IProperty[] {FACES});
    }

    private final static PropertyEnum FACES ;

    static {

        ArrayList<PropertyBlockFacings> values = Lists.newArrayList();

        values.addAll(PropertyBlockFacings.ALL_AND_NONE);
        values.addAll(PropertyBlockFacings.FACES);
        values.addAll(PropertyBlockFacings.ANGLES);
        values.addAll(PropertyBlockFacings.CORNERS);
        values.add(PropertyBlockFacings.Opposite_EW);

        FACES = PropertyEnum.create("faces", PropertyBlockFacings.class, values);
    }
}
