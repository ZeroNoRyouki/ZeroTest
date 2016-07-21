package it.zerono.mods.zerotest.common.multiblock.mightyfurnace;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;

public class MightyFurnaceBlockPort extends MightyFurnaceBlockBase {

    public static final PropertyBool ASSEMBLED = PropertyBool.create("assembled");
    public static final PropertyDirection HFACING = PropertyDirection.create("hfacing", EnumFacing.Plane.HORIZONTAL);

    public MightyFurnaceBlockPort(String name, MightyFurnaceBlockType portType) {

        super(name, portType);

        if (MightyFurnaceBlockType.Wall == portType)
            throw new IllegalArgumentException("Invalid port type");

        this.setDefaultState(this.blockState.getBaseState().withProperty(HFACING, EnumFacing.NORTH).withProperty(ASSEMBLED, false));
    }
    /*
    @Override
    public String getBlockNameStateSuffix(ItemStack stack) {
        return null;
    }
    */

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {

        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (EnumFacing.Axis.Y == enumfacing.getAxis())
            enumfacing = EnumFacing.NORTH;

        return this.getDefaultState().withProperty(HFACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {

        return ((EnumFacing)state.getValue(HFACING)).getIndex();
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos position) {

        IMultiblockPart part = this.getMultiblockPartAt(world, position);

        if (part instanceof MightyFurnaceTileEntity) {

            MightyFurnaceTileEntity wallTile = (MightyFurnaceTileEntity)part;
            boolean assembled = wallTile.isConnected() && wallTile.getMultiblockController().isAssembled();

            state = state.withProperty(ASSEMBLED, assembled);

            if (assembled) {

                switch (wallTile.getPartPosition()) {

                    case NorthFace:
                        state = state.withProperty(HFACING, EnumFacing.NORTH);
                        break;

                    case SouthFace:
                        state = state.withProperty(HFACING, EnumFacing.SOUTH);
                        break;

                    case WestFace:
                        state = state.withProperty(HFACING, EnumFacing.WEST);
                        break;

                    case EastFace:
                        state = state.withProperty(HFACING, EnumFacing.EAST);
                        break;
                }
            }
        }

        return state;
    }

    /***
     * Called before a block is placed in the world to generate the block state for the block
     */
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
                                     int meta, EntityLivingBase placer) {

        facing = (null != placer) ? placer.getHorizontalFacing().getOpposite() : EnumFacing.NORTH;

        return this.getDefaultState().withProperty(HFACING, facing);
    }

    /**
     * Called when a block is placed in the world by code (world.setBlockState())
     * */
    public void onBlockAdded(World world, BlockPos position, IBlockState state) {

        EnumFacing newFacing = this.suggestDefaultFacing(world, position, (EnumFacing)state.getValue(HFACING));

        world.setBlockState(position, state.withProperty(HFACING, newFacing), 2);
    }

    /**
     * Return the suggested facing for a block indirectly placed in the world (by World.setBlockState for example)
     *
     * @param world the current world
     * @param position position of the block
     * @param currentFacing the current facing
     * @return the new facing for the block based on the surrounding blocks
     */
    protected EnumFacing suggestDefaultFacing(World world, BlockPos position, EnumFacing currentFacing) {

        EnumFacing oppositeFacing = currentFacing.getOpposite();
        /*
        Block facingBlock = world.getBlockState(position.offset(currentFacing)).getBlock();
        Block oppositeBlock = world.getBlockState(position.offset(oppositeFacing)).getBlock();

        return facingBlock.isFullBlock() && !oppositeBlock.isFullBlock() ? oppositeFacing : currentFacing;
        */
        IBlockState facingBlockState = world.getBlockState(position.offset(currentFacing));
        IBlockState oppositeBlockState = world.getBlockState(position.offset(oppositeFacing));

        return facingBlockState.isFullBlock() && !oppositeBlockState.isFullBlock() ? oppositeFacing : currentFacing;
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, new IProperty[] {HFACING, ASSEMBLED});
    }
}
