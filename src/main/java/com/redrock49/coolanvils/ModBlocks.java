package com.redrock49.coolanvils;


import com.redrock49.coolanvils.block.custom.MythrilAnvilBlock;
import com.redrock49.coolanvils.world.biomemods.ModBiomeModifiers;
import com.redrock49.coolanvils.world.feature.ModPlacedFeatures;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.redrock49.coolanvils.CoolAnvils.MODID;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static void registerModBlocks() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBiomeModifiers.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModPlacedFeatures.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    //Items
    public static final RegistryObject<Item> MYTHRIL_INGOT = ITEMS.register("mythril_ingot", () -> new Item(new Item.Properties().tab(CoolAnvils.TAB_COOLANVILS)));
    public static final RegistryObject<Item> MYTHRIL_RAW = ITEMS.register("mythril_raw",() -> new Item(new Item.Properties().tab(CoolAnvils.TAB_COOLANVILS)));

    //Blocks
    public static final RegistryObject<Block> MYTHRIL_BLOCK = BLOCKS.register("mythril_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MYTHRIL_ORE = BLOCKS.register("mythril_ore_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MYTHRIL_ORE_DEEPSLATE = BLOCKS.register("mythril_ore_deepslate", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MYTHRIL_ORE_RAW_BLOCK = BLOCKS.register("mythril_ore_raw_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()));


    //Block_items
    public static final RegistryObject<Item> MYTHRIL_BLOCK_ITEM = ITEMS.register("mythril_block",() -> new BlockItem(MYTHRIL_BLOCK.get(), new Item.Properties().tab(CoolAnvils.TAB_COOLANVILS)));
    public static final RegistryObject<Item> MYTHRIL_ORE_ITEM = ITEMS.register("mythril_ore_block", () -> new BlockItem(MYTHRIL_ORE.get(), new Item.Properties().tab(CoolAnvils.TAB_COOLANVILS)));
    public static final RegistryObject<Item> MYTHRIL_ORE_DEEPSLATE_ITEM = ITEMS.register("mythril_ore_deepslate", () -> new BlockItem(MYTHRIL_ORE_DEEPSLATE.get(), new Item.Properties().tab(CoolAnvils.TAB_COOLANVILS)));
    public static final RegistryObject<Item> MYTHRIL_ORE_RAW_BLOCK_ITEM = ITEMS.register("mythril_ore_raw_block",() -> new BlockItem(MYTHRIL_ORE_RAW_BLOCK.get(), new Item.Properties().tab(CoolAnvils.TAB_COOLANVILS)));

    //Entity Blocks
    public static final RegistryObject<Block> MYTHRIL_ANVIL = BLOCKS.register("mythril_anvil", () -> new MythrilAnvilBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Item> MYTHRIL_ANVIL_ITEM = ITEMS.register("mythril_anvil", () -> new BlockItem(MYTHRIL_ANVIL.get(),new Item.Properties().tab(CoolAnvils.TAB_COOLANVILS)));
}
