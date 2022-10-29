package com.redrock49.coolanvils.block.entity;

import com.redrock49.coolanvils.CoolAnvils;
import com.redrock49.coolanvils.ModBlocks;
import com.redrock49.coolanvils.block.entity.custom.MythrilAnvilBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CoolAnvils.MODID);
    public static void RegisterBlockEntities(){
        BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static final RegistryObject<BlockEntityType<MythrilAnvilBlockEntity>> MYTHRIL_ANVIL_BLOCK_ENTITY = BLOCK_ENTITIES.register("mythril_anvil", () -> BlockEntityType.Builder.of(MythrilAnvilBlockEntity::new, ModBlocks.MYTHRIL_ANVIL.get()).build(null));
}
