package net.berrygames.cloudberry.proxy;

import net.berrygames.cloudberry.Cloudberry;
import net.berrygames.translation.ITranslatable;
import net.md_5.bungee.api.plugin.Plugin;

public abstract class BerryProxy extends Plugin implements Cloudberry {

    private static BerryProxy INSTANCE;

    public BerryProxy() {
        INSTANCE = this;
    }

    @Override
    public abstract ITranslatable getTranslatable();

    public static BerryProxy get() {
        return INSTANCE;
    }
}
