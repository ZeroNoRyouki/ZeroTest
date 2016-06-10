package zero.mods.zerotest.common.block.ctx;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import zero.mods.zerocore.lib.BlockFacings;
import zero.mods.zerocore.lib.PropertyBlockFacings;
import zero.mods.zerotest.common.block.TestBlockBase;

public class OrnatedEmeraldBlock extends TestBlockBase {

    public OrnatedEmeraldBlock(String blockName) {

        super(blockName, Material.iron);

        this._actualFacings = new boolean[EnumFacing.VALUES.length];

        this.setDefaultState(
                this.blockState.getBaseState().withProperty(PropertyBlockFacings.FACINGS, PropertyBlockFacings.None)
        );
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos position) {

        Block thisBlock = state.getBlock();
        int len = EnumFacing.VALUES.length;

        for (int i = 0; i < len; ++i) {

            IBlockState neighbor = world.getBlockState(position.offset(EnumFacing.VALUES[i]));

            this._actualFacings[i] = thisBlock == neighbor.getBlock();
        }

        BlockFacings facings = BlockFacings.from(this._actualFacings);

        return state.withProperty(PropertyBlockFacings.FACINGS, facings.toProperty());
    }

    @Override
    public int getMetaFromState(IBlockState state) {

        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, PropertyBlockFacings.FACINGS);
    }

    private boolean[] _actualFacings;
}
