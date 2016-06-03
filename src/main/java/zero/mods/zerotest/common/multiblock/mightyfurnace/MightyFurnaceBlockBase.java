package zero.mods.zerotest.common.multiblock.mightyfurnace;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zero.mods.zerocore.api.multiblock.IMultiblockPart;
import zero.mods.zerocore.api.multiblock.MultiblockControllerBase;
import zero.mods.zerocore.api.multiblock.validation.ValidationError;
import zero.mods.zerotest.common.block.TestBlockBase;

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

        boolean isServerSide = !world.isRemote;

        if (!world.isRemote || player.isSneaking() || (hand != EnumHand.OFF_HAND) || (null != heldItem))
            return false;

        MultiblockControllerBase controller = this.getMultiblockController(world, position);

        if (null != controller) {

            ValidationError status = controller.getLastError();

            if (null != status) {

                //player.addChatMessage(new TextComponentString(status.getValidationMessage()));
                player.addChatMessage(status.getChatMessage());
                return true;
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

    protected MightyFurnaceBlockBase(String name, MightyFurnaceBlockType blockType) {

        super(name, Material.iron);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(SoundType.METAL);

        this._myType = blockType;
    }

    private MightyFurnaceBlockType _myType;
}
