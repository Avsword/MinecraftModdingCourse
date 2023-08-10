package tamk.aarov;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;

// Lisätyt importit
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ExampleMod implements ModInitializer {

	// Uusi itemi, Cabostaff.
	public static final Cabostaff Cabostaff = new Cabostaff(new FabricItemSettings().maxDamage(5).rarity(Rarity.RARE));

	// Uusi blocki, opaali ore.
	public static final Block opal_ore  = new Block(FabricBlockSettings.create().strength(1.0f));

	// Uusi ore toiminnallisuus
	public static final RegistryKey<PlacedFeature> opal = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("ooyo","opal"));
	
	// Uusi ore tiputtaa opaali itemin, eikä täten vaadi uunia, kuten rauta ja kulta.
	public static final Item opal_item = new Item(new FabricItemSettings());
	

	// Uusi joukko, capybara.
    public static final EntityType<CapybaraEntity> CAPY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("ooyo", "capybara"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CapybaraEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

	public static final EntityModelLayer MODEL_CAPYBARA_LAYER = new EntityModelLayer(new Identifier("ooyo", "capybara"), "main");


	// Capybara tiputtaa uuden esineen
	public static final Item orange = new Item(new FabricItemSettings());


	@Override
	public void onInitialize() {
		// Rekisteröi Cabostaff
		Registry.register(Registries.ITEM, new Identifier("ooyo", "cabostaff"), Cabostaff);
		
		// Tämän avulla esine löytyy "Combat" item groupista.
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
			content.add(Cabostaff);
		});

		// Rekisteröi opaali ore.
		// Tässä tilanteessa minecraft:example_ore korvataan ooyo:opal_ore:lla, sillä me olemme rekisteröineet malmin näillä tiedoilla.
		Registry.register(Registries.BLOCK, new Identifier("ooyo", "opal_ore"), opal_ore);
		Registry.register(Registries.ITEM, new Identifier("ooyo", "opal_ore"), new BlockItem(opal_ore, new FabricItemSettings()));

		// Lisää ore worldiin
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, opal);

		// Rekisteröi opaali item
		Registry.register(Registries.ITEM, new Identifier("ooyo", "opal_item"), opal_item);

		// Lisää capybaralle attribuutit.
		FabricDefaultAttributeRegistry.register(CAPY, CapybaraEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D).add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D));

		// Rekisteröi capybara renderöijä
		EntityRendererRegistry.register(CAPY  , (context) -> {
            return new CapybaraEntityRenderer(context, new Capybara_model(context.getPart(MODEL_CAPYBARA_LAYER)), 0.5f);
        });
 
        EntityModelLayerRegistry.registerModelLayer(MODEL_CAPYBARA_LAYER, Capybara_model::getTexturedModelData);

		/* BiomeModifications.addSpawn(BiomeSelectors.all(), SpawnGroup.CREATURE, CAPY, 70, 1, 200); */
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, CAPY, 90, 1, 3);
		
		// Rekisteröi orange item
		Registry.register(Registries.ITEM, new Identifier("ooyo", "orange"), orange);


	}

}