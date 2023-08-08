package tamk.aarov;

import java.util.logging.Logger;

import org.joml.Vector3d;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.World.ExplosionSourceType;

public class Cabostaff extends Item {


    public Cabostaff(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){

        Logger LOGGER = Logger.getLogger("ooyo");
        LOGGER.info("Cabostaff used!");
        final Double distance = 20.0;
        final Double distanceY = Math.sqrt(2 * (distance*distance));
        final Vector3d frontOfPlayer = new Vector3d(user.getX()+(user.getRotationVector().x*distance), user.getY()+(user.getRotationVector().y*distanceY), user.getZ()+(user.getRotationVector().z*distance));
 
        final CatEntity  aa = new CatEntity(EntityType.CAT, world);
        aa.setPos(user.getX(), user.getY()+1.5, user.getZ());
        world.spawnEntity(aa);
        aa.takeKnockback(3, user.getRotationVector().x*-1, user.getRotationVector().z*-1);
        aa.setUpwardSpeed(3f);

        // FOR NOW. LATER WILL BE MADE IN TO A THROWABLE ENTITY
        final LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightning.setPos(frontOfPlayer.x, frontOfPlayer.y, frontOfPlayer.z);
        world.spawnEntity(lightning);
        world.createExplosion(user, frontOfPlayer.x, frontOfPlayer.y, frontOfPlayer.z, 2, false, ExplosionSourceType.TNT);


        return TypedActionResult.success(user.getStackInHand(hand)); 
    }
    

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand){
        entity.takeKnockback(5, 2, 2);

        return ActionResult.SUCCESS;
    }

    
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.takeKnockback(5, 2, 2);
        final World world = attacker.getEntityWorld();
        final LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightning.setPos(target.getX(), target.getY(), target.getZ());
        world.spawnEntity(lightning);
        return true;
    }
    
}
