package astreaInfinitum.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * EcoVent - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class EcoVent extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape14;

    public EcoVent() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.shape1 = new ModelRenderer(this, 0, 32);
        this.shape1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shape1.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 32);
        this.shape3.setRotationPoint(0.0F, -16.0F, 0.0F);
        this.shape3.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
        this.shape14 = new ModelRenderer(this, 0, 64);
        this.shape14.setRotationPoint(0.0F, -32.0F, 0.0F);
        this.shape14.addBox(-24.0F, -8.0F, -24.0F, 48, 16, 48, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 32);
        this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	this.shape1.render(f5);
        this.shape3.render(f5);
        this.shape14.render(f5);
        this.shape2.render(f5);
    }
    
    public void render(float f5){
    	this.shape1.render(f5);
        this.shape3.render(f5);
        this.shape14.render(f5);
        this.shape2.render(f5);
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
