package net.berrygames;

import java.util.function.Consumer;

/**
 * This file is a part of BerryGames, located on net.berrygames.tools
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-09-14 at 11:10.
 */
public interface CallBack<T> {
    void then(Consumer<? super T> success);
}