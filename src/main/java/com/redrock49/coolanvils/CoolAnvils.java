package com.redrock49.coolanvils;

import com.mojang.logging.LogUtils;
import com.redrock49.coolanvils.block.entity.ModBlockEntities;
import com.redrock49.coolanvils.screen.ModMenuTypes;
import com.redrock49.coolanvils.screen.MythrilAnvilScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CoolAnvils.MODID)
public class CoolAnvils
{
    public static final String MODID = "coolanvils";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final CreativeModeTab TAB_COOLANVILS = new CreativeModeTab("coolAnvils") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModBlocks.MYTHRIL_BLOCK_ITEM.get());
        }
    };

    public CoolAnvils()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        ModBlocks.registerModBlocks();
        ModBlockEntities.RegisterBlockEntities();
        ModMenuTypes.registerMenuTypes();
    }

    private void clientSetup(final FMLClientSetupEvent event){
        MenuScreens.register(ModMenuTypes.MYTHRIL_ANVIL_MENU.get(), MythrilAnvilScreen::new);
    }
    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("Your server hosting sucks ;) ");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
            LOGGER.info("Nice pc you've got over there! :)");
        }
    }
}
