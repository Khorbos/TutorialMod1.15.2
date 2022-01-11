package com.khorbos.tutorialmod.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.storage.loot.LootTables;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;

public class ExampleEntity extends AnimalEntity {
    private static final DataParameter<Integer> SLIME_SIZE = EntityDataManager.createKey(ExampleEntity.class, DataSerializers.VARINT);
    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private boolean wasOnGround;

    public ExampleEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveController = new ExampleEntity.MoveHelperController(this);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ExampleEntity.FloatGoal(this));
        this.goalSelector.addGoal(3, new ExampleEntity.FaceRandomGoal(this));
        this.goalSelector.addGoal(5, new ExampleEntity.HopGoal(this));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(SLIME_SIZE, 1);
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    protected void setSlimeSize(int size, boolean resetHealth) {
        this.dataManager.set(SLIME_SIZE, size);
        this.recenterBoundingBox();
        this.recalculateSize();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)(size * size));
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)(0.2F + 0.1F * (float)size));
        if (resetHealth) {
            this.setHealth(this.getMaxHealth());
        }

        this.experienceValue = size;
    }



    /**
     * Returns the size of the slime.
     */
    public int getSlimeSize() {
        return this.dataManager.get(SLIME_SIZE);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Size", this.getSlimeSize() - 1);
        compound.putBoolean("wasOnGround", this.wasOnGround);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        int i = compound.getInt("Size");
        if (i < 0) {
            i = 0;
        }

        this.setSlimeSize(i + 1, false);
        super.readAdditional(compound);
        this.wasOnGround = compound.getBoolean("wasOnGround");
    }

    public boolean isSmallSlime() {
        return this.getSlimeSize() <= 1;
    }

    protected IParticleData getSquishParticle() {
        return ParticleTypes.PORTAL;
    }

    protected boolean isDespawnPeaceful() {
        return false;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
        this.prevSquishFactor = this.squishFactor;
        super.tick();
        if (this.onGround && !this.wasOnGround) {
            int i = this.getSlimeSize();

            if (spawnCustomParticles()) i = 0; // don't spawn particles if it's handled by the implementation itself
            for(int j = 0; j < i * 8; ++j) {
                float f = this.rand.nextFloat() * ((float)Math.PI * 2F);
                float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
                this.world.addParticle(this.getSquishParticle(), this.getPosX() + (double)f2, this.getPosY(), this.getPosZ() + (double)f3, 0.0D, 0.0D, 0.0D);
            }

            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            this.squishAmount = -0.5F;
        } else if (!this.onGround && this.wasOnGround) {
            this.squishAmount = 1.0F;
        }

        this.wasOnGround = this.onGround;
        this.alterSquishAmount();
    }

    protected void alterSquishAmount() {
        this.squishAmount *= 0.6F;
    }

    /**
     * Gets the amount of time the slime needs to wait between jumps.
     */
    protected int getJumpDelay() {
        return this.rand.nextInt(20) + 10;
    }

    public void recalculateSize() {
        double d0 = this.getPosX();
        double d1 = this.getPosY();
        double d2 = this.getPosZ();
        super.recalculateSize();
        this.setPosition(d0, d1, d2);
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (SLIME_SIZE.equals(key)) {
            this.recalculateSize();
            this.rotationYaw = this.rotationYawHead;
            this.renderYawOffset = this.rotationYawHead;
            if (this.isInWater() && this.rand.nextInt(20) == 0) {
                this.doWaterSplashEffect();
            }
        }

        super.notifyDataManagerChange(key);
    }

    public EntityType<? extends ExampleEntity> getType() {
        return (EntityType<? extends ExampleEntity>) super.getType();
    }

    @Override
    public void remove(boolean keepData) {
        int i = this.getSlimeSize();
        if (!this.world.isRemote && i > 1 && this.getHealth() <= 0.0F && !this.removed) {
            int j = 2 + this.rand.nextInt(3);

            for(int k = 0; k < j; ++k) {
                float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
                float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
                ExampleEntity slimeentity = this.getType().create(this.world);
                if (this.hasCustomName()) {
                    slimeentity.setCustomName(this.getCustomName());
                }

                if (this.isNoDespawnRequired()) {
                    slimeentity.enablePersistence();
                }

                slimeentity.setInvulnerable(this.isInvulnerable());
                slimeentity.setSlimeSize(i / 2, true);
                slimeentity.setLocationAndAngles(this.getPosX() + (double)f, this.getPosY() + 0.5D, this.getPosZ() + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.world.addEntity(slimeentity);
            }
        }

        super.remove(keepData);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.625F * sizeIn.height;
    }

    /**
     * Indicates weather the slime is able to damage the player (based upon the slime's size)
     */
    protected boolean canDamagePlayer() {
        return false;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return this.isSmallSlime() ? SoundEvents.ENTITY_SLIME_HURT_SMALL : SoundEvents.ENTITY_SLIME_HURT;
    }

    protected SoundEvent getDeathSound() {
        return this.isSmallSlime() ? SoundEvents.ENTITY_SLIME_DEATH_SMALL : SoundEvents.ENTITY_SLIME_DEATH;
    }

    protected SoundEvent getSquishSound() {
        return this.isSmallSlime() ? SoundEvents.ENTITY_SLIME_SQUISH_SMALL : SoundEvents.ENTITY_SLIME_SQUISH;
    }

    protected ResourceLocation getLootTable() {
        return this.getSlimeSize() == 1 ? this.getType().getLootTable() : LootTables.EMPTY;
    }

    public static boolean func_223366_c(EntityType<ExampleEntity> entity, IWorld worldIn, SpawnReason reason, BlockPos blockPos, Random randomIn) {
        Biome biome = worldIn.getBiome(blockPos);
        if (biome == Biomes.END_HIGHLANDS || biome == Biomes.END_BARRENS || biome == Biomes.END_MIDLANDS || biome == Biomes.SMALL_END_ISLANDS) {
            return canSpawnOn(entity, worldIn, reason, blockPos, randomIn);
        }
        return false;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume() {
        return 0.4F * (float)this.getSlimeSize();
    }

    /**
     * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
     * use in wolves.
     */
    public int getVerticalFaceSpeed() {
        return 0;
    }

    /**
     * Returns true if the slime makes a sound when it jumps (based upon the slime's size)
     */
    protected boolean makesSoundOnJump() {
        return this.getSlimeSize() > 0;
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jump() {
        Vec3d vec3d = this.getMotion();
        this.setMotion(vec3d.x, (double)this.getJumpUpwardsMotion(), vec3d.z);
        this.isAirBorne = true;
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int i = this.rand.nextInt(3);
        if (i < 2 && this.rand.nextFloat() < 0.5F * difficultyIn.getClampedAdditionalDifficulty()) {
            ++i;
        }

        int j = 1 << i;
        this.setSlimeSize(j, true);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected SoundEvent getJumpSound() {
        return this.isSmallSlime() ? SoundEvents.ENTITY_SLIME_JUMP_SMALL : SoundEvents.ENTITY_SLIME_JUMP;
    }

    public EntitySize getSize(Pose poseIn) {
        return super.getSize(poseIn).scale(0.255F * (float)this.getSlimeSize());
    }

    /**
     * Called when the slime spawns particles on landing, see onUpdate.
     * Return true to prevent the spawning of the default particles.
     */
    protected boolean spawnCustomParticles() { return false; }

    static class FaceRandomGoal extends Goal {
        private final ExampleEntity slime;
        private float chosenDegrees;
        private int nextRandomizeTime;

        public FaceRandomGoal(ExampleEntity slimeIn) {
            this.slime = slimeIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return this.slime.getAttackTarget() == null && (this.slime.onGround || this.slime.isInWater() || this.slime.isInLava() || this.slime.isPotionActive(Effects.LEVITATION)) && this.slime.getMoveHelper() instanceof ExampleEntity.MoveHelperController;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (--this.nextRandomizeTime <= 0) {
                this.nextRandomizeTime = 40 + this.slime.getRNG().nextInt(60);
                this.chosenDegrees = (float)this.slime.getRNG().nextInt(360);
            }

            ((ExampleEntity.MoveHelperController)this.slime.getMoveHelper()).setDirection(this.chosenDegrees, false);
        }
    }

    static class FloatGoal extends Goal {
        private final ExampleEntity slime;

        public FloatGoal(ExampleEntity slimeIn) {
            this.slime = slimeIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
            slimeIn.getNavigator().setCanSwim(true);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return (this.slime.isInWater() || this.slime.isInLava()) && this.slime.getMoveHelper() instanceof ExampleEntity.MoveHelperController;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.slime.getRNG().nextFloat() < 0.8F) {
                this.slime.getJumpController().setJumping();
            }

            ((ExampleEntity.MoveHelperController)this.slime.getMoveHelper()).setSpeed(1.2D);
        }
    }

    static class HopGoal extends Goal {
        private final ExampleEntity slime;

        public HopGoal(ExampleEntity slimeIn) {
            this.slime = slimeIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return !this.slime.isPassenger();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            ((ExampleEntity.MoveHelperController)this.slime.getMoveHelper()).setSpeed(1.0D);
        }
    }

    static class MoveHelperController extends MovementController {
        private float yRot;
        private int jumpDelay;
        private final ExampleEntity slime;
        private boolean isAggressive;

        public MoveHelperController(ExampleEntity slimeIn) {
            super(slimeIn);
            this.slime = slimeIn;
            this.yRot = 180.0F * slimeIn.rotationYaw / (float)Math.PI;
        }

        public void setDirection(float yRotIn, boolean aggressive) {
            this.yRot = yRotIn;
            this.isAggressive = aggressive;
        }

        public void setSpeed(double speedIn) {
            this.speed = speedIn;
            this.action = MovementController.Action.MOVE_TO;
        }

        public void tick() {
            this.mob.rotationYaw = this.limitAngle(this.mob.rotationYaw, this.yRot, 90.0F);
            this.mob.rotationYawHead = this.mob.rotationYaw;
            this.mob.renderYawOffset = this.mob.rotationYaw;
            if (this.action != MovementController.Action.MOVE_TO) {
                this.mob.setMoveForward(0.0F);
            } else {
                this.action = MovementController.Action.WAIT;
                if (this.mob.onGround) {
                    this.mob.setAIMoveSpeed((float)(this.speed * this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.slime.getJumpDelay();
                        if (this.isAggressive) {
                            this.jumpDelay /= 3;
                        }

                        this.slime.getJumpController().setJumping();
                        if (this.slime.makesSoundOnJump()) {
                            this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), ((this.slime.getRNG().nextFloat() - this.slime.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                        }
                    } else {
                        this.slime.moveStrafing = 0.0F;
                        this.slime.moveForward = 0.0F;
                        this.mob.setAIMoveSpeed(0.0F);
                    }
                } else {
                    this.mob.setAIMoveSpeed((float)(this.speed * this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
                }

            }
        }
    }
}
