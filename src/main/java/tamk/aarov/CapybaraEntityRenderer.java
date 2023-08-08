package tamk.aarov;

import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;


public class CapybaraEntityRenderer extends MobEntityRenderer<CapybaraEntity, Capybara_model> {

    public CapybaraEntityRenderer(Context context, Capybara_model entityModel, float f) {
        super(context, entityModel, f);

    }

       @Override
    public Identifier getTexture(CapybaraEntity entity) {
        return new Identifier("ooyo", "textures/entity/capydarkened.png");
    }
    




}
