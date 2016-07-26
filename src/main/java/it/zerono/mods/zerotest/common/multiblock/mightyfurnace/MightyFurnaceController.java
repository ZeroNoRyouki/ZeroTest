package it.zerono.mods.zerotest.common.multiblock.mightyfurnace;

import it.zerono.mods.zerocore.util.WorldHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.rectangular.RectangularMultiblockControllerBase;
import it.zerono.mods.zerocore.lib.block.ModTileEntity;

public class MightyFurnaceController extends RectangularMultiblockControllerBase {

    public MightyFurnaceController(World world) {

        super(world);

        this._inputPort = this._outputPort = null;
        this._powerPort = null;
        this._active = false;
    }

    public boolean isActive() {
        return this._active;
    }

    public void toggleActive() {
        this.setActive(!this._active);
    }

    public void setActive(boolean active) {

        if (this._active == active)
            return;

        // the state was changed, set it
        this._active = active;

        if (WorldHelper.calledByLogicalServer(this.WORLD)) {

            // on the server side, request an update to be sent to the client and mark the save delegate as dirty
            this.markReferenceCoordForUpdate();
            this.markReferenceCoordDirty();

        } else {

            // on the client, request a render update
            this.markMultiblockForRenderUpdate();
        }
    }

    @Override
    protected boolean isBlockGoodForFrame(World world, int x, int y, int z, IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError("zerocoretest:api.multiblock.validation.invalid_block", x, y, z);
        return false;
    }

    @Override
    protected boolean isBlockGoodForTop(World world, int x, int y, int z, IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError("zerocoretest:api.multiblock.validation.invalid_block", x, y, z);
        return false;
    }

    @Override
    protected boolean isBlockGoodForBottom(World world, int x, int y, int z, IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError("zerocoretest:api.multiblock.validation.invalid_block", x, y, z);
        return false;
    }

    @Override
    protected boolean isBlockGoodForSides(World world, int x, int y, int z, IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError("zerocoretest:api.multiblock.validation.invalid_block", x, y, z);
        return false;
    }

    @Override
    protected boolean isBlockGoodForInterior(World world, int x, int y, int z, IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError("zerocoretest:api.multiblock.validation.invalid_block", x, y, z);
        return false;
    }

    @Override
    protected void onBlockAdded(IMultiblockPart newPart) {
    }

    @Override
    protected void onBlockRemoved(IMultiblockPart oldPart) {

        if (oldPart instanceof MightyFurnacePowerTileEntity) {

            MightyFurnacePowerTileEntity tile = (MightyFurnacePowerTileEntity)oldPart;

            if (this._powerPort == tile)
                this._powerPort = null;

        } else if (oldPart instanceof MightyFurnaceIOPortTileEntity) {

            MightyFurnaceIOPortTileEntity tile = (MightyFurnaceIOPortTileEntity)oldPart;

            if (this._outputPort == tile)
                this._outputPort = null;
            else if (this._inputPort == tile)
                this._inputPort = null;
        }
    }

    @Override
    protected boolean isMachineWhole(IMultiblockValidator validatorCallback) {

        MightyFurnacePowerTileEntity powerPort = null;
        MightyFurnaceIOPortTileEntity inputPort = null;
        MightyFurnaceIOPortTileEntity outputPort = null;

        if (!super.isMachineWhole(validatorCallback))
            return false;

        for (IMultiblockPart part : this.connectedParts) {

            if (part instanceof MightyFurnacePowerTileEntity) {

                if (null != powerPort) {

                    validatorCallback.setLastError("zerocoretest:api.multiblock.validation.powerport_already_present");
                    return false;
                }

                powerPort = (MightyFurnacePowerTileEntity)part;

            } else if (part instanceof MightyFurnaceIOPortTileEntity) {

                MightyFurnaceIOPortTileEntity io = (MightyFurnaceIOPortTileEntity) part;
                boolean isInput = io.isInput();

                if (isInput) {

                    if (null != inputPort) {

                        validatorCallback.setLastError("zerocoretest:api.multiblock.validation.inputport_already_present");
                        return false;
                    }

                    inputPort = io;

                } else {

                    if (null != outputPort) {

                        validatorCallback.setLastError("zerocoretest:api.multiblock.validation.outputport_already_present");
                        return false;
                    }

                    outputPort = io;
                }
            }
        }

        if (null == powerPort) {

            validatorCallback.setLastError("zerocoretest:api.multiblock.validation.powerport_missing");
            return false;
        }

        if (null == inputPort) {

            validatorCallback.setLastError("zerocoretest:api.multiblock.validation.inputport_missing");
            return false;
        }

        if (null == outputPort) {

            validatorCallback.setLastError("zerocoretest:api.multiblock.validation.outputport_missing");
            return false;
        }

        return true;
    }

    @Override
    protected void onMachineAssembled() {

        this.lookupPorts();

        if (WorldHelper.calledByLogicalClient(this.WORLD))
            // on the client, force a render update
            this.markMultiblockForRenderUpdate();
    }

    @Override
    protected void onMachineRestored() {

        this.lookupPorts();

        if (this.WORLD.isRemote)
            // on the client, force a render update
            this.markMultiblockForRenderUpdate();
    }

    @Override
    protected void onMachinePaused() {

        // pause work?

        if (this.WORLD.isRemote)
            // on the client, force a render update
            this.markMultiblockForRenderUpdate();
    }

    @Override
    protected void onMachineDisassembled() {

        if (this.WORLD.isRemote)
            // on the client, force a render update
            this.markMultiblockForRenderUpdate();
    }

    @Override
    public void onAttachedPartWithMultiblockData(IMultiblockPart part, NBTTagCompound data) {
    }

    @Override
    protected void onAssimilate(MultiblockControllerBase assimilated) {
    }

    @Override
    protected void onAssimilated(MultiblockControllerBase assimilator) {
    }

    @Override
    protected int getMinimumNumberOfBlocksForAssembledMachine() {
        return 27;
    }

    @Override
    protected int getMaximumXSize() {
        return MACHINE_SIZE;
    }

    @Override
    protected int getMaximumZSize() {
        return MACHINE_SIZE;
    }

    @Override
    protected int getMaximumYSize() {
        return MACHINE_SIZE;
    }

    @Override
    protected int getMinimumXSize() {
        return MACHINE_SIZE;
    }

    @Override
    protected int getMinimumYSize() {
        return MACHINE_SIZE;
    }

    @Override
    protected int getMinimumZSize() {
        return MACHINE_SIZE;
    }

    @Override
    protected boolean updateServer() {
        return false;
    }

    @Override
    protected void updateClient() {
    }

    @Override
    protected void syncDataFrom(NBTTagCompound data, ModTileEntity.SyncReason syncReason) {

        if (data.hasKey("isActive"))
            this.setActive(data.getBoolean("isActive"));
    }

    @Override
    protected void syncDataTo(NBTTagCompound data, ModTileEntity.SyncReason syncReason) {
        data.setBoolean("isActive", this.isActive());
    }

    private void lookupPorts() {

        this._outputPort = this._inputPort = null;
        this._powerPort = null;

        for (IMultiblockPart part : this.connectedParts) {

            if (part instanceof MightyFurnacePowerTileEntity)
                this._powerPort = (MightyFurnacePowerTileEntity)part;

            if (part instanceof MightyFurnaceIOPortTileEntity) {

                MightyFurnaceIOPortTileEntity io = (MightyFurnaceIOPortTileEntity)part;

                if (io.isInput())
                    this._inputPort = io;
                else
                    this._outputPort = io;
            }
        }
    }

    private MightyFurnaceIOPortTileEntity _inputPort;
    private MightyFurnaceIOPortTileEntity _outputPort;
    private MightyFurnacePowerTileEntity _powerPort;
    private boolean _active;

    private static final int MACHINE_SIZE = 3;
}
