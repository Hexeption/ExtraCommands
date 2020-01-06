package uk.co.hexeption.extracommands;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.hexeption.extracommands.command.CommandManager;
import uk.co.hexeption.extracommands.command.commands.EnderChest;
import uk.co.hexeption.extracommands.command.commands.Fly;
import uk.co.hexeption.extracommands.command.commands.God;
import uk.co.hexeption.extracommands.command.commands.Heal;
import uk.co.hexeption.extracommands.config.ConfigHelper;
import uk.co.hexeption.extracommands.config.ConfigHolder;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExtraCommands.MODID)
public class ExtraCommands {

    public final static String MODID = "extracommands";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public CommandManager commandManager;

    public ExtraCommands() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onModConfigEvent);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(Type.SERVER, ConfigHolder.SERVER_SPEC);
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");

        commandManager = new CommandManager(event.getCommandDispatcher());
        commandManager.registerCommand(new God());
        commandManager.registerCommand(new Fly());
        commandManager.registerCommand(new Heal());
        commandManager.registerCommand(new EnderChest());
    }

    @SubscribeEvent
    public void onServerStarted(FMLServerStartedEvent event) {
    }

    @SubscribeEvent
    public void onModConfigEvent(ModConfig.ModConfigEvent event) {
        LOGGER.info("HELLO from config event");

        final ModConfig config = event.getConfig();

        if (config.getSpec() == ConfigHolder.SERVER_SPEC) {
            ConfigHelper.bakeServer(config);
        }
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onBlocksRegistry(
            final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
