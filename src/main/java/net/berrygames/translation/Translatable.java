package net.berrygames.translation;

import net.berrygames.cloudberry.Cloudberry;
import net.berrygames.cloudberry.bukkit.BukkitCloud;
import org.bukkit.Bukkit;

import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

public class Translatable implements ITranslatable {

    @Override
    public String format(String playerName, String key, Object... parameters) {
        final var player = BukkitCloud.get().getPlayer(playerName);
        try {
            var bundle = new PropertyResourceBundle(Files.newInputStream(Paths.get(player.getLocale().toLanguageTag() + ".lang")));
            try {
                key = bundle.getString(key);
            } catch(MissingResourceException e) {
                try {
                    bundle = new PropertyResourceBundle(Files.newInputStream(Paths.get(I18n.ENGLISH.toLanguageTag() + ".lang")));
                    key = bundle.getString(key);
                } catch(Exception ignored) {}
                Bukkit.getLogger().severe(getClass().getName() + " : Key '" + key + "' wasn't found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.format(key, parameters);
    }

    @Override
    public void load(DataSource source) throws SQLException, IllegalAccessException, IOException {
        var connection = source.getConnection();

        final var query = "select * from translations";
        final var statement = connection.prepareStatement(query);

        for(Field field : I18n.class.getDeclaredFields()) {
            final var result = statement.executeQuery();
            final var locale = (Locale) field.get(null);
            final var file = new File(locale.toLanguageTag() + ".lang");
            if(!file.exists()) file.createNewFile();

            final var writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            while(result.next()) {
                writer.write(result.getString("i18n") + "=" + result.getString(locale.toLanguageTag()) + "\n");
            }
            writer.close();
            result.close();
        }
        statement.close();
        connection.close();
    }
}
