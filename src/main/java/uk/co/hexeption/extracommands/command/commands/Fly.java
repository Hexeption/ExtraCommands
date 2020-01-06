package uk.co.hexeption.extracommands.command.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameType;
import uk.co.hexeption.extracommands.command.ExtraCommandBase;
import uk.co.hexeption.extracommands.command.ExtraCommandBuilder;

/**
 * Fly
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 06/01/2020 - 02:23 am
 */
public class Fly extends ExtraCommandBase {

    @Override
    public ExtraCommandBuilder getCommandBuilder() {
        return new ExtraCommandBuilder().set(LiteralArgumentBuilder.literal("fly").executes(e -> {
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

        if (playerEntity.interactionManager.getGameType() == GameType.SURVIVAL
            || playerEntity.interactionManager.getGameType() == GameType.ADVENTURE) {
            if (!playerEntity.abilities.allowFlying) {
                playerEntity.abilities.allowFlying = true;
                playerEntity.sendPlayerAbilities();

                playerEntity.sendMessage(
                    new StringTextComponent("Fly Enabled!")
                        .setStyle(new Style().setColor(TextFormatting.GREEN)));

            } else {
                playerEntity.abilities.allowFlying = false;
                playerEntity.abilities.isFlying = false;
                playerEntity.sendPlayerAbilities();

                playerEntity.sendMessage(
                    new StringTextComponent("Fly Disabled!")
                        .setStyle(new Style().setColor(TextFormatting.GREEN)));
            }
        } else {
            playerEntity.sendMessage(new StringTextComponent("commands.god.error")
                .setStyle(new Style().setColor(TextFormatting.RED)));
        }

    }
}