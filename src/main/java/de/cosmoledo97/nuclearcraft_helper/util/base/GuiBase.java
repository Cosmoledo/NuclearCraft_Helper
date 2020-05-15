package de.cosmoledo97.nuclearcraft_helper.util.base;

import nc.gui.NCGui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiBase extends NCGui {
	private final ResourceLocation texture;
	private final TileEntityBase tileentity;

	public GuiBase(InventoryPlayer playerInv, TileEntityBase tileentity, ResourceLocation texture) {
		super(new ContainerBase(playerInv, tileentity));
		this.tileentity = tileentity;
		this.texture = texture;

		this.xSize = 178;
		this.ySize = 256;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(this.texture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize - this.fontRenderer.getStringWidth(tileName)) / 2, 8, 4210752);
	}
}