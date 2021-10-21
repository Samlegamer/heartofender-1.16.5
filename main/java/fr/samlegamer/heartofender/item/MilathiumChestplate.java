package fr.samlegamer.heartofender.item;

import fr.samlegamer.heartofender.core.References;
import fr.samlegamer.heartofender.inits.ItemsMod;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class MilathiumChestplate extends ItemArmor
{
	public MilathiumChestplate(String name, ArmorMaterial azuriumMat, EntityEquipmentSlot equipmentSlotIn) {
		super(azuriumMat, 0, equipmentSlotIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        if(repair.getItem() == ItemsMod.milathium_ingot)
        {
            return true;
        }
        return false;
    }
}