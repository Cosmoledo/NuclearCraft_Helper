package de.cosmoledo97.nuclearcraft_helper.util.base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

import de.cosmoledo97.nuclearcraft_helper.Main;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.items.IItemHandler;

public class ContainerBase extends Container {
	private final static Comparator<ItemStack> SORT_INVENTORY = new Comparator<ItemStack>() {
		@Override
		public int compare(ItemStack stack1, ItemStack stack2) {
			int out = stack1.getUnlocalizedName().compareToIgnoreCase(stack2.getUnlocalizedName());
			if (out == 0)
				out = stack2.getCount() - stack1.getCount();
			return out;
		}
	};

	public final static ArrayList<IRecipe> recipes = new ArrayList<IRecipe>();
	protected TileEntityBase tileentity;
	protected int slotCount = 0;

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileentity.isUsableByPlayer(playerIn);
	}

	protected void extractFromOutput(ItemStack stack) {
		IRecipe recipe = null;

		for (int r = 0; r < ContainerBase.recipes.size(); r++) {
			recipe = ContainerBase.recipes.get(r);

			if (this.isStackSame(stack, recipe.getRecipeOutput()) && this.isRecipeCraftable(recipe))
				break;
		}

		if (recipe == null) {
			Main.info(stack.getUnlocalizedName() + " was extracted but its recipe was not found.");
			return;
		}

		for (int i = 0; i < recipe.getIngredients().size(); i++) {
			ItemStack[] ingredients = recipe.getIngredients().get(i).getMatchingStacks();

			for (int k = 0; k < ingredients.length; k++) {
				ItemStack ingredient = ingredients[k];

				for (int s = 0; s < this.slotCount / 2; s++) {
					Slot slot = this.getSlot(s);
					ItemStack itemstack = slot.getStack();

					if (this.isStackSame(itemstack, ingredient) && itemstack.getCount() >= ingredient.getCount()) {
						itemstack.setCount(itemstack.getCount() - ingredient.getCount());
						k++;
						break;
					}
				}

			}
		}
	}

	public void init(InventoryPlayer playerInv, TileEntityBase tileentity) {
		this.tileentity = tileentity;

		// inventory
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 9; x++)
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 173 + y * 18));

		// hotbar
		for (int x = 0; x < 9; x++)
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 231));

		this.update();
	}

	private boolean isRecipeCraftable(IRecipe recipe) {
		IItemHandler handler = this.tileentity.getCapabilityHandler();

		int ingredientCount = 0;
		RecipeItemHelper recipeItemHelper = new RecipeItemHelper();
		List<ItemStack> items = Lists.newArrayList();

		boolean isSimple = true;
		for (Ingredient i : recipe.getIngredients())
			isSimple &= i.isSimple();

		for (int i = 0; i < handler.getSlots(); ++i) {
			ItemStack itemstack = handler.getStackInSlot(i);
			if (!itemstack.isEmpty())
				if (isSimple) {
					ingredientCount += itemstack.getCount();
					recipeItemHelper.accountStack(itemstack, itemstack.getCount());
				} else {
					ingredientCount++;
					items.add(itemstack);
				}
		}

		if (ingredientCount < recipe.getIngredients().size())
			return false;

		if (isSimple)
			return recipeItemHelper.canCraft(recipe, null);

		return RecipeMatcher.findMatches(items, recipe.getIngredients()) != null;
	}

	private boolean isStackSame(ItemStack stack1, ItemStack stack2) {
		return !stack1.isEmpty() && !stack2.isEmpty() && stack1.getItem() == stack2.getItem() && ItemStack.areItemStackTagsEqual(stack1, stack2)
				&& (!stack1.getHasSubtypes() || !stack2.getHasSubtypes() || stack1.getMetadata() == stack2.getMetadata());
	}

	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		ItemStack stack = super.slotClick(slotId, dragType, clickTypeIn, player);
		this.update();
		return stack;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.getSlot(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < this.slotCount) {
				// extract from gui
				if (!this.mergeItemStack(itemstack1, this.slotCount, this.inventorySlots.size(), true))
					return ItemStack.EMPTY;
				this.extractFromOutput(itemstack);
			} else // insert into inventory
			if (!this.mergeItemStack(itemstack1, 0, this.slotCount, false))
				return ItemStack.EMPTY;

			if (itemstack1.isEmpty())
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();

			this.update();
		}

		return itemstack;
	}

	private void update() {
		this.updateInputSlots();
		this.updateOutputSlots();
	}

	private void updateInputSlots() {
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();

		for (int i = 0; i < this.slotCount / 2; i++) {
			Slot slot = this.getSlot(i);

			if (slot != null && slot.getHasStack()) {
				stacks.add(slot.getStack().copy());
				slot.decrStackSize(64);
			}
		}

		for (int i = 0; i < stacks.size(); i++)
			for (int k = 0; k < stacks.size(); k++)
				if (i != k && this.isStackSame(stacks.get(i), stacks.get(k))) {
					int sum = stacks.get(i).getCount() + stacks.get(k).getCount();
					int max = stacks.get(i).getMaxStackSize();

					if (sum <= max) {
						stacks.get(i).setCount(sum);
						stacks.get(k).setCount(0);
					} else {
						stacks.get(i).setCount(max);
						stacks.get(k).setCount(sum - max);
					}
				}

		stacks.sort(SORT_INVENTORY);

		int slot = 0;
		for (int i = 0; i < stacks.size(); i++)
			if (!stacks.get(i).isEmpty())
				this.getSlot(slot++).putStack(stacks.get(i));
	}

	private void updateOutputSlots() {
		for (int i = this.slotCount / 2; i < this.slotCount; i++)
			this.getSlot(i).decrStackSize(64);

		List<ItemStack> outputs = new ArrayList<ItemStack>();

		for (int r = 0; r < ContainerBase.recipes.size(); r++) {
			IRecipe recipe = ContainerBase.recipes.get(r);

			if (this.isRecipeCraftable(recipe))
				outputs.add(recipe.getRecipeOutput());
		}

		outputs.sort(SORT_INVENTORY);
		int offset = this.slotCount / 2;
		for (ItemStack output : outputs)
			this.getSlot(offset++).putStack(output.copy());
	}
}
