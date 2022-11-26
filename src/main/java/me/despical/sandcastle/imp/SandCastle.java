package me.despical.sandcastle.imp;

import me.despical.commons.compat.XMaterial;
import me.despical.sandcastle.command.ICommand;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Despical
 * <p>
 * Created at 24.11.2022
 */
public class SandCastle extends BukkitRunnable {

	private static final ItemStack SAND_BLOCK = XMaterial.SAND.parseItem();
	private static final double[][] wallPositions   = {{1.25, 0}, {1.25, .425}, {1.25, .825}, {1.25, -.425}, {1.25, -.825}, {.825, 0.825}, {.425, 0.825}, {0, 0.825}, {-.425, 0.825}, {-.825, 0.825}, {.825, -0.825}, {.425, -0.825}, {0, -0.825}, {-.425, -0.825}, {-.825, -0.825}, {-1.25, .825}, {-1.25, .425}, {-1.25, 0}, {-1.25, -.425}, {-1.25, -.825}};
	private static final double[][] cornerPositions = {{1.25, 0}, {1.25, .825}, {1.25, -.825}, {.425, 0.825}, {-.425, 0.825}, {.425, -0.825}, {-.425, -0.825}, {-1.25, .825}, {-1.25, 0}, {-1.25, -.825}};

	private int y;

	private final Location origin;
	private final List<ArmorStand> armorStands;

	public SandCastle(Location origin) {
		this.origin = origin;
		this.armorStands = new ArrayList<>();
		this.createSandBlocks();
		this.runTaskTimer(ICommand.plugin, 20L, 1L);
	}

	@Override
	public void run() {
		if (++y > 77) {

			int estimatedTime = (y - 77) / 20;

			if (estimatedTime > 2) {
				for (ArmorStand stand : armorStands) {
					stand.teleport(origin.clone().add(0, .1, 0));
				}

				if (estimatedTime > 4) {
					armorStands.forEach(ArmorStand::remove);
					cancel();
				}
			}

			return;
		}

		for (ArmorStand stand : armorStands) {
			stand.teleport(stand.getLocation().clone().add(0, .02, 0));
		}
	}

	private void createSandBlocks() {
		origin.setYaw(0);
		origin.setPitch(90);
		origin.subtract(-.5, 2, -.5);

		for (double y = 0; y <= 2; y++) {
			if (y == 2) {
				for (double[] positions : cornerPositions) {
					spawnArmorStand(positions[0], y * .42, positions[1]);
				}

				break;
			}

			for (double[] positions : wallPositions) {
				spawnArmorStand(positions[0], y * .42, positions[1]);
			}
		}
	}

	private void spawnArmorStand(double x, double y, double z) {
		final ArmorStand armorStand = (ArmorStand) origin.getWorld().spawnEntity(origin.clone().add(x, y, z), EntityType.ARMOR_STAND);
		armorStand.setSmall(true);
		armorStand.setArms(false);
		armorStand.setGravity(false);
		armorStand.setInvisible(true);
		armorStand.setHelmet(SAND_BLOCK);

		this.armorStands.add(armorStand);
	}
}