// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src.item;

// Referenced classes of package net.minecraft.src:
//            ItemBlock, Block

import net.minecraft.src.block.Block;

public class ItemSapling extends ItemBlock {

  public ItemSapling(int i) {
    super(i);
    setMaxDamage(0);
    setHasSubtypes(true);
  }

  public int getPlacedBlockMetadata(int i) {
    return i;
  }

  public int getIconFromDamage(int i) {
    return Block.sapling.getBlockTextureFromSideAndMetadata(0, i);
  }
}
