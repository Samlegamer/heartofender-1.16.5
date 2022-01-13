package fr.samlegamer.heartofender.utils;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class HoeBlocksUtils
{
	public static final BlockBehaviour.Properties STONE_BASE = BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).requiresCorrectToolForDrops();
	
	public static final BlockBehaviour.Properties METAL_BASE = BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).requiresCorrectToolForDrops();
	
	public static final BlockBehaviour.Properties GLOWSTONE_BASE = BlockBehaviour.Properties.of(Material.GLASS).sound(SoundType.GLASS).strength(1, 10).lightLevel((p_235468_0_) -> { return 20; });
	
	public static final BlockBehaviour.Properties FIRE_BASE = BlockBehaviour.Properties.of(Material.FIRE, MaterialColor.COLOR_LIGHT_GREEN).instabreak().noCollission().lightLevel((p_235468_0_) -> {return 30;}).sound(SoundType.WOOL).noDrops();
}