package zero.mods.zerotest.common.multiblock.mightyfurnace;

import zero.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import zero.mods.zerocore.api.multiblock.MultiblockControllerBase;
import zero.mods.zerocore.api.multiblock.rectangular.RectangularMultiblockTileEntityBase;

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

    private void updateBlockState() {

        // FIX
        //this.worldObj.markBlockForUpdate(this.pos);
    }
}
