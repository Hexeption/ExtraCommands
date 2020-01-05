package uk.co.hexeption.extracommands.command;

/**
 * ICommandExecutor
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 05/01/2020 - 08:42 am
 */
@FunctionalInterface
public interface ICommandExecutor {

    void onExecute(ExtraCommandResult result);
}
