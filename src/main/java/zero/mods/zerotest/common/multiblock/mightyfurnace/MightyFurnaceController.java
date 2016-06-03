package zero.mods.zerotest.common.multiblock.mightyfurnace;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import zero.mods.zerocore.api.multiblock.IMultiblockPart;
import zero.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import zero.mods.zerocore.api.multiblock.MultiblockControllerBase;
import zero.mods.zerocore.api.multiblock.validation.InvalidPart;
import zero.mods.zerocore.api.multiblock.validation.ValidationError;
import zero.mods.zerocore.api.multiblock.rectangular.RectangularMultiblockControllerBase;

public class MightyFurnaceController extends RectangularMultiblockControllerBase /*implements IEnergyReceiver*/ {


    public MightyFurnaceController(World world) {

        super(world);

        this._inputPort = this._outputPort = null;
        this._powerPort = null;
        /*
        this._rfStorage = null;
        */
        this._active = false;
    }

    public boolean isActive() {

        return this._active;
    }

    /*
    public void switchActive() {

        //this._active = !this._active;
    }*/

    // IEnergyReceiver begin
    /*
    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {

        FMLLog.info("RFTEST - controller:receiveEnergy called (%s, %d, %s)", facing.toString(), maxReceive, simulate ? "simulation" : "real" );

        int r = this.getRFStorage().receiveEnergy(maxReceive, simulate);

        FMLLog.info("RFTEST - controller:receiveEnergy result = %d", r);
        return r;
    }

    @Override
    public int getEnergyStored(EnumFacing facing) {

        return (null == this._rfStorage) ? 0 : this.getRFStorage().getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing facing) {

        return RF_CAPACITY;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing facing) {
        return false; // never called
    }

    // IEnergyReceiver end
    */

    @Override
    protected boolean isBlockGoodForFrame(World world, int x, int y, int z, IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(new InvalidPart("zerocoretest:api.multiblock.validation.invalid_block", x, y, z));
        return false;
    }

    @Override
    protected boolean isBlockGoodForTop(World world, int x, int y, int z, IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(new InvalidPart("zerocoretest:api.multiblock.validation.invalid_block", x, y, z));
        return false;
    }

    @Override
    protected boolean isBlockGoodForBottom(World world, int x, int y, int z, IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(new InvalidPart("zerocoretest:api.multiblock.validation.invalid_block", x, y, z));
        return false;
    }

    @Override
    protected boolean isBlockGoodForSides(World world, int x, int y, int z, IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(new InvalidPart("zerocoretest:api.multiblock.validation.invalid_block", x, y, z));
        return false;
    }

    @Override
    protected boolean isBlockGoodForInterior(World world, int x, int y, int z, IMultiblockValidator validatorCallback) {

        validatorCallback.setLastError(new InvalidPart("zerocoretest:api.multiblock.validation.invalid_block", x, y, z));
        return false;
    }

    @Override
    protected void onBlockAdded(IMultiblockPart newPart) {

        FMLLog.info("MightyFurnaceController:onBlockAdded : %s", newPart.toString());
    }

    @Override
    protected void onBlockRemoved(IMultiblockPart oldPart) {

        FMLLog.info("MightyFurnaceController:onBlockRemoved : %s", oldPart.toString());

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

        FMLLog.info("MightyFurnaceController:isMachineWhole");

        MightyFurnacePowerTileEntity powerPort = null;
        MightyFurnaceIOPortTileEntity inputPort = null;
        MightyFurnaceIOPortTileEntity outputPort = null;

        if (!super.isMachineWhole(validatorCallback))
            return false;

        for (IMultiblockPart part : this.connectedParts) {

            if (part instanceof MightyFurnacePowerTileEntity) {

                if (null != powerPort) {

                    validatorCallback.setLastError(new ValidationError("zerocoretest:api.multiblock.validation.powerport_already_present"));
                    return false;
                }

                powerPort = (MightyFurnacePowerTileEntity)part;

            } else if (part instanceof MightyFurnaceIOPortTileEntity) {

                MightyFurnaceIOPortTileEntity io = (MightyFurnaceIOPortTileEntity) part;
                boolean isInput = io.isInput();

                if (isInput) {

                    if (null != inputPort) {

                        validatorCallback.setLastError(new ValidationError("zerocoretest:api.multiblock.validation.inputport_already_present"));
                        return false;
                    }

                    inputPort = io;

                } else {

                    if (null != outputPort) {

                        validatorCallback.setLastError(new ValidationError("zerocoretest:api.multiblock.validation.outputport_already_present"));
                        return false;
                    }

                    outputPort = io;
                }
            }
        }

        if (null == powerPort) {

            validatorCallback.setLastError(new ValidationError("zerocoretest:api.multiblock.validation.powerport_missing"));
            return false;
        }

        if (null == inputPort) {

            validatorCallback.setLastError(new ValidationError("zerocoretest:api.multiblock.validation.inputport_missing"));
            return false;
        }

        if (null == outputPort) {

            validatorCallback.setLastError(new ValidationError("zerocoretest:api.multiblock.validation.outputport_missing"));
            return false;
        }

        return true;
    }

    @Override
    protected void onMachineAssembled() {

        this.lookupPorts();

        if (this.worldObj.isRemote)
            // on the client, force a render update
            this.markReferenceCoordForUpdate();

        FMLLog.info("CONTROLLER - assembled");
    }

    @Override
    protected void onMachineRestored() {

        this.lookupPorts();

        FMLLog.info("CONTROLLER - restored");

        if (this.worldObj.isRemote)
            // on the client, force a render update
            this.markReferenceCoordForUpdate();
    }

    @Override
    protected void onMachinePaused() {

        // pause work?
        FMLLog.info("CONTROLLER - paused");

        if (this.worldObj.isRemote)
            // on the client, force a render update
            this.markReferenceCoordForUpdate();
    }

    @Override
    protected void onMachineDisassembled() {

        FMLLog.info("CONTROLLER - disassembled");

        if (this.worldObj.isRemote)
            // on the client, force a render update
            this.markReferenceCoordForUpdate();
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

        FMLLog.info("Controller.getMinimumNumberOfBlocksForAssembledMachine called");

        return 27;
    }

    @Override
    protected int getMaximumXSize() {

        return 3;
    }

    @Override
    protected int getMaximumZSize() {

        return 3;
    }

    @Override
    protected int getMaximumYSize() {

        return 3;
    }

    @Override
    protected boolean updateServer() {

        // consume energy
        /*
        this._active = this.tryToConsumeEnergy(RF_PER_OPERATION);
        */
        return false;
    }

    @Override
    protected void updateClient() {
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
    }

    @Override
    public void formatDescriptionPacket(NBTTagCompound data) {
    }

    @Override
    public void decodeDescriptionPacket(NBTTagCompound data) {
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

    /*
    protected EnergyStorage getRFStorage() {

        if (null == this._rfStorage)
            this._rfStorage = new EnergyStorage(RF_CAPACITY, RF_PER_OPERATION * 2, RF_PER_OPERATION);

        return this._rfStorage;
    }

    protected boolean tryToConsumeEnergy(int amount) {

        if ((null == this._rfStorage) ||(amount != this._rfStorage.extractEnergy(amount, true)))
            return  false;

        this._rfStorage.extractEnergy(amount, false);
        return true;
    }
    */

    private MightyFurnaceIOPortTileEntity _inputPort;
    private MightyFurnaceIOPortTileEntity _outputPort;
    private MightyFurnacePowerTileEntity _powerPort;
    private boolean _active;

    /*
    private EnergyStorage _rfStorage;

    private static final int RF_CAPACITY = 1000;
    private static final int RF_PER_OPERATION = 100;
    */
}
