package de.cosmoledo97.nuclearcraft_helper;

import org.apache.logging.log4j.Logger;

import de.cosmoledo97.nuclearcraft_helper.proxy.CommonProxy;
import de.cosmoledo97.nuclearcraft_helper.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MCVERSION, dependencies = "after:nuclearcraft")
public class Main {
	private static Logger logger;

	@Instance
	public static Main instance;

	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
	public static CommonProxy proxy;

	public static final CreativeTabs TAB = new Tab();

	public static void info(Object... objects) {
		String out = "";
		for (Object object : objects)
			out += object + " ";
		logger.info(out);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		RegistryHandler.initRegistries(event);
	}

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
	}
}