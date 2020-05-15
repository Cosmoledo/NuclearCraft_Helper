package de.cosmoledo97.nuclearcraft_helper.util.handlers;

import de.cosmoledo97.nuclearcraft_helper.Reference;
import de.cosmoledo97.nuclearcraft_helper.util.base.ContainerBase;
import de.cosmoledo97.nuclearcraft_helper.util.base.GuiBase;
import de.cosmoledo97.nuclearcraft_helper.util.base.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_FUEL_CRAFTER)
			return new GuiBase(player.inventory, (TileEntityBase) world.getTileEntity(new BlockPos(x, y, z)), Reference.TEXTURE);

		if (ID == Reference.GUI_TINY_COMPACTOR)
			return new GuiBase(player.inventory, (TileEntityBase) world.getTileEntity(new BlockPos(x, y, z)), Reference.TEXTURE);

		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_FUEL_CRAFTER)
			return new ContainerBase(player.inventory, (TileEntityBase) world.getTileEntity(new BlockPos(x, y, z)));

		if (ID == Reference.GUI_TINY_COMPACTOR)
			return new ContainerBase(player.inventory, (TileEntityBase) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}
}
