package com.moonlight.sadako;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(SadakoMod.ID)
public class SadakoMod {
    public static final String ID = "sadako";
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ID);
    public static final RegistryObject<EntityType<SadakoEntity>> SADAKO = ENTITIES.register("sadako", () ->
            EntityType.Builder.of(SadakoEntity::new, MobCategory.MONSTER).sized(0.6f, 1.9f).clientTrackingRange(8).build(ID + ":sadako"));
    public static final RegistryObject<Item> EGG = ITEMS.register("sadako_spawn_egg", () ->
            new ForgeSpawnEggItem(SADAKO, 0x141414, 0xE5E5E5, new Item.Properties()));

    public SadakoMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITIES.register(bus);
        ITEMS.register(bus);
        bus.addListener(this::attributes);
        bus.addListener(this::creativeTab);
    }
    private void attributes(EntityAttributeCreationEvent event) {
        event.put(SADAKO.get(), SadakoEntity.createAttributes().build());
    }
    private void creativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) event.accept(EGG.get());
    }
}
