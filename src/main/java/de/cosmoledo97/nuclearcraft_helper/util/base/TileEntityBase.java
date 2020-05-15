package de.cosmoledo97.nuclearcraft_helper.util.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityBase extends TileEntity {
	public static final int[] ROW_AMOUNT = {5, 2};
	public static final int INPUT_AMOUNT = ROW_AMOUNT[0] * 9;
	private final ItemStackHandlerBase handler = new ItemStackHandlerBase(INPUT_AMOUNT + ROW_AMOUNT[1] * 9);
	private final String blockName;

	public TileEntityBase(String blockName) {
		this.blockName = blockName;
		this.handler.setRecipes(blockName);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (this.hasCapability(capability, facing))
			return (T) this.handler;
		return super.getCapability(capability, facing);
	}

	public IItemHandler getCapabilityHandler() {
		return this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("container." + this.blockName);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
	}

	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this
				? false
				: player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		return compound;
	}
}