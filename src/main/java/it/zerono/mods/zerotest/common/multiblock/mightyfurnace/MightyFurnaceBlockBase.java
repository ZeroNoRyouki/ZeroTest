package it.zerono.mods.zerotest.common.multiblock.mightyfurnace;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.validation.ValidationError;
import it.zerono.mods.zerotest.common.block.TestBlockBase;

public abstract class MightyFurnaceBlockBase extends TestBlockBase {


    @Override
    public boolean hasTileEntity(IBlockState state) {

        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {

        switch (this._myType) {

            default:
                return new MightyFurnaceTileEntity();

            case Power:
                return new MightyFurnacePowerTileEntity();

            case Input:
                return new MightyFurnaceIOPortTileEntity(true);

            case Output:
                return new MightyFurnaceIOPortTileEntity(false);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos position, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (world.isRemote || (hand != EnumHand.OFF_HAND) || (null != heldItem))
            return false;

        MightyFurnaceController controller = this.getFurnaceController(world, position);

        if (null != controller) {

            if (player.isSneaking()) {

                // toggle machine status
                controller.toggleActive();
                return true;

            } else {

                // display any validation errors

                ValidationError status = controller.getLastError();

                if (null != status) {

                    player.addChatMessage(status.getChatMessage());
                    return true;
                }
            }
        }

        return false;
    }

    protected IMultiblockPart getMultiblockPartAt(IBlockAccess world, BlockPos position) {

        TileEntity te = world.getTileEntity(position);

        return te instanceof IMultiblockPart ? (IMultiblockPart)te : null;
    }

    protected MultiblockControllerBase getMultiblockController(IBlockAccess world, BlockPos position) {

        IMultiblockPart part = this.getMultiblockPartAt(world, position);

        return null != part ? part.getMultiblockController() : null;
    }

    protected MightyFurnaceController getFurnaceController(IBlockAccess world, BlockPos position) {

        MultiblockControllerBase controller = this.getMultiblockController(world, position);

        return controller instanceof MightyFurnaceController ? (MightyFurnaceController)controller : null;
    }

    protected MightyFurnaceBlockBase(String name, MightyFurnaceBlockType blockType) {

        super(name, Material.IRON);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);

        this._myType = blockType;
    }

    private MightyFurnaceBlockType _myType;
}
