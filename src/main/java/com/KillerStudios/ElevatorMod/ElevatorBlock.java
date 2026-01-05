package com.KillerStudios.ElevatorMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ElevatorBlock extends Block {
    protected ElevatorBlock(Material materialIn) {
        super(materialIn);
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ){
        if (getSurroundingBlock(world, x-1, y, z-1, x+1, y, z+1)){
            player.addChatComponentMessage(new ChatComponentText("test"));
            return true;
        }else {
            player.addChatComponentMessage(new ChatComponentText("test failed"));
            return super.onBlockActivated(world, x, y, z, player, side, subX, subY, subZ);
        }
    }
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        if (placer instanceof EntityPlayer && world.getBlock(x, y, z) instanceof ElevatorBlock){
            if (getSurroundingBlock(world, x-1, y, z-1, x+1, y, z+1)){
                ((EntityPlayer) placer).addChatComponentMessage(new ChatComponentText("Test"));
            }else {
                ((EntityPlayer) placer).addChatComponentMessage(new ChatComponentText("test failed"));
            }
        }
    }
    public boolean getSurroundingBlock(World world, int minX, int minY, int minZ,int maxX, int maxY, int maxZ){
        int count = 0;
        int sizeX = (maxX - minX)+1;
        int sizeY = (maxY - minY)+1;
        int sizeZ = (maxZ - minZ)+1;
        int totalBlocks = sizeX*sizeY*sizeZ;
        for (int x = minX; x <= maxX; x++){
            for (int y = minY; y <= maxY; y++){
                for (int z = minZ; z <= maxZ; z++){
                    Block block = world.getBlock(x,y,z);
                    if (block instanceof ElevatorBlock){
                        count++;
                    }
                }
            }
        }
        return count == totalBlocks && count > 0;
    }
}
