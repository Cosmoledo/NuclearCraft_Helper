package de.cosmoledo97.nuclearcraft_helper.util;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBlockDescripted extends ItemBlock {
	private final String tooltipText;

	public ItemBlockDescripted(Block block, String tooltipText) {
		super(block);
		this.tooltipText = tooltipText;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.AQUA + this.tooltipText);
	}
}
