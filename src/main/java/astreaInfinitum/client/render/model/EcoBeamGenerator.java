package astreaInfinitum.client.render.model;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * EcoBeamGenerator - Jared
 * Created using Tabula 4.1.1
 */
public class EcoBeamGenerator extends ModelBase {
    public ModelRenderer Base;
    public ModelRenderer UpperBase;
    public ModelRenderer UpperUpperBase;
    public ModelRenderer UpperUpperUpperBase;
    public ModelRenderer Holder0;
    public ModelRenderer Holder1;
    public ModelRenderer Holder2;
    public ModelRenderer Holder3;
    public ModelRenderer Crystal;
    public ModelRenderer Crystal2;

    public EcoBeamGenerator() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Holder0 = new ModelRenderer(this, 49, 59);
        this.Holder0.setRotationPoint(2.0F, 11.0F, 2.0F);
        this.Holder0.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.Crystal = new ModelRenderer(this, 66, 58);
        this.Crystal.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.Crystal.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(Crystal, 0.7853981633974483F, 0.0F, 0.0F);
        this.Holder2 = new ModelRenderer(this, 57, 59);
        this.Holder2.setRotationPoint(-3.0F, 11.0F, -3.0F);
        this.Holder2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.Holder1 = new ModelRenderer(this, 53, 59);
        this.Holder1.setRotationPoint(2.0F, 11.0F, -3.0F);
        this.Holder1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.Holder3 = new ModelRenderer(this, 61, 59);
        this.Holder3.setRotationPoint(-3.0F, 11.0F, 2.0F);
        this.Holder3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.Crystal2 = new ModelRenderer(this, 78, 58);
        this.Crystal2.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.Crystal2.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(Crystal2, 0.0F, 0.0F, 0.7853981633974483F);
        this.Base = new ModelRenderer(this, 0, 47);
        this.Base.setRotationPoint(0.0F, 22.5F, 0.0F);
        this.Base.addBox(-7.0F, -1.5F, -7.0F, 14, 3, 14, 0.0F);
        this.UpperBase = new ModelRenderer(this, 0, 32);
        this.UpperBase.setRotationPoint(0.0F, 19.5F, 0.0F);
        this.UpperBase.addBox(-6.0F, -1.5F, -6.0F, 12, 3, 12, 0.0F);
        this.UpperUpperBase = new ModelRenderer(this, 0, 19);
        this.UpperUpperBase.setRotationPoint(0.0F, 16.5F, 0.0F);
        this.UpperUpperBase.addBox(-5.0F, -1.5F, -5.0F, 10, 3, 10, 0.0F);
        this.UpperUpperUpperBase = new ModelRenderer(this, 0, 8);
        this.UpperUpperUpperBase.setRotationPoint(0.0F, 13.5F, 0.0F);
        this.UpperUpperUpperBase.addBox(-4.0F, -1.5F, -4.0F, 8, 3, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Holder0.render(f5);
        this.Crystal.render(f5);
        this.Holder2.render(f5);
        this.Holder1.render(f5);
        this.Holder3.render(f5);
        this.Crystal2.render(f5);
        this.Base.render(f5);
        this.UpperBase.render(f5);
        this.UpperUpperBase.render(f5);
        this.UpperUpperUpperBase.render(f5);
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
