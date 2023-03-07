package charcoalPit.item.tool;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class ContainerItem extends Item {

    public ContainerItem(Properties properties) {
        super(properties);
    }

    public abstract int getBoxSize();

    @Nullable

    @Override
    public @org.jetbrains.annotations.Nullable ICapabilityProvider initCapabilities(ItemStack stack, @org.jetbrains.annotations.Nullable CompoundTag nbt) {
        if(!stack.isEmpty()) {
            //return new ForgeCapabilities.ITEM_HANDLER;

        }
        return null;
    }

}
