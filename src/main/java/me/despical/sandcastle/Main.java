package me.despical.sandcastle;

import me.despical.commandframework.CommandFramework;
import me.despical.sandcastle.command.impl.SandCastleCommands;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Despical
 * <p>
 * Created at 24.11.2022
 */
public class Main extends JavaPlugin {

	private CommandFramework commandFramework;

	@Override
	public void onEnable() {
		this.commandFramework = new CommandFramework(this);

		new SandCastleCommands();
	}

	public CommandFramework getCommandFramework() {
		return commandFramework;
	}
}