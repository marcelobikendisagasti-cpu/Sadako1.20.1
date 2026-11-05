package com.moonlight.sadako.client;

import com.moonlight.sadako.SadakoEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

/** Animaciones nativas sobre el mismo modelo humanoide de la primera versión. */
public class SadakoAnimatedModel extends PlayerModel<SadakoEntity> {
    public SadakoAnimatedModel(ModelPart root) {
        super(root, false);
    }

    @Override
    public void setupAnim(SadakoEntity entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float t = entity.tickCount + ageInTicks - (float) entity.tickCount;
        float walk = Mth.clamp(limbSwingAmount, 0.0F, 1.0F);
        float sway = Mth.sin(t * 0.11F);

        // Postura encorvada, rostro hacia abajo y oscilaciones inquietantes.
        this.body.xRot = 0.13F + walk * 0.15F + sway * 0.015F;
        this.head.xRot = 0.29F + Mth.sin(t * 0.055F) * 0.055F;
        this.head.zRot = Mth.sin(t * 0.038F) * 0.065F;
        this.head.yRot = Mth.sin(t * 0.027F) * 0.06F;
        this.leftArm.xRot = -0.15F + Mth.cos(limbSwing * 0.48F) * walk * 0.28F;
        this.rightArm.xRot = -0.15F - Mth.cos(limbSwing * 0.48F) * walk * 0.28F;
        this.leftArm.zRot = -0.06F + sway * 0.025F;
        this.rightArm.zRot = 0.06F - sway * 0.025F;
        this.leftLeg.xRot *= 0.58F;
        this.rightLeg.xRot *= 0.58F;

        // Espasmo repentino durante el ataque.
        if (this.attackTime > 0.0F) {
            float strike = Mth.sin(this.attackTime * Mth.PI);
            this.rightArm.xRot = -1.7F * strike;
            this.leftArm.xRot = -1.1F * strike;
            this.head.xRot -= 0.42F * strike;
        }

        // El renderer inclina el cuerpo al morir; las extremidades se aflojan.
        if (entity.deathTime > 0) {
            float fall = Mth.clamp((entity.deathTime + (ageInTicks - entity.tickCount)) / 20.0F, 0.0F, 1.0F);
            this.head.xRot = 0.35F + fall * 0.45F;
            this.leftArm.xRot = -0.2F + fall * 0.65F;
            this.rightArm.xRot = -0.2F + fall * 0.65F;
            this.leftLeg.xRot = fall * 0.25F;
            this.rightLeg.xRot = -fall * 0.25F;
        }
        this.hat.copyFrom(this.head);
        this.jacket.copyFrom(this.body);
        this.leftSleeve.copyFrom(this.leftArm);
        this.rightSleeve.copyFrom(this.rightArm);
        this.leftPants.copyFrom(this.leftLeg);
        this.rightPants.copyFrom(this.rightLeg);
    }
}
