package ooi.exbm.common.item;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import ooi.exbm.ExBMConfig;
import ooi.exbm.common.util.ExPlayerSacrificeHelper;
import wayoftime.bloodmagic.common.item.ItemSacrificialDagger;
import wayoftime.bloodmagic.event.SacrificeKnifeUsedEvent;
import wayoftime.bloodmagic.util.helper.IncenseHelper;
import wayoftime.bloodmagic.util.helper.PlayerHelper;

public class ItemExtraSacrificialDagger extends ItemSacrificialDagger {
    public ItemExtraSacrificialDagger(Properties props) {
        super();
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player && !entity.getCommandSenderWorld().isClientSide) {
            if (ExPlayerSacrificeHelper.playerHealth((Player) entity)) {
                IncenseHelper.setHasMaxIncense(stack, (Player) entity, false);
            }
        }
        super.releaseUsing(stack, level, entity, timeLeft);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 36000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (PlayerHelper.isFakePlayer(player)) {
            return super.use(level, player, hand);
        }

        if (this.canUseForSacrifice(stack)) {
            player.startUsingItem(hand);
            return InteractionResultHolder.success(stack);
        }
        int lpAdded = ExBMConfig.COMMON.daggerConversion.get() * 4;

        if (!player.getAbilities().instabuild) {
            SacrificeKnifeUsedEvent event = new SacrificeKnifeUsedEvent(player, true, true, 1, lpAdded);
            if (MinecraftForge.EVENT_BUS.post(event)) {
                return super.use(level, player, hand);
            }

            if (event.shouldDrainHealth) {
                player.invulnerableTime = 0;

                player.setHealth(Math.max(player.getHealth() - 1.998f, 0.0001f));
                if (player.getHealth() <= 0.001f && !level.isClientSide) {
                    player.invulnerableTime = 0;

                }
            }

            if (!event.shouldFillAltar) {
                return super.use(level, player, hand);
            }
            lpAdded = event.lpAdded;
        } else if (player.isShiftKeyDown()) {
            lpAdded = Integer.MAX_VALUE;
        }

        double posX = player.getX();
        double posY = player.getY();
        double posZ = player.getZ();
        level.playSound(player, posX, posY, posZ, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5f, 2.6f + (level.random.nextFloat() - level.random.nextFloat()) * 0.8f);

        for (int i = 0; i < 8; i++) {
            level.addParticle(DustParticleOptions.REDSTONE, posX + Math.random() - Math.random(), posY + Math.random() - Math.random(), posZ + Math.random() - Math.random(), 0, 0, 0);
        }

        if (!level.isClientSide && PlayerHelper.isFakePlayer(player)) {
            return super.use(level, player, hand);
        }

        ExPlayerSacrificeHelper.findAndFillAltar(level, player, lpAdded, false);
        return super.use(level, player, hand);
    }
}
