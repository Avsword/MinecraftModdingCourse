// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
package tamk.aarov;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;


public class Capybara_model extends EntityModel<CapybaraEntity> {
	private final ModelPart body;
	private final ModelPart body_r1;
	private final ModelPart head;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	public Capybara_model(ModelPart root) {
		this.body = root.getChild("body");
		this.body_r1 = this.body.getChild("body_r1");
		this.head = this.body.getChild("head");
		this.leg0 = this.body.getChild("leg0");
		this.leg1 = this.body.getChild("leg1");
		this.leg2 = this.body.getChild("leg2");
		this.leg3 = this.body.getChild("leg3");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 10.0F, 0.0F));
		ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(3, 28).cuboid(-8.5F, -10.0F, -4.0F, 14.0F, 22.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 3.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(-3, 3).cuboid(-3.5F, -7.0F, -3.0F, 8.0F, 7.0F, 12.0F, new Dilation(0.0F))
		.uv(52, 25).cuboid(-3.5F, -9.0F, 6.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(52, 25).cuboid(2.5F, -9.0F, 6.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.5F, -17.0F));
		ModelPartData leg0 = body.addChild("leg0", ModelPartBuilder.create().uv(42, 2).cuboid(-1.0F, 2.0F, -1.0F, 3.0F, 7.0F, 4.0F, new Dilation(0.0F))
		.uv(44, 4).cuboid(-1.0F, 8.0F, -3.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.5F, 5.0F, 9.0F));
		ModelPartData leg1 = body.addChild("leg1", ModelPartBuilder.create().uv(42, 2).cuboid(-1.0F, 2.0F, -1.0F, 3.0F, 7.0F, 4.0F, new Dilation(0.0F))
		.uv(44, 4).cuboid(-1.0F, 8.0F, -3.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(5.5F, 5.0F, 9.0F));
		ModelPartData leg2 = body.addChild("leg2", ModelPartBuilder.create().uv(42, 2).cuboid(-1.0F, 2.0F, -1.0F, 3.0F, 7.0F, 4.0F, new Dilation(0.0F))
		.uv(44, 4).cuboid(-1.0F, 8.0F, -3.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.5F, 5.0F, -9.0F));
		ModelPartData leg3 = body.addChild("leg3", ModelPartBuilder.create().uv(42, 2).cuboid(-1.0F, 2.0F, -1.0F, 3.0F, 7.0F, 4.0F, new Dilation(0.0F))
		.uv(44, 4).cuboid(-1.0F, 8.0F, -3.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(5.5F, 5.0F, -9.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
	@Override
	public void setAngles(CapybaraEntity entity, float limbAngle, float limbDistance, float animationProgress,
			float headYaw, float headPitch) {
		this.leg0.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		this.leg1.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
		this.leg2.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
		this.leg3.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
	}
}