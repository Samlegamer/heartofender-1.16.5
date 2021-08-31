package fr.samlegamer.heartofender.block;

import fr.samlegamer.heartofender.entity.EntityHerobrine;
import fr.samlegamer.heartofender.inits.BlocksMod;
import fr.samlegamer.heartofender.utils.config.HoeConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HerobrineHeadBlock extends Block
{
	public HerobrineHeadBlock(String name, Material materialIn)
	{
		super(materialIn);
		setUnlocalizedName(name);
		setRegistryName(name);
        this.setSoundType(SoundType.STONE);
	}
	
	//this code, spawn entity herobrine in the world to summon
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if (HoeConfig.enableHerobrine)
		{
		IBlockState diamond_block = worldIn.getBlockState(pos.down());
		IBlockState azurium_block = worldIn.getBlockState(pos.down(2));
		IBlockState diamond_block1 = worldIn.getBlockState(pos.down().east());
		IBlockState diamond_block2 = worldIn.getBlockState(pos.down().west());
		IBlockState diamond_block3 = worldIn.getBlockState(pos.down().north());
		IBlockState diamond_block4 = worldIn.getBlockState(pos.down().south());

		if(!worldIn.isRemote)
		{
		if(placer instanceof EntityPlayer)
		{
			
			if(diamond_block.getBlock()!= Blocks.AIR && azurium_block.getBlock()!= Blocks.AIR)
			{
				
				if(diamond_block.getBlock() == Blocks.DIAMOND_BLOCK && azurium_block.getBlock() == BlocksMod.azurium_block)
				{
										
					EntityHerobrine herobrine = new EntityHerobrine(worldIn);
									
					
					herobrine.setPosition(pos.getX(), pos.getY(), pos.getZ());
					
					worldIn.destroyBlock(pos, false);
					worldIn.destroyBlock(pos.down(), false);
					worldIn.destroyBlock(pos.down(2), false);
					worldIn.destroyBlock(pos.down().east(), false);
					worldIn.destroyBlock(pos.down().west(), false);
					worldIn.destroyBlock(pos.down().north(), false);
					worldIn.destroyBlock(pos.down().south(), false);

					
					worldIn.spawnEntity(herobrine);
					
										
				}
			}
		}
	  }
	}
	}
}