package net.berrygames.cloudberry.bukkit.title;

import lombok.Getter;
import lombok.Setter;
import net.berrygames.cloudberry.bukkit.BukkitCloud;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public abstract class AnimatedTitle extends HashMap<Integer, String[]> {

    private BukkitRunnable runnable;
    @Getter @Setter
    private int time = 0;

    @Getter
    private int interval, fadeIn, stay, fadeOut;

    public AnimatedTitle(int interval, int fadeIn, int stay, int fadeOut) {
        this.interval = interval;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public boolean isRunning() {
        return runnable != null || !runnable.isCancelled();
    }

    public void setRunnable(BukkitRunnable runnable) {
        this.runnable = runnable;
        runnable.runTaskTimerAsynchronously(BukkitCloud.get(), 0, interval);
    }
}
