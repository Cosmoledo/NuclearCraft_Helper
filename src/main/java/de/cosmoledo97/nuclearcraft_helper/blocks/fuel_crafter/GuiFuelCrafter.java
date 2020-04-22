package de.cosmoledo97.nuclearcraft_helper.blocks.fuel_crafter;

import de.cosmoledo97.nuclearcraft_helper.Reference;
import nc.gui.NCGui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFuelCrafter extends NCGui {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/interface.png");
	private final TileEntityFuelCrafter tileentity;

	public GuiFuelCrafter(InventoryPlayer player, TileEntityFuelCrafter tileentity) {
		super(new ContainerFuelCrafter(player, tileentity));
		this.tileentity = tileentity;

		this.xSize = 178;
		this.ySize = 256;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize - this.fontRenderer.getStringWidth(tileName)) / 2, 8, 4210752);
	}
}