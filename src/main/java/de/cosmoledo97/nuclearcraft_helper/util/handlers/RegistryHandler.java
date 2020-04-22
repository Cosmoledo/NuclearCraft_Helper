package de.cosmoledo97.nuclearcraft_helper.util.handlers;

import de.cosmoledo97.nuclearcraft_helper.Main;
import de.cosmoledo97.nuclearcraft_helper.Reference;
import de.cosmoledo97.nuclearcraft_helper.init.BlockInit;
import de.cosmoledo97.nuclearcraft_helper.init.ItemInit;
import de.cosmoledo97.nuclearcraft_helper.util.IHasModel;
import nc.enumm.MetaEnums.AlloyType;
import nc.enumm.MetaEnums.PartType;
import nc.init.NCItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {
	private static void addShapedRecipe(ItemStack output, Object... input) {
		ResourceLocation name = new ResourceLocation(Reference.MODID + ":" + output.getDisplayName());
		ResourceLocation group = null;
		GameRegistry.addShapedRecipe(name, group, output, input);
	}

	public static void initRegistries(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());

		addShapedRecipe(
			new ItemStack(BlockInit.BLOCK_FUEL_CRAFTER, 1),
			new Object[]{"ACA", "BDB", "AEA", 'A', new ItemStack(NCItems.part, 1, PartType.PLATE_ADVANCED.getID()), 'B',
					new ItemStack(NCItems.part, 1, PartType.MOTOR.getID()), 'C', new ItemStack(NCItems.alloy, 1, AlloyType.TOUGH.getID()), 'D',
					new ItemStack(NCItems.part, 1, PartType.CHASSIS.getID()), 'E', new ItemStack(NCItems.part, 1, PartType.ACTUATOR.getID()),});

		addShapedRecipe(
			new ItemStack(BlockInit.BLOCK_TINY_COMPACTOR, 1),
			new Object[]{"ACA", "BDB", "AEA", 'A', new ItemStack(NCItems.part, 1, PartType.PLATE_ADVANCED.getID()), 'B',
					new ItemStack(NCItems.part, 1, PartType.MOTOR.getID()), 'C', Blocks.OBSIDIAN, 'D', new ItemStack(NCItems.part, 1, PartType.CHASSIS.getID()),
					'E', new ItemStack(NCItems.part, 1, PartType.ACTUATOR.getID()),});
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (Item item : ItemInit.ITEMS)
			if (item instanceof IHasModel)
				((IHasModel) item).registerModels();

		for (Block block : BlockInit.BLOCKS)
			if (block instanceof IHasModel)
				((IHasModel) block).registerModels();
	}
}
