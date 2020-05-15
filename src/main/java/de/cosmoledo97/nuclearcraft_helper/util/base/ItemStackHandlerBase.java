package de.cosmoledo97.nuclearcraft_helper.util.base;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

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

		for (IRecipe r : ContainerBase.recipes)
			for (Ingredient i : r.getIngredients())
				for (ItemStack is : i.getMatchingStacks())
					if (OreDictionary.itemMatches(is, stack, false))
						return true;

		return false;
	}
}
