package uk.co.hexeption.extracommands.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * ConfigHolder
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 05/01/2020 - 06:22 am
 */
public class ConfigHolder {

    public static final ForgeConfigSpec SERVER_SPEC;
    static final ServerConfig SERVER;

    static {
        final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder()
            .configure(ServerConfig::new);
        SERVER = specPair.getLeft();
        SERVER_SPEC = specPair.getRight();
    }

}
