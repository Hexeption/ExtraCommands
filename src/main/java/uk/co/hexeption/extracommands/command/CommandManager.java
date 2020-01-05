package uk.co.hexeption.extracommands.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.command.CommandSource;

/**
 * CommandManager
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 05/01/2020 - 08:38 am
 */
public class CommandManager {

    private CommandDispatcher<CommandSource> dispatcher;

    public CommandManager(
        CommandDispatcher<CommandSource> dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * The dispatcher object.
     *
     * @return Brigadier dispatcher object.
     */
    public CommandDispatcher<CommandSource> getDispatcher() {
        return dispatcher;
    }

    /**
     * Registers a commandbuilder
     *
     * @param commandBuilder Command Builder
     */
    public synchronized void registerCommand(ExtraCommandBuilder commandBuilder) {
        CommandNode<?> node = dispatcher.register(commandBuilder.build());
        commandBuilder.getAliases().forEach(o -> {
            LiteralArgumentBuilder argumentBuilder = LiteralArgumentBuilder.literal((String) o);
            dispatcher.register((LiteralArgumentBuilder) argumentBuilder.redirect(node));
        });
    }

    /**
     * Register a ExtraCommand
     *
     * @param commandBase ExtraCommand
     */
    public void registerCommand(ExtraCommandBase commandBase) {
        registerCommand(commandBase.getCommandBuilder());
    }


}
