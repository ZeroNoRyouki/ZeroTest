package it.zerono.mods.zerotest.common.multiblock.mightyfurnace;

import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import it.zerono.mods.zerocore.api.multiblock.validation.ValidationError;

public class MightyFurnacePowerTileEntity extends MightyFurnaceTileEntity {

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

    private static ValidationError s_invalidPosition = new ValidationError("zerocoretest:api.multiblock.validation.powerport_invalid_position");
}
