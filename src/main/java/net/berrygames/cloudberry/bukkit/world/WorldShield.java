package net.berrygames.cloudberry.bukkit.world;

public interface WorldShield {

    void enableAll();
    void disableAll();

    void enablePvP(boolean enabled);
    boolean isPvP();
    void enablePvE(boolean enabled);
    boolean isPvE();

    void enableDamage(boolean enabled);
    void enableFoodLevelChange(boolean enabled);
    boolean isDamage();
    boolean isFoodLevelChange();

    default void enableGrief(boolean enabled) {
        enableBlockBreak(enabled);
        enableBlockPlace(enabled);
    }
    void enableBlockBreak(boolean enabled);
    void enableBlockPlace(boolean enabled);
    default boolean isGriefable() {
        return isBlockBreak() && isBlockPlace();
    }
    boolean isBlockBreak();
    boolean isBlockPlace();

    void enableDrop(boolean enabled);
    boolean isDrop();
    void enabledPickup(boolean enabled);
    boolean isPickup();
}
