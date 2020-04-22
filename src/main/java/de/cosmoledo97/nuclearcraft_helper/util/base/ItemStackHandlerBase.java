package de.cosmoledo97.nuclearcraft_helper.util.base;

import nc.Global;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerBase extends ItemStackHandler {
	public ItemStackHandlerBase(int size) {
		super(size);
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (this.isItemValid(slot, stack))
			return super.insertItem(slot, stack, simulate);
		return stack;
	}

	private boolean isInOutputRange(int slot) {
		return slot >= this.getSlots() / 2;
	}

	@Override
	public boolean isItemValid(int slot, ItemStack stack) {
		if (this.isInOutputRange(slot))
			return false;
		return stack.getItem().getRegistryName().getResourceDomain().equals(Global.MOD_ID);
	}
}
