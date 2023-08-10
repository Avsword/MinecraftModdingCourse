package tamk.aarov;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.PounceAtTargetGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;


public class CapybaraEntity extends HostileEntity {
    protected CapybaraEntity(EntityType<? extends HostileEntity> entityType, World world) {
      super(entityType, world);
      this.goalSelector.add(1, new WanderAroundFarGoal(this, 1.0D));
      this.goalSelector.add(1, new MeleeAttackGoal(this, 1, false));
      this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
      this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
      this.goalSelector.add(8, new LookAroundGoal(this));
      this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.4F));
      this.targetSelector.add(2, new ActiveTargetGoal<>(this, ChickenEntity.class, true));
    }
    
    @Override
    public void onDeath(net.minecraft.entity.damage.DamageSource cause) {
        super.onDeath(cause);
        
        // Tiputa appelsiini
        ItemStack opalItemStack = new ItemStack(Registries.ITEM.get(new Identifier("ooyo", "orange")));
        this.dropStack(opalItemStack);
    }
}
