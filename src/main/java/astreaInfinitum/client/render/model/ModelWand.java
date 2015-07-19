package astreaInfinitum.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Wand - Jared Created using Tabula 4.1.1
 */
public class ModelWand extends ModelBase {
	public ModelRenderer Handle;
	public ModelRenderer base;
	public ModelRenderer end;

	public ModelWand() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.base = new ModelRenderer(this, 4, 60);
		this.base.setRotationPoint(-1.05F, -6.34F, 4.6F);
		this.base.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(base, -1.1383037381507017F, -0.20943951023931953F, 0.0F);
		this.Handle = new ModelRenderer(this, 0, 59);
		this.Handle.setRotationPoint(0.0F, -2.8F, 0.0F);
		this.Handle.addBox(0.0F, -3.0F, 0.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(Handle, -0.7853981633974483F, -0.2617993877991494F, 0.0F);
		this.end = new ModelRenderer(this, 8, 56);
		this.end.setRotationPoint(-1.05F, -5.44F, 4.6F);
		this.end.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
		this.setRotateAngle(end, 1.5707963267948966F, -0.31869712141416456F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.base.render(f5);
		this.Handle.render(f5);
		this.end.render(f5);
	}

	public void render(float f5) {
		render(null, 0, 0, 0, 0, 0, f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
