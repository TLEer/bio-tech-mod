package com.qingshanyinzhe.bioarcana.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class BioarcanaEntities {

    public static final RegistryKey<EntityType<?>> MIRAGE_WRAITH_KEY = RegistryKey.of(
            RegistryKeys.ENTITY_TYPE,
            Identifier.of("bioarcana", "mirage_wraith")
    );

    public static final EntityType<MirageWraith> MIRAGE_WRAITH_ENTITY_TYPE =

                    EntityType.Builder.create(MirageWraith::new, SpawnGroup.MISC)
                            .dimensions(0.75F, 0.75F) // 调整大小
                            .maxTrackingRange(7) // 同步范围
                            .build(MIRAGE_WRAITH_KEY);

    public static void registerAll() {
        // 此方法中调用上面的注册过程
        Registry.register(
                Registries.ENTITY_TYPE,
                MIRAGE_WRAITH_KEY.getValue(),
                MIRAGE_WRAITH_ENTITY_TYPE
        );


    }
}
