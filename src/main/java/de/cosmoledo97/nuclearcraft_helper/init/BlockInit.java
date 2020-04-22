package de.cosmoledo97.nuclearcraft_helper.init;

import java.util.ArrayList;
import java.util.List;

import de.cosmoledo97.nuclearcraft_helper.blocks.fuel_crafter.BlockFuelCrafter;
import de.cosmoledo97.nuclearcraft_helper.blocks.tiny_compactor.BlockTinyCompactor;
import net.minecraft.block.Block;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static final String NAME_FUEL_CRAFTER = "fuel_crafter";
	public static final String NAME_TINY_COMPACTOR = "tiny_compactor";

	public static final Block BLOCK_TINY_COMPACTOR = new BlockTinyCompactor(NAME_TINY_COMPACTOR);
	public static final Block BLOCK_FUEL_CRAFTER = new BlockFuelCrafter(NAME_FUEL_CRAFTER);
}
