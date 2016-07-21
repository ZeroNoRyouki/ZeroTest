package it.zerono.mods.zerotest.common.multiblock.mightyfurnace;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import it.zerono.mods.zerocore.api.multiblock.validation.ValidationError;

public class MightyFurnaceIOPortTileEntity extends MightyFurnaceTileEntity /*implements IInventory, ISidedInventory*/ {

    public MightyFurnaceIOPortTileEntity(boolean isInput) {

        this._isInput = isInput;
        this._inventory = null;
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

    // IInventory
    /*

    @Override
    public void clear() {

        this._inventory = null;
    }

    @Override
    public int getSizeInventory() {

        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int index) {

        return 0 == index ? this._inventory : null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {

        ItemStack itemStack = this.getStackInSlot(index);

        if (itemStack != null)
            this.setInventorySlotContents(index, null);

        return itemStack;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {

        //return 0 == index ? InventoryHelper.decreaseStackSize(this, 0, count) : null;
        return null;
    }

    //@Override
    @Deprecated
    public ItemStack getStackInSlotOnClosing(int index) {

        ItemStack itemStack = this.getStackInSlot(index);

        if (itemStack != null)
            this.setInventorySlotContents(index, null);

        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        if (0 == index)
            this._inventory = stack;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {

        //return CodeHelper.isEntityInRange(player, this.pos, 8.0);
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {

        return true;
    }

    @Override
    public int getField(int id) {

        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public String getName() {

        return "te.mightyFurnaceIO";
    }

    @Override
    public boolean hasCustomName() {

        return false;
    }

    @Override
    public IChatComponent getDisplayName() {

        return InventoryHelper.getDisplayName(this);
    }
    */

    // ISidedInventory
    /*
    @Override
    public int[] getSlotsForFace(EnumFacing side) {

        return new int[0];
    }

    / **
     * Returns true if automation can extract the given item in the given slot from the given side. Args: slot, item,
     * side
     * /
    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return false;
    }

    / **
     * Returns true if automation can insert the given item in the given slot from the given side. Args: slot, item,
     * side
     * /
    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return false;
    }
    */

    // save/load state

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
    protected ItemStack _inventory;

    private static ValidationError s_invalidPosition = new ValidationError("zerocoretest:api.multiblock.validation.ioport_invalid_position");
}
