package tconstruct.smeltery.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GlueFluid extends BlockFluidFinite
{

    IIcon stillIcon;
    IIcon flowIcon;

    boolean overwriteFluidIcons = true;

    public GlueFluid(Fluid fluid, Material material)
    {
        super(fluid, material);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister)
    {
        stillIcon = iconRegister.registerIcon("tinker:liquid_glue");
        flowIcon = iconRegister.registerIcon("tinker:liquid_glue_flow");

        if (this.overwriteFluidIcons)
            this.getFluid().setIcons(stillIcon, flowIcon);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta)
    {
        if (side == 0 || side == 1)
            return stillIcon;
        return flowIcon;
    }

    @Override
    public void onEntityCollidedWithBlock (World world, int x, int y, int z, Entity entity)
    {
        entity.motionX *= 0.1;
        entity.motionZ *= 0.1;

        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase living = (EntityLivingBase) entity;
            // Well you'd feel ill too standing in glue...
            living.addPotionEffect(new PotionEffect(Potion.hunger.getId(), 20, 4));

            // Glue is sticky stuff
            living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 30, 4));
        }
    }

    public void suppressOverwritingFluidIcons ()
    {
        this.overwriteFluidIcons = false;
    }
}
