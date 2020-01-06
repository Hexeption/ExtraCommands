package uk.co.hexeption.extracommands.command;

import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Collection;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

/**
 * ExtraCommandResult
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 05/01/2020 - 08:43 am
 */
public class ExtraCommandResult {

    private CommandContext<?> context;

    public ExtraCommandResult(CommandContext<?> context) {
        this.context = context;
    }

    public String getString(String name) {
        return StringArgumentType.getString(context, name);
    }

    public Object getCustom(String node, Class<?> clazz) {
        return context.getArgument(node, clazz);
    }

    public int getInteger(String name) {
        return IntegerArgumentType.getInteger(context, name);
    }

    public float getFloat(String node) {
        return FloatArgumentType.getFloat(context, node);
    }

    public double getDouble(String node) {
        return DoubleArgumentType.getDouble(context, node);
    }

    public boolean getBoolean(String node) {
        return BoolArgumentType.getBool(context, node);
    }

    public Collection<ServerPlayerEntity> getEntity(String node) throws CommandSyntaxException {
        return EntityArgument.getPlayers((CommandContext<CommandSource>) context, node);
    }

    public CommandContext<?> getContext() {
        return context;
    }
}
