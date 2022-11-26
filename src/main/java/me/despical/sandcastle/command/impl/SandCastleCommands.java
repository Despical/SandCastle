package me.despical.sandcastle.command.impl;

import me.despical.commandframework.Command;
import me.despical.commandframework.CommandArguments;
import me.despical.sandcastle.command.ICommand;
import me.despical.sandcastle.imp.SandCastle;

import org.bukkit.entity.Player;

/**
 * @author Despical
 * <p>
 * Created at 24.11.2022
 */
public class SandCastleCommands implements ICommand {

	@Command(
			name = "sandcastle",
			permission = "sandcastle.use",
			min = 2,
			senderType = Command.SenderType.PLAYER
	)
	public void sandCastle(CommandArguments arguments) {
		Player player = arguments.getSender();

		new SandCastle(player.getLocation().clone());
	}

	{
		register(this);
	}
}