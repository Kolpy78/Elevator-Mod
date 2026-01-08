package com.KillerStudios.ElevatorMod;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ElevatorBlockMaster extends Block implements ITileEntityProvider {
    MultiBlockHelper multiBlockHelper = new MultiBlockHelper();
    protected ElevatorBlockMaster(Material materialIn) {
        super(Material.iron);
    }
    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        if (meta == 2){
            return new TileEntityElevatorBlock();
        } return null;
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ){
        multiBlockHelper.isFormed(world, x, y, z, player);
        return super.onBlockActivated(world, x, y, z, player, side, subX, subY, subZ);
    }
}
