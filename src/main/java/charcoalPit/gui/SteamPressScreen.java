package charcoalPit.gui;

import charcoalPit.CharcoalPit;
import charcoalPit.core.MethodHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

public class SteamPressScreen extends AbstractContainerScreen<SteamPressContainer> {
	
	private static final ResourceLocation STEAM_PRESS_GUI_TEXTURES = new ResourceLocation(CharcoalPit.MODID, "textures/gui/container/steam_press.png");
	
	public SteamPressScreen(SteamPressContainer container, Inventory inv, Component title){
		super(container,inv,title);
	}
	
	@Override
	protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0,STEAM_PRESS_GUI_TEXTURES);
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(pPoseStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
		MethodHelper.renderFluid(this,this.minecraft,pPoseStack,FluidStack.loadFluidStackFromNBT(this.menu.fluid_tag.getStackInSlot(0).getOrCreateTag().getCompound("fluid1")),
				1000,109,32,MethodHelper.TANK_WIDE,i,j);
		MethodHelper.renderFluid(this,this.minecraft,pPoseStack,FluidStack.loadFluidStackFromNBT(this.menu.fluid_tag.getStackInSlot(0).getOrCreateTag().getCompound("fluid2")),
				8000,57,68,MethodHelper.TANK_WIDE,i,j);
		MethodHelper.renderFluid(this,this.minecraft,pPoseStack,FluidStack.loadFluidStackFromNBT(this.menu.fluid_tag.getStackInSlot(0).getOrCreateTag().getCompound("fluid2")),
				8000,57+16,68,MethodHelper.TANK_WIDE,i,j);
	}
	
	@Override
	protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
		super.renderLabels(pPoseStack, pMouseX, pMouseY);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0,STEAM_PRESS_GUI_TEXTURES);
		this.blit(pPoseStack,57,53,176,60,32,16);
		this.blit(pPoseStack,109,17,176,60,16,16);
		int time=menu.array.get(2);
		int total=menu.array.get(3);
		if(total>0&&time>=0) {
			int height=(int)((total-time)*13F/total);
			this.blit(pPoseStack, 64, 19, 200, 3, 18, 13-height);
		}
		int burnTotal=this.menu.array.get(1);
		if(burnTotal==0)
			burnTotal=200;
		int burnTime=this.menu.array.get(0);
		if(burnTime>0) {
			int k = burnTime * 13 / burnTotal;
			this.blit(pPoseStack, 91, 36 + 12 - k, 176, 12 - k, 14, k + 1);
		}
	}
	
	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderBackground(pPoseStack);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
		this.renderTooltip(pPoseStack, pMouseX, pMouseY);
		IClientFluidTypeExtensions client_extensions = IClientFluidTypeExtensions.of(Fluids.WATER);
		MethodHelper.drawFluidTooltip(this,FluidStack.loadFluidStackFromNBT(this.menu.fluid_tag.getStackInSlot(0).getOrCreateTag().getCompound("fluid1")),
				1000,pMouseX,pMouseY,108,16,125,33,pPoseStack, Component.translatable(Fluids.WATER.getFluidType().getDescriptionId()));
		MethodHelper.drawFluidTooltip(this,FluidStack.loadFluidStackFromNBT(this.menu.fluid_tag.getStackInSlot(0).getOrCreateTag().getCompound("fluid2")),
				8000,pMouseX,pMouseY,56,52,89,69,pPoseStack,Component.translatable("tooltip.charcoal_pit.fluid_empty"));
	}
	
}
