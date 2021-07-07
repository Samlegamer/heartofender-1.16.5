package fr.samlegamer.heartofender.block;

import java.util.Random;

import fr.samlegamer.heartofender.inits.BlocksMod;
import fr.samlegamer.heartofender.inits.ItemsMod;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHeartWart extends BlockBush
{
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);
    private static final AxisAlignedBB[] NETHER_WART_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D)};

    public BlockHeartWart(String name)
    {
        super(Material.PLANTS, MapColor.GREEN);
        setUnlocalizedName(name);
		setRegistryName(name);
        this.setSoundType(SoundType.CLOTH);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
        this.setTickRandomly(true);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return NETHER_WART_AABB[((Integer)state.getValue(AGE)).intValue()];
    }

    /**
     * Return true if the block can sustain a Bush
     */
    public boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == BlocksMod.dead_sand;
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        return super.canBlockStay(worldIn, pos, state);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        int i = ((Integer)state.getValue(AGE)).intValue();

        if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(10) == 0))
        {
            IBlockState newState = state.withProperty(AGE, Integer.valueOf(i + 1));
            worldIn.setBlockState(pos, newState, 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, newState);
        }

        super.updateTick(worldIn, pos, state, rand);
    }

    @SuppressWarnings("unused")
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
        if (false && !worldIn.isRemote)
        {
            int i = 1;

            if (((Integer)state.getValue(AGE)).intValue() >= 3)
            {
                i = 2 + worldIn.rand.nextInt(3);

                if (fortune > 0)
                {
                    i += worldIn.rand.nextInt(fortune + 1);
                }
            }

            for (int j = 0; j < i; ++j)
            {
                spawnAsEntity(worldIn, pos, new ItemStack(BlocksMod.heart_wart_block));
            }
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 0;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(BlocksMod.heart_wart_block);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }

    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        int count = 1;

        if (((Integer)state.getValue(AGE)) >= 3)
        {
            count = 2 + rand.nextInt(3) + (fortune > 0 ? rand.nextInt(fortune + 1) : 0);
        }

        for (int i = 0; i < count; i++)
        {
            drops.add(new ItemStack(BlocksMod.heart_wart_block));
        }
    }

    public BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }
}
