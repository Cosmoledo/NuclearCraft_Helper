package de.cosmoledo97.nuclearcraft_helper.util.handlers;

import de.cosmoledo97.nuclearcraft_helper.Reference;
import de.cosmoledo97.nuclearcraft_helper.blocks.fuel_crafter.ContainerFuelCrafter;
import de.cosmoledo97.nuclearcraft_helper.blocks.fuel_crafter.GuiFuelCrafter;
import de.cosmoledo97.nuclearcraft_helper.blocks.fuel_crafter.TileEntityFuelCrafter;
import de.cosmoledo97.nuclearcraft_helper.blocks.tiny_compactor.ContainerTinyCompactor;
import de.cosmoledo97.nuclearcraft_helper.blocks.tiny_compactor.GuiTinyCompactor;
import de.cosmoledo97.nuclearcraft_helper.blocks.tiny_compactor.TileEntityTinyCompactor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_FUEL_CRAFTER)
			return new GuiFuelCrafter(player.inventory, (TileEntityFuelCrafter) world.getTileEntity(new BlockPos(x, y, z)));

		if (ID == Reference.GUI_TINY_COMPACTOR)
			return new GuiTinyCompactor(player.inventory, (TileEntityTinyCompactor) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_FUEL_CRAFTER)
			return new ContainerFuelCrafter(player.inventory, (TileEntityFuelCrafter) world.getTileEntity(new BlockPos(x, y, z)));

		if (ID == Reference.GUI_TINY_COMPACTOR)
			return new ContainerTinyCompactor(player.inventory, (TileEntityTinyCompactor) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}
}
