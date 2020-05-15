package de.cosmoledo97.nuclearcraft_helper.blocks.tiny_compactor;

import de.cosmoledo97.nuclearcraft_helper.Main;
import de.cosmoledo97.nuclearcraft_helper.Reference;
import de.cosmoledo97.nuclearcraft_helper.util.base.BlockBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTinyCompactor extends BlockBase {
	public BlockTinyCompactor(String name) {
		super(name);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityTinyCompactor();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX,
			float hitY, float hitZ) {
		if (!worldIn.isRemote)
			playerIn.openGui(Main.instance, Reference.GUI_TINY_COMPACTOR, worldIn, pos.getX(), pos.getY(), pos.getZ());

		return true;
	}
}