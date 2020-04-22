package de.cosmoledo97.nuclearcraft_helper.blocks.tiny_compactor;

import java.util.Iterator;

import de.cosmoledo97.nuclearcraft_helper.util.base.ContainerBase;
import nc.Global;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerTinyCompactor extends ContainerBase {
	static {
		Iterator<IRecipe> iterator = CraftingManager.REGISTRY.iterator();
		while (iterator.hasNext()) {
			IRecipe recipe = iterator.next();

			// remove invalid recipes
			if (recipe == null)
				continue;

			// filter for nuclearcraft recipes
			if (!recipe.getRegistryName().getResourceDomain().equals(Global.MOD_ID))
				continue;

			// filter for item recipes
			if (!recipe.getRecipeOutput().getUnlocalizedName().contains("item"))
				continue;

			// filter for tiny ingredient
			boolean valid = false;
			for (Ingredient ingredient : recipe.getIngredients())
				for (ItemStack stack : ingredient.getMatchingStacks())
					if (stack.getUnlocalizedName().contains("tiny")) {
						valid = true;
						break;
					}
			if (!valid)
				continue;
			//

			recipes.add(recipe);
		}
	}

	public ContainerTinyCompactor(InventoryPlayer player, TileEntityTinyCompactor tileentity) {
		// input
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 9; x++)
				this.addSlotToContainer(new SlotItemHandler(tileentity.getCapabilityHandler(), this.slotCount++, 8 + x * 18, 21 + y * 18));

		// output
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 9; x++)
				this.addSlotToContainer(new SlotItemHandler(tileentity.getCapabilityHandler(), this.slotCount++, 8 + x * 18, 97 + y * 18) {
					@Override
					public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
						ContainerTinyCompactor.this.extractFromOutput(stack);
						return super.onTake(thePlayer, stack);
					}
				});

		this.init(player, tileentity);
	}
}
