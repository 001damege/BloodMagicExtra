package ooi.exbm.common.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import ooi.exbm.ExBMConfig;
import wayoftime.bloodmagic.altar.IBloodAltar;
import wayoftime.bloodmagic.event.SacrificeKnifeUsedEvent;
import wayoftime.bloodmagic.util.helper.PlayerSacrificeHelper;

public class ExPlayerSacrificeHelper {
    public static boolean playerHealth(Player player) {
        double amount = PlayerSacrificeHelper.getPlayerIncense(player);
        if (amount >= 0) {
            float health = player.getHealth();
            float maxHealth = player.getMaxHealth();
            if (health > maxHealth / 5.0) {
                float sacrificedHealth = health - maxHealth / 5.0f;
                int lpAdded = (int) (sacrificedHealth * ExBMConfig.COMMON.daggerConversion.get() * PlayerSacrificeHelper.getModifier(amount));
                IBloodAltar altar = PlayerSacrificeHelper.getAltar(player.getCommandSenderWorld(), player.blockPosition());
                if (altar != null) {
                    SacrificeKnifeUsedEvent event = new SacrificeKnifeUsedEvent(player, true, true, (int) sacrificedHealth, lpAdded);
                    if (MinecraftForge.EVENT_BUS.post(event)) {
                        return false;
                    }

                    altar.sacrificialDaggerCall(event.lpAdded, false);
                    altar.startCycle();
                    player.setHealth(maxHealth / 5.0f);
                    PlayerSacrificeHelper.setPlayerIncense(player, 0);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean findAndFillAltar(Level level, LivingEntity entity, int amount, boolean isSacrifice) {
        IBloodAltar altar = PlayerSacrificeHelper.getAltar(level, entity.blockPosition());
        if (altar == null) {
            return false;
        } else {
            altar.sacrificialDaggerCall(amount, isSacrifice);
            altar.startCycle();
            return true;
        }
    }
}
