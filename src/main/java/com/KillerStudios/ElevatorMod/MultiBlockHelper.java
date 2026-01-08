package com.KillerStudios.ElevatorMod;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MultiBlockHelper {
    public ArrayList<Block> blocks = new ArrayList<>();
    public HashMap<String, Block> coords = new HashMap<>();
    public HashMap<String, Block> master = new HashMap<>();
    boolean isMaster = false;
    boolean isSlave = false;
    public boolean getSurroundingBlock(World world, int minX, int minY, int minZ, int maxX, int maxY, int maxZ){
        blocks.clear();
        int count = 0;
        int sizeX = (maxX - minX)+1;
        int sizeY = (maxY - minY)+1;
        int sizeZ = (maxZ - minZ)+1;
        int totalBlocks = sizeX*sizeY*sizeZ;
        for (int x = minX; x <= maxX; x++){
            for (int y = minY; y <= maxY; y++){
                for (int z = minZ; z <= maxZ; z++){
                    String key  = x + "," + y + "," + z;
                    String masterKey = x + "," + y + "," + z;
                    Block block = world.getBlock(x,y,z);
                    if (block instanceof ElevatorBlock) {
                        count++;
                        blocks.add(block);
                        coords.put(key, block);
                        isMaster = false;
                        isSlave = true;
                    }
                    if (block instanceof ElevatorBlockMaster){
                        count++;
                        blocks.add(block);
                        master.put(masterKey, block);
                        coords.put(key, block);
                        isMaster = true;
                        isSlave = false;
                    }
                }
            }
        }
        return count == totalBlocks && count > 0;
    }
    public void isFormed(World world, int x, int y, int z, EntityPlayer player){
        int meta = world.getBlockMetadata(x,y,z);
        player.addChatComponentMessage(new ChatComponentText("block meta: " + meta));
        player.addChatComponentMessage(new ChatComponentText("List: " + blocks.size()));
        if ((getSurroundingBlock(world, x-1, y, z-1, x+1, y, z+1 ) && isMaster && isSlave)|| isPartOf(world, x,y,z, meta)){
            player.addChatComponentMessage(new ChatComponentText("Test Successful"));
            changeMetaData(world, 9);
        } else {
            player.addChatComponentMessage(new ChatComponentText("Test failed"));
        }
    }
    public boolean isPartOf(World world, int x, int y, int z, int meta){
        Block block = world.getBlock(x,y,z);
        return (block instanceof ElevatorBlock || block instanceof ElevatorBlockMaster) && blocks.contains(block) && (meta == 1 || meta == 2);
    }
    public boolean checkCenter(){
        for (Block block : blocks){
            if (block instanceof ElevatorBlockMaster){
                return true;
            }
        }
        return false;
    }
    public void changeMetaData(World world, int maxSize){
        int count = 0;
        for (Map.Entry<String, Block> BC : coords.entrySet()){
            String coords = BC.getKey();
            Block block = BC.getValue();
            String[] parts = coords.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            int z = Integer.parseInt(parts[2]);
            if (block instanceof ElevatorBlock) {
                world.setBlockMetadataWithNotify(x, y, z,1, 2);
                count++;
                if (count >= maxSize){
                    return;
                }
            }else if (block instanceof ElevatorBlockMaster){
                world.setBlockMetadataWithNotify(x, y, z, 2, 2);
                if (count >= maxSize){
                    return;
                }
            }
        }
    }
}
