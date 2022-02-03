package fr.samlegamer.heartofender.block;

import net.minecraft.block.BlockMagma;

public class BlockBlueMagmaBlock extends BlockMagma
{
	public BlockBlueMagmaBlock(String name)
    {
        super();
        setUnlocalizedName(name);
		setRegistryName(name);       
		this.setLightLevel(0.2F);
        this.setTickRandomly(true);
    }
}