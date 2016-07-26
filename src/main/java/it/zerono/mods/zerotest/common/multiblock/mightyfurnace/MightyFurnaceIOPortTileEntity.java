package it.zerono.mods.zerotest.common.multiblock.mightyfurnace;

import net.minecraft.nbt.NBTTagCompound;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import it.zerono.mods.zerocore.api.multiblock.validation.ValidationError;

public class MightyFurnaceIOPortTileEntity extends MightyFurnaceTileEntity {

    public MightyFurnaceIOPortTileEntity(boolean isInput) {

        this._isInput = isInput;
    }

    public MightyFurnaceIOPortTileEntity() {
        this(false);
    }

    @Override
    public boolean isGoodForFrame(IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(MightyFurnaceIOPortTileEntity.s_invalidPosition);
        return false;
    }

    @Override
    public boolean isGoodForTop(IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(MightyFurnaceIOPortTileEntity.s_invalidPosition);
        return false;
    }

    @Override
    public boolean isGoodForBottom(IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(MightyFurnaceIOPortTileEntity.s_invalidPosition);
        return false;
    }

    @Override
    public boolean isGoodForInterior(IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(MightyFurnaceIOPortTileEntity.s_invalidPosition);
        return false;
    }

    public boolean isInput() {
        return this._isInput;
    }

    @Override
    protected void syncDataFrom(NBTTagCompound data, SyncReason syncReason) {

        super.syncDataFrom(data, syncReason);

        if (data.hasKey("mfIOdir"))
            this._isInput = data.getBoolean("mfIOdir");
    }

    @Override
    protected void syncDataTo(NBTTagCompound data, SyncReason syncReason) {

        super.syncDataTo(data, syncReason);
        data.setBoolean("mfIOdir", this._isInput);
    }

    protected boolean _isInput;

    private static ValidationError s_invalidPosition = new ValidationError("zerocoretest:api.multiblock.validation.ioport_invalid_position");
}
