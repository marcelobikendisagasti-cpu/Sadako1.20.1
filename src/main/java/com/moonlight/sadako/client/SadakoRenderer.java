package com.moonlight.sadako.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonlight.sadako.SadakoEntity;
import com.moonlight.sadako.SadakoMod;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.math.Axis;

public class SadakoRenderer extends MobRenderer<SadakoEntity, SadakoAnimatedModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(SadakoMod.ID, "textures/entity/sadako.png");

    public SadakoRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new SadakoAnimatedModel(ctx.bakeLayer(ModelLayers.PLAYER)), 0.45F);
    }

    @Override
    public ResourceLocation getTextureLocation(SadakoEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(SadakoEntity entity, PoseStack pose, float ageInTicks,
                                  float rotationYaw, float partialTicks) {
        if (entity.deathTime > 0) {
            // Evita la rotación de muerte genérica y crea una caída hacia delante.
            pose.mulPose(Axis.YP.rotationDegrees(180.0F - rotationYaw));
            float fall = Mth.clamp((entity.deathTime + partialTicks) / 20.0F, 0.0F, 1.0F);
            fall = fall * fall * (3.0F - 2.0F * fall);
            pose.translate(0.0F, -0.55F * fall, 0.0F);
            pose.mulPose(Axis.XP.rotationDegrees(80.0F * fall));
        } else {
            super.setupRotations(entity, pose, ageInTicks, rotationYaw, partialTicks);
        }
    }
}
