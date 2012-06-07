/*******************************************************************************
 * Copyright (c) 2012 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     cpw - initial API and implementation
 ******************************************************************************/
package cpw.mods.compactsolars;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemCompactSolar extends ItemBlock {
	public ItemCompactSolar(int id) {
		super(id);
        setMaxDamage(0);
        setHasSubtypes(true);
	}
	
	public int getMetadata(int i) {
		if (i<CompactSolarType.values().length) {
			return i;
		} else {
			return 0;
		}
	}
	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return CompactSolarType.values()[itemstack.getItemDamage()].name();
	}

}
