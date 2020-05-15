package de.cosmoledo97.nuclearcraft_helper;

import net.minecraft.util.ResourceLocation;

public class Reference {
	public static final String MODID = "nuclearcraft_helper";
	public static final String NAME = "NuclearCraft Helper";

	public static final String VERSION = "1.0.3";
	public static final String MCVERSION = "1.12.2";

	public static final String CLIENT = "de.cosmoledo97.nuclearcraft_helper.proxy.ClientProxy";
	public static final String SERVER = "de.cosmoledo97.nuclearcraft_helper.proxy.CommonProxy";

	public static final ResourceLocation TEXTURE = new ResourceLocation(MODID + ":textures/gui/interface.png");

	public static final int GUI_FUEL_CRAFTER = 1;
	public static final int GUI_TINY_COMPACTOR = 2;
}
