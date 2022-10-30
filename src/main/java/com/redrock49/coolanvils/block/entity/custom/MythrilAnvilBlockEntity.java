package com.redrock49.coolanvils.block.entity.custom;

import com.redrock49.coolanvils.block.entity.ModBlockEntities;
import com.redrock49.coolanvils.screen.MythrilAnvilMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MythrilAnvilBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public MythrilAnvilBlockEntity( BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.MYTHRIL_ANVIL_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Mythril Anvil");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return new MythrilAnvilMenu(p_39954_, p_39955_, this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    private static void enchantItem(MythrilAnvilBlockEntity entity) {
        entity.itemHandler.setStackInSlot(2, entity.itemHandler.getStackInSlot(0).copy());
        Map<Enchantment, Integer> map1 = EnchantmentHelper.getEnchantments(entity.itemHandler.getStackInSlot(0).copy());
        Map<Enchantment, Integer> map2 = EnchantmentHelper.getEnchantments(entity.itemHandler.getStackInSlot(1).copy());

        Map<Enchantment, Integer> map3 = Stream.of(map1, map2).flatMap(map -> map.entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); //map merging using stream

        EnchantmentHelper.setEnchantments(map3, entity.itemHandler.getStackInSlot(2));

        entity.level.playLocalSound(entity.worldPosition.getX(), entity.worldPosition.getY(), entity.worldPosition.getZ(), SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0F, 1.0F,false);
        entity.itemHandler.setStackInSlot(0,new ItemStack(Items.AIR));
        entity.itemHandler.setStackInSlot(1,new ItemStack(Items.AIR));
    }

    private static boolean hasEnchantRecipe(MythrilAnvilBlockEntity entity) {
        boolean firstSlotSuitable = entity.itemHandler.getStackInSlot(0).isRepairable();
        boolean secondSlotSuitable = entity.itemHandler.getStackInSlot(1).getItem() == Items.ENCHANTED_BOOK;

        return firstSlotSuitable && secondSlotSuitable;
    }

    private static boolean hasRepairRecipe(MythrilAnvilBlockEntity entity) {
        ItemStack firstSlotStack = entity.itemHandler.getStackInSlot(0);
        ItemStack secondSlotStack = entity.itemHandler.getStackInSlot(1);
        boolean firstSlotItemRepairable = firstSlotStack.isRepairable();
        boolean secondSlotHasRepairMaterial = firstSlotStack.getItem().isValidRepairItem(firstSlotStack,secondSlotStack);
        boolean isDamaged = firstSlotStack.isDamaged();

        return firstSlotItemRepairable && secondSlotHasRepairMaterial && isDamaged;
    }
    private static void repairItem(MythrilAnvilBlockEntity entity){
        entity.itemHandler.setStackInSlot(2, entity.itemHandler.getStackInSlot(0).copy());

        var damagedOn = entity.itemHandler.getStackInSlot(2).getItem().getDamage(entity.itemHandler.getStackInSlot(0));
        entity.itemHandler.getStackInSlot(2).hurt(-damagedOn, RandomSource.create(),null );


        entity.level.playLocalSound(entity.worldPosition.getX(), entity.worldPosition.getY(), entity.worldPosition.getZ(), SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0F, 1.0F,false);
        entity.itemHandler.setStackInSlot(0, new ItemStack(Items.AIR));
        entity.itemHandler.extractItem(1,2,false);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, MythrilAnvilBlockEntity pBlockEntity) {
        if(hasEnchantRecipe(pBlockEntity)) {
            enchantItem(pBlockEntity);
        } else if (hasRepairRecipe(pBlockEntity)) {
            repairItem(pBlockEntity);
        }else
            return;
    }
}
