package uk.co.hexeption.extracommands.command.commands;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.TranslationTextComponent;
import uk.co.hexeption.extracommands.command.ExtraCommandBase;
import uk.co.hexeption.extracommands.command.ExtraCommandBuilder;

/**
 * EnderChest
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 06/01/2020 - 02:34 am
 */
public class EnderChest extends ExtraCommandBase {

    @Override
    public ExtraCommandBuilder getCommandBuilder() {
        return new ExtraCommandBuilder().addCommand("enderchest", result -> {
            ServerPlayerEntity playerEntity = (ServerPlayerEntity) ((CommandSource) result
                .getContext().getSource()).getEntity();
            EnderChestInventory inv = playerEntity.getInventoryEnderChest();
            playerEntity.openContainer(new SimpleNamedContainerProvider(
                (p_createMenu_1_, p_createMenu_2_, p_createMenu_3_) -> ChestContainer
                    .createGeneric9X3(p_createMenu_1_, p_createMenu_2_, inv),
                new TranslationTextComponent("container.enderchest")));
            playerEntity.addStat(Stats.OPEN_ENDERCHEST);
        }).registerAlias("ec");
    }
}
