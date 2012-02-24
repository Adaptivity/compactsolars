package cpw.mods.compactsolars;

import java.util.List;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICrafting;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class ContainerCompactSolar extends Container {
	public TileEntityCompactSolar tile;
	private boolean theSunIsVisible;
	private boolean initialized;

	public ContainerCompactSolar(IInventory playerInventory, TileEntityCompactSolar inventory, CompactSolarType type) {
        this.tile = inventory;
        layoutContainer(playerInventory, inventory, type);
    }

	
	private void layoutContainer(IInventory playerInventory, IInventory inventory, CompactSolarType type) {
        addSlot(new Slot(inventory, 0, 80, 26));
        for (int inventoryRow = 0; inventoryRow < 3; inventoryRow++)
        {
            for (int inventoryColumn = 0; inventoryColumn < 9; inventoryColumn++)
            {
                addSlot(new Slot(playerInventory, inventoryColumn + inventoryRow * 9 + 9, 8 + inventoryColumn * 18, 84 + inventoryRow * 18));
            }
        }

        for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++)
        {
            addSlot(new Slot(playerInventory, hotbarSlot, 8 + hotbarSlot * 18, 142));
        }
		
	}

	@Override
	public void updateCraftingResults() {
		super.updateCraftingResults();
		@SuppressWarnings("unchecked")
		List<ICrafting> crafters=this.crafters;
		for (ICrafting crafter : crafters) {
			if (theSunIsVisible!=tile.theSunIsVisible || !initialized) {
				crafter.updateCraftingInventoryInfo(this, 0, tile.theSunIsVisible ? 1 : 0);
			}
		}
		initialized=true;
		theSunIsVisible=tile.theSunIsVisible;
	}

	public void updateProgressBar(int i, int j) {
		if (i==0) {
			tile.theSunIsVisible=(j==1);
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tile.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i == 0)
            {
                if (!mergeItemStack(itemstack1, 1, 37, true))
                {
                    return null;
                }
            }
            else if (i >= 1 && i < 28)
            {
                if (!mergeItemStack(itemstack1, 28, 37, false))
                {
                    return null;
                }
            }
            else if (i >= 28 && i < 37)
            {
                if (!mergeItemStack(itemstack1, 1, 27, false))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, 1, 37, false))
            {
                return null;
            }
            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(itemstack1);
            }
            else
            {
                return null;
            }
        }
        return itemstack;
    }
}
