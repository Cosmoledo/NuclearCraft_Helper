package de.cosmoledo97.nuclearcraft_helper;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Tab extends CreativeTabs {
	public Tab() {
		super("nuclear_helper");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(nc.init.NCBlocks.fission_block);
	}
}
