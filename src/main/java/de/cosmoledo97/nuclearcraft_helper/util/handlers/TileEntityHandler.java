package de.cosmoledo97.nuclearcraft_helper.util.handlers;

import de.cosmoledo97.nuclearcraft_helper.Reference;
import de.cosmoledo97.nuclearcraft_helper.blocks.fuel_crafter.TileEntityFuelCrafter;
import de.cosmoledo97.nuclearcraft_helper.blocks.tiny_compactor.TileEntityTinyCompactor;
import de.cosmoledo97.nuclearcraft_helper.init.BlockInit;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityFuelCrafter.class, new ResourceLocation(Reference.MODID + ":" + BlockInit.NAME_FUEL_CRAFTER));
		GameRegistry.registerTileEntity(TileEntityTinyCompactor.class, new ResourceLocation(Reference.MODID + ":" + BlockInit.NAME_TINY_COMPACTOR));
	}
}
