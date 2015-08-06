package astreaInfinitum.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * AIPedestal - Jared
 * Created using Tabula 4.1.1
 */
public class ModelPedestal extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape8;
    public ModelRenderer shape9;
   

    public ModelPedestal() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.shape2 = new ModelRenderer(this, 64, 50);
        this.shape2.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.shape2.addBox(-6.0F, 0.0F, -6.0F, 12, 2, 12, 0.0F);
        this.shape7 = new ModelRenderer(this, 48, 52);
        this.shape7.setRotationPoint(-0.5F, 15.0F, 0.4F);
        this.shape7.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(shape7, -2.2689280275926285F, 0.6981317007977318F, 0.0F);
        this.shape4 = new ModelRenderer(this, 32, 38);
        this.shape4.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.shape4.addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 46);
        this.shape1.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.shape1.addBox(-8.0F, 0.0F, -8.0F, 16, 2, 16, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 36);
        this.shape3.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.shape3.addBox(-4.0F, 0.0F, -4.0F, 8, 2, 8, 0.0F);
        this.shape5 = new ModelRenderer(this, 48, 32);
        this.shape5.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.shape5.addBox(-8.0F, 0.0F, -8.0F, 16, 2, 16, 0.0F);
        this.shape6 = new ModelRenderer(this, 60, 52);
        this.shape6.setRotationPoint(0.8F, 15.0F, 1.4F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(shape6, 2.2689280275926285F, 0.6981317007977318F, 0.0F);
        this.shape8 = new ModelRenderer(this, 52, 52);
        this.shape8.setRotationPoint(-1.4F, 15.0F, 0.8F);
        this.shape8.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(shape8, 2.2689280275926285F, -0.6981317007977318F, 0.0F);
        this.shape9 = new ModelRenderer(this, 56, 52);
        this.shape9.setRotationPoint(0.0F, 15.0F, -0.5F);
        this.shape9.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(shape9, -2.2689280275926285F, -0.6981317007977318F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape2.render(f5);
        this.shape7.render(f5);
        this.shape4.render(f5);
        this.shape1.render(f5);
        this.shape3.render(f5);
        this.shape5.render(f5);
        this.shape6.render(f5);
        this.shape8.render(f5);
        this.shape9.render(f5);
    }
    
    public void render(float f5){
    	
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
