package com.qingshanyinzhe.bioarcana.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker;

public class MirageWraith extends Entity{


    //About Health
    private static final TrackedData<Float> HEALTH =
            DataTracker.registerData(MirageWraith.class, TrackedDataHandlerRegistry.FLOAT);

    private final float initHealth;

    public MirageWraith(EntityType<? extends MirageWraith> entityType, World world) {
        super(entityType, world);
        this.initHealth = world.random.nextFloat() * 15.0F;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(HEALTH, initHealth);
    }

    public float getHealth() {
        return this.dataTracker.get(HEALTH);
    }

    public void setHealth(float newHealth) {
        this.dataTracker.set(HEALTH, newHealth);
    }

    ///////////////////////////

    @Override
    public boolean damage(ServerWorld world,DamageSource source, float amount) {
        if (world.isClient) {
            return false; // 客户端不处理伤害
        }
        float currentHealth = this.getHealth();
        currentHealth -= amount;

        // 更新血量
        this.setHealth(currentHealth);

        // 如果血量 <= 0，移除实体
        if (currentHealth <= 0) {
            // 触发自定义死亡逻辑、音效或掉落物等



            this.kill(world);
        }
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        //放置幻化为其它生物逻辑



    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putFloat("Health", this.getHealth());
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("Health")) {
            this.setHealth(nbt.getFloat("Health").orElse(0.0F));
        }
    }

    @Override
    public boolean collidesWith(Entity other) {
        return false;
    }


}
