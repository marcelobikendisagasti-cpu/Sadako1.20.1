package com.moonlight.sadako.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moonlight.sadako.SadakoEntity;
import com.moonlight.sadako.SadakoMod;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SadakoRenderer extends MobRenderer<SadakoEntity, PlayerModel<SadakoEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(SadakoMod.ID, "textures/entity/sadako.png");
    public SadakoRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new PlayerModel<>(ctx.bakeLayer(ModelLayers.PLAYER), false), 0.45f);
    }
    @Override public ResourceLocation getTextureLocation(SadakoEntity entity) { return TEXTURE; }
    @Override protected void scale(SadakoEntity entity, PoseStack pose, float partialTick) {
        super.scale(entity, pose, partialTick);
    }
}
