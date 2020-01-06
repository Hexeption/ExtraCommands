package uk.co.hexeption.extracommands.command.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import uk.co.hexeption.extracommands.command.ExtraCommandBase;
import uk.co.hexeption.extracommands.command.ExtraCommandBuilder;

/**
 * Heal
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 06/01/2020 - 02:21 am
 */
public class Heal extends ExtraCommandBase {

    @Override
    public ExtraCommandBuilder getCommandBuilder() {
        return new ExtraCommandBuilder().set(LiteralArgumentBuilder.literal("heal").executes(e -> {
            onExecute(e, false);
            return 1;
        }).then(RequiredArgumentBuilder.argument("targets", EntityArgument.players())
            .executes(e -> {
                onExecute(e, true);
                return 1;
            })));
    }

    public void onExecute(CommandContext context, boolean remoteCall) {
        ServerPlayerEntity playerEntity = null;
        try {
            playerEntity =
                remoteCall ? EntityArgument.getPlayer(context, "targets")
                    : (ServerPlayerEntity) ((CommandSource) context.getSource()).getEntity();
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        playerEntity.setHealth(playerEntity.getMaxHealth());
        playerEntity.getFoodStats().setFoodLevel(20);

    }
}