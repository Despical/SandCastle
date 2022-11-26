package me.despical.sandcastle.command;

import me.despical.sandcastle.Main;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Despical
 * <p>
 * Created at 24.11.2022
 */
public interface ICommand {

	Main plugin = JavaPlugin.getPlugin(Main.class);

	default void register(Object clazz) {
		plugin.getCommandFramework().registerCommands(clazz);
	}
}