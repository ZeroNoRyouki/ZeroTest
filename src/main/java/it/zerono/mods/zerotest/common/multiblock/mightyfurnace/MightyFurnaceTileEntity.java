package it.zerono.mods.zerotest.common.multiblock.mightyfurnace;

import it.zerono.mods.zerocore.util.WorldHelper;
import net.minecraft.util.math.BlockPos;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.rectangular.RectangularMultiblockTileEntityBase;

public class MightyFurnaceTileEntity extends RectangularMultiblockTileEntityBase {

    @Override
    public boolean isGoodForSides(IMultiblockValidator validatorCallback) {
        return true;
    }

    @Override
    public boolean isGoodForFrame(IMultiblockValidator validatorCallback) {
        return true;
    }

    @Override
    public boolean isGoodForTop(IMultiblockValidator validatorCallback) {
        return true;
    }

    @Override
    public boolean isGoodForBottom(IMultiblockValidator validatorCallback) {
        return true;
    }

    @Override
    public boolean isGoodForInterior(IMultiblockValidator validatorCallback) {
        return true;
    }

    @Override
    public void onMachineActivated() {
    }

    @Override
    public void onMachineDeactivated() {
    }

    @Override
    public void onMachineAssembled(MultiblockControllerBase controller) {

        super.onMachineAssembled(controller);
        this.updateBlockState();
    }

    @Override
    public void onMachineBroken() {

        super.onMachineBroken();
        this.updateBlockState();
    }

    @Override
    public MultiblockControllerBase createNewMultiblock() {
        return new MightyFurnaceController(this.worldObj);
    }

    @Override
    public Class<? extends MultiblockControllerBase> getMultiblockControllerType() {
        return MightyFurnaceController.class;
    }

    @Override
    public BlockPos getWorldPosition() {
        return this.pos;
    }

    public MightyFurnaceController getFurnaceController() {
        return (MightyFurnaceController)this.getMultiblockController();
    }

    private void updateBlockState() {
        WorldHelper.notifyBlockUpdate(this.worldObj, this.pos, null, null);
    }
}
