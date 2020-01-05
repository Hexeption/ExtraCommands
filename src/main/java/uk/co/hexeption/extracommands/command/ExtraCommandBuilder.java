package uk.co.hexeption.extracommands.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;

/**
 * ExtraCommandBuilder
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 05/01/2020 - 08:40 am
 */
public class ExtraCommandBuilder<T> {

    private LiteralArgumentBuilder<?> builder;
    private List<String> aliases = new ArrayList<>();

    /**
     * Adds a single command with no argument, recommenced for no argument commands
     *
     * @param command Name of Command
     * @param executor ICommandExecutor
     * @return ExtraCommandBuilder
     */
    public ExtraCommandBuilder addCommand(String command, ICommandExecutor executor) {
        return set(Commands.literal(command).executes(context -> {
            executor.onExecute(new ExtraCommandResult(context));
            return 1;
        }));
    }

    /**
     * Set's the LiteralArgumentBuilder of this commandBuilder, used for create a command tree
     *
     * @param argumentBuilder LiteralArgumentBuilder
     * @return ExtraCommandBuilder
     */
    public ExtraCommandBuilder set(LiteralArgumentBuilder argumentBuilder) {
        builder = argumentBuilder;
        return this;
    }

    /**
     * Appends a literal or an argument to the existing command tree
     *
     * @param argument LiteralArgumentBuilder
     * @return ExtraCommandBuilder
     */
    public ExtraCommandBuilder append(LiteralArgumentBuilder argument) {
        if (builder == null) {
            builder = argument;
        } else {
            builder.then(argument);
        }
        return this;
    }

    /**
     * Register an alternate name for the command.
     *
     * @param alias alternate name for the command
     * @return ExtraCommandBuilder
     */
    public ExtraCommandBuilder registerAlias(String alias) {
        aliases.add(alias);
        return this;
    }

    /**
     * Tab list of all player on the server.
     *
     * @return List of players.
     */
    public ArgumentType<T> getEntityArgumentType() {
        return (ArgumentType<T>) EntityArgument.players();
    }

    /**
     * Return the Builder for use at the end of the command tree.
     *
     * @return LiteralArgumentBuilder
     */
    protected LiteralArgumentBuilder build() {
        return builder;
    }

    /**
     * List of all alternate names for the command.
     *
     * @return an array of strings
     */
    public List<String> getAliases() {
        return aliases;
    }
}
