package uk.co.hexeption.extracommands.config;

import net.minecraftforge.fml.config.ModConfig;

/**
 * ConfigHelper
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 05/01/2020 - 06:19 am
 */
public class ConfigHelper {

    private static ModConfig serverConfig;


    public static void bakeServer(final ModConfig config) {
        serverConfig = config;

        ExtraCommandsConfig.warps = ConfigHolder.SERVER.warps.get();

    }

    private static void setValueAndSave(final ModConfig modConfig, final String path,
        final Object newValue) {
        modConfig.getConfigData().set(path, newValue);
        modConfig.save();
    }
}
