package it.zerono.mods.zerotest.common.multiblock.mightyfurnace;

import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import it.zerono.mods.zerocore.api.multiblock.validation.ValidationError;

public class MightyFurnacePowerTileEntity extends MightyFurnaceTileEntity /*implements IEnergyReceiver*/ {

    @Override
    public boolean isGoodForFrame(IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(MightyFurnacePowerTileEntity.s_invalidPosition);
        return false;
    }

    @Override
    public boolean isGoodForTop(IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(MightyFurnacePowerTileEntity.s_invalidPosition);
        return false;
    }

    @Override
    public boolean isGoodForBottom(IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(MightyFurnacePowerTileEntity.s_invalidPosition);
        return false;
    }

    @Override
    public boolean isGoodForInterior(IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(MightyFurnacePowerTileEntity.s_invalidPosition);
        return false;
    }

    /*
    // IEnergyReceiver begin

    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {

        FMLLog.info("RFTEST - powerTE:receiveEnergy called (%s, %d, %s)", facing.toString(), maxReceive, simulate ? "simulation" : "real" );

        if (!this.isFacingGoodForEnergy(facing)) {
            FMLLog.info("RFTEST - powerTE:receiveEnergy facing not good!");
        FMLLog.info("TILE POWER : received %d RF", maxReceive);

        if (!this.isFacingGoodForEnergy(facing))
            return 0;
        }

        MultiblockControllerBase controller = this.getMultiblockController();

        int r = (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).receiveEnergy(facing, maxReceive, simulate) : 0;

        FMLLog.info("RFTEST - powerTE:receiveEnergy result = %d", r);
        return r;
    }

    @Override
    public int getEnergyStored(EnumFacing facing) {

        FMLLog.info("RFTEST - powerTE:getEnergyStored called (%s)", facing.toString());

        if (!this.isFacingGoodForEnergy(facing)) {
            FMLLog.info("RFTEST - powerTE:getEnergyStored facing not good!");
            return 0;
        }

        MultiblockControllerBase controller = this.getMultiblockController();

        int r = (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).getEnergyStored(facing) : 0;

        FMLLog.info("RFTEST - powerTE:getEnergyStored result = %d", r);
        return r;
    }

    @Override
    public int getMaxEnergyStored(EnumFacing facing) {

        FMLLog.info("RFTEST - powerTE:getMaxEnergyStored called (%s)", facing.toString());

        if (!this.isFacingGoodForEnergy(facing)) {
            FMLLog.info("RFTEST - powerTE:getMaxEnergyStored facing not good!");
            return 0;
        }

        MultiblockControllerBase controller = this.getMultiblockController();

        int r = (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).getMaxEnergyStored(facing) : 0;


        FMLLog.info("RFTEST - powerTE:getMaxEnergyStored result = %d", r);
        return r;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing facing) {

        return true;

        //return this.isFacingGoodForEnergy(facing);
    }


    private boolean isFacingGoodForEnergy(EnumFacing facing) {

        return this.isConnected() && this.getMultiblockController().isAssembled() && this.getOutwardsDir().isSet(facing);
    }

    // IEnergyReceiver end
    */

    private static ValidationError s_invalidPosition = new ValidationError("zerocoretest:api.multiblock.validation.powerport_invalid_position");
}
