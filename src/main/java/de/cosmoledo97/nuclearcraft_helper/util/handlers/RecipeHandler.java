package de.cosmoledo97.nuclearcraft_helper.util.handlers;

import java.util.ArrayList;
import java.util.Iterator;

import de.cosmoledo97.nuclearcraft_helper.init.BlockInit;
import nc.Global;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;

public class RecipeHandler {
	public static final ArrayList<IRecipe> RECIPE_FUEL_CRAFTER = new ArrayList<IRecipe>();
	public static final ArrayList<IRecipe> RECIPE_TINY_COMPACTOR = new ArrayList<IRecipe>();

	static {
		Iterator<IRecipe> iterator = CraftingManager.REGISTRY.iterator();

		while (iterator.hasNext()) {
			IRecipe recipe = iterator.next();

			if (recipe == null)
				continue;

			checkFuelCrafter(recipe);
			checkTinyCompactor(recipe);
		}
	}

	private static void checkFuelCrafter(IRecipe recipe) {
		// filter for nuclearcraft recipes
		if (!recipe.getRegistryName().getResourceDomain().equals(Global.MOD_ID))
			return;

		// filter for fuel recipes
		if (!recipe.getRecipeOutput().getUnlocalizedName().contains("fuel"))
			return;

		// filter for item recipes
		if (!recipe.getRecipeOutput().getUnlocalizedName().contains("item"))
			return;

		RECIPE_FUEL_CRAFTER.add(recipe);
	}

	private static void checkTinyCompactor(IRecipe recipe) {
		// remove invalid recipes
		if (recipe == null)
			return;

		// filter for nuclearcraft recipes
		if (!recipe.getRegistryName().getResourceDomain().equals(Global.MOD_ID))
			return;

		// filter for item recipes
		if (!recipe.getRecipeOutput().getUnlocalizedName().contains("item"))
			return;

		// filter for tiny ingredient
		boolean valid = false;

		for (Ingredient ingredient : recipe.getIngredients())
			for (ItemStack stack : ingredient.getMatchingStacks())
				if (stack.getUnlocalizedName().contains("tiny")) {
					valid = true;
					break;
				}

		if (!valid)
			return;
		//

		RECIPE_TINY_COMPACTOR.add(recipe);
	}

	public static ArrayList<IRecipe> getList(String blockName) {
		switch (blockName) {
			case BlockInit.NAME_FUEL_CRAFTER :
				return RECIPE_FUEL_CRAFTER;

			case BlockInit.NAME_TINY_COMPACTOR :
				return RECIPE_TINY_COMPACTOR;
		}

		return new ArrayList<IRecipe>();
	}
}
