package tconstruct.library.client;

import net.minecraft.item.ItemStack;

public class PatternGuiElement
{
    //Position
    public final int buttonIconX;
    public final int buttonIconY;
    //Name
    public final String title;
    //Tooltip
    public final String body;

    public final ItemStack ourItem;

    public PatternGuiElement (int bx, int by, String t, String b, ItemStack item)
    {
        buttonIconX = bx;
        buttonIconY = by;
        title = t;
        body = b;
        ourItem = item;
    }
}
