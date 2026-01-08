package com.KillerStudios.ElevatorMod;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class Blocks {
    public static Block ElevatorBlock;
    public static Block ElevatorBlockMaster;

    public static void init(){
        FMLLog.info("initializing blocks");
        ElevatorBlock = new ElevatorBlock(Material.iron)
            .setBlockName("Elevator")
            .setCreativeTab(CreativeTabs.tabBlock)
            .setHardness(5.0F)
            .setBlockTextureName("em:Elevator")
        ;
        ElevatorBlockMaster = new ElevatorBlockMaster(Material.iron)
            .setBlockName("Elevator Controller")
            .setCreativeTab(CreativeTabs.tabBlock)
            .setHardness(5.0F)
            .setBlockTextureName("em:ElevatorMaster");
        FMLLog.info("Finished initializing blocks");
    }
    public static void register(){
        FMLLog.info("registering blocks");
        GameRegistry.registerBlock(ElevatorBlock, ElevatorBlock.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(ElevatorBlockMaster, ElevatorBlockMaster.getUnlocalizedName().substring(5));
        FMLLog.info("Finished registering blocks");
    }
}
