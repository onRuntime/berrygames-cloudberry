package net.berrygames.translation;

import org.bukkit.entity.Player;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * This file is a part of common-parent, located on net.unreaded.api.i18n
 * <p>
  * Copyright (c) BerryGames <https://berrygames.net> - All rights reserved
 * <p>
 *
 * @author Aurélie Gabrielle (MissIlys) {@literal <MISSING>}
 * Created the 24/08/2018 at 16:45.
 */
public interface ITranslatable {

    String format(String playerName, String key, Object... parameters);

    void load(DataSource source) throws SQLException, IllegalAccessException, IOException;
}
