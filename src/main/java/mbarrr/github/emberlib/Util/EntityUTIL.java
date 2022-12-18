package mbarrr.github.emberlib.Util;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class EntityUTIL {

    public boolean safeHealEntity(LivingEntity entity, double healAmount){

        if(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH) == null) return false;
        double maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        if(entity.getHealth()+healAmount > maxHealth) return false;

        entity.setHealth(entity.getHealth()+healAmount);
        return true;
    }
}
