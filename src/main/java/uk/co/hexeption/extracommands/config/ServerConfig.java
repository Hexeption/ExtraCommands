package uk.co.hexeption.extracommands.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import uk.co.hexeption.extracommands.ExtraCommands;

/**
 * ServerConfig
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 05/01/2020 - 06:23 am
 */
public class ServerConfig {


    /**
     * Warps
     */
    final ConfigValue<List<String>> warps;


    ServerConfig(final ForgeConfigSpec.Builder builder) {
        builder.comment("Base Configuration").push("base");
        builder.pop();

        builder.comment("Commands Configuration").push("command");
        builder.pop();

        builder.comment("Warp Configuration").push("warp");
        warps = builder
            .comment("List of Warps")
            .translation(ExtraCommands.MODID + ".config.warp.warps")
            .define("warps", new ArrayList<>());
        builder.pop();
    }
}
