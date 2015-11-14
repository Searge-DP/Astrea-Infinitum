package astreaInfinitum.client.render.model;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelAIPylonModel - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelAIPylonModel extends ModelBase {
    public ModelRenderer ArchLTop;
    public ModelRenderer ArchFBase;
    public ModelRenderer TopP2;
    public ModelRenderer TopP1;
    public ModelRenderer ArchRBase;
    public ModelRenderer ArchLBase;
    public ModelRenderer ArchBTop;
    public ModelRenderer ArchBBase;
    public ModelRenderer SupportFL;
    public ModelRenderer SupportFR;
    public ModelRenderer ArchFTop;
    public ModelRenderer SupportBL;
    public ModelRenderer ArchRTop;
    public ModelRenderer SupportBR;
    public ModelRenderer BaseP1;
    public ModelRenderer BaseP2;
    public ModelRenderer Core;

    public ModelAIPylonModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.ArchRBase = new ModelRenderer(this, 24, 26);
        this.ArchRBase.setRotationPoint(-4.0F, 3.9F, 0.0F);
        this.ArchRBase.addBox(-1.0F, -2.5F, -1.5F, 2, 5, 3, 0.0F);
        this.TopP2 = new ModelRenderer(this, 0, 0);
        this.TopP2.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.TopP2.addBox(-6.0F, -1.0F, -6.0F, 12, 2, 12, 0.0F);
        this.ArchBBase = new ModelRenderer(this, 24, 26);
        this.ArchBBase.setRotationPoint(0.0F, 3.9000000953674316F, 3.9000000953674316F);
        this.ArchBBase.addBox(-1.0F, -2.5F, -1.5F, 2, 5, 3, 0.0F);
        this.setRotateAngle(ArchBBase, 0.0F, 1.5707963705062866F, 0.0F);
        this.SupportFL = new ModelRenderer(this, 48, 0);
        this.SupportFL.setRotationPoint(-2.5F, 15.0F, -4.5F);
        this.SupportFL.addBox(-0.5F, -5.0F, -0.5F, 1, 10, 1, 0.0F);
        this.SupportBL = new ModelRenderer(this, 48, 0);
        this.SupportBL.setRotationPoint(2.5F, 15.0F, 4.5F);
        this.SupportBL.addBox(-0.5F, -5.0F, -0.5F, 1, 10, 1, 0.0F);
        this.ArchFBase = new ModelRenderer(this, 24, 26);
        this.ArchFBase.setRotationPoint(-3.0F, 1.399999976158142F, -4.0F);
        this.ArchFBase.addBox(-1.0F, 0.0F, 1.5F, 2, 5, 3, 0.0F);
        this.setRotateAngle(ArchFBase, 0.0F, 1.5707963705062866F, 0.0F);
        this.BaseP1 = new ModelRenderer(this, 0, 0);
        this.BaseP1.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.BaseP1.addBox(-6.0F, -1.0F, -6.0F, 12, 2, 12, 0.0F);
        this.BaseP2 = new ModelRenderer(this, 0, 14);
        this.BaseP2.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.BaseP2.addBox(-5.0F, -1.0F, -5.0F, 10, 2, 10, 0.0F);
        this.Core = new ModelRenderer(this, 0, 26);
        this.Core.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.Core.addBox(-3.0F, -5.0F, -3.0F, 6, 10, 6, 0.0F);
        this.setRotateAngle(Core, 0.0F, 0.7853981852531433F, 0.0F);
        this.SupportFR = new ModelRenderer(this, 48, 0);
        this.SupportFR.setRotationPoint(2.5F, 15.0F, -4.5F);
        this.SupportFR.addBox(-0.5F, -5.0F, -0.5F, 1, 10, 1, 0.0F);
        this.ArchBTop = new ModelRenderer(this, 34, 26);
        this.ArchBTop.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.ArchBTop.addBox(-0.5F, -2.0F, -1.0F, 1, 4, 2, 0.0F);
        this.setRotateAngle(ArchBTop, 0.0F, 1.5707963267948966F, 0.0F);
        this.SupportBR = new ModelRenderer(this, 48, 0);
        this.SupportBR.setRotationPoint(-2.5F, 15.0F, 4.5F);
        this.SupportBR.addBox(-0.5F, -5.0F, -0.5F, 1, 10, 1, 0.0F);
        this.ArchLBase = new ModelRenderer(this, 24, 26);
        this.ArchLBase.setRotationPoint(4.0F, 1.4F, 0.0F);
        this.ArchLBase.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
        this.TopP1 = new ModelRenderer(this, 0, 14);
        this.TopP1.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.TopP1.addBox(-5.0F, -1.0F, -5.0F, 10, 2, 10, 0.0F);
        this.ArchLTop = new ModelRenderer(this, 34, 26);
        this.ArchLTop.setRotationPoint(4.0F, 0.0F, 0.0F);
        this.ArchLTop.addBox(-0.5F, -2.0F, -1.0F, 1, 4, 2, 0.0F);
        this.ArchRTop = new ModelRenderer(this, 34, 26);
        this.ArchRTop.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.ArchRTop.addBox(-0.5F, -2.0F, -1.0F, 1, 4, 2, 0.0F);
        this.ArchFTop = new ModelRenderer(this, 34, 26);
        this.ArchFTop.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.ArchFTop.addBox(-0.5F, -2.0F, -1.0F, 1, 4, 2, 0.0F);
        this.setRotateAngle(ArchFTop, 0.0F, 4.71238898038469F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.ArchRBase.render(f5);
        this.TopP2.render(f5);
        this.ArchBBase.render(f5);
        this.SupportFL.render(f5);
        this.SupportBL.render(f5);
        this.ArchFBase.render(f5);
        this.BaseP1.render(f5);
        this.BaseP2.render(f5);
        this.Core.render(f5);
        this.SupportFR.render(f5);
        this.ArchBTop.render(f5);
        this.SupportBR.render(f5);
        this.ArchLBase.render(f5);
        this.TopP1.render(f5);
        this.ArchLTop.render(f5);
        this.ArchRTop.render(f5);
        this.ArchFTop.render(f5);
    }
    
    public void render(float f5) { 
        this.ArchRBase.render(f5);
        this.TopP2.render(f5);
        this.ArchBBase.render(f5);
        this.SupportFL.render(f5);
        this.SupportBL.render(f5);
        this.ArchFBase.render(f5);
        this.BaseP1.render(f5);
        this.BaseP2.render(f5);
        this.Core.render(f5);
        this.SupportFR.render(f5);
        this.ArchBTop.render(f5);
        this.SupportBR.render(f5);
        this.ArchLBase.render(f5);
        this.TopP1.render(f5);
        this.ArchLTop.render(f5);
        this.ArchRTop.render(f5);
        this.ArchFTop.render(f5);
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
