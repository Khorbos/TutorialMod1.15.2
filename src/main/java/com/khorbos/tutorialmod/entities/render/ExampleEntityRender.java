package com.khorbos.tutorialmod.entities.render;

import com.khorbos.tutorialmod.TutorialMod;
import com.khorbos.tutorialmod.entities.ExampleEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeGelLayer;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class ExampleEntityRender extends MobRenderer<ExampleEntity, SlimeModel<ExampleEntity>> {
    protected static final ResourceLocation EXAMPLE_TEXTURES = new ResourceLocation(TutorialMod.ID, "textures/entity/slime/example_entity.png");

    public ExampleEntityRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SlimeModel<>(16), 0.25f);
        this.addLayer(new SlimeGelLayer(this));
    }

    public void render(ExampleEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        this.shadowSize = 0.25F * (float)entityIn.getSlimeSize();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    protected void preRenderCallback(ExampleEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 0.999F;
        matrixStackIn.scale(0.999F, 0.999F, 0.999F);
        matrixStackIn.translate(0.0D, (double)0.001F, 0.0D);
        float f1 = (float)entitylivingbaseIn.getSlimeSize();
        float f2 = MathHelper.lerp(partialTickTime, entitylivingbaseIn.prevSquishFactor, entitylivingbaseIn.squishFactor) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    public ResourceLocation getEntityTexture(ExampleEntity entity) {
        return EXAMPLE_TEXTURES;
    }
}
