package com.redrock49.coolanvils.screen;

import com.redrock49.coolanvils.CoolAnvils;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CoolAnvils.MODID);
    public static final RegistryObject<MenuType<MythrilAnvilMenu>> MYTHRIL_ANVIL_MENU = registerMenuType(MythrilAnvilMenu::new, "mythril_anvil_menu");
    public static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,String name ){
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
    public static void registerMenuTypes(){
        MENUS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
