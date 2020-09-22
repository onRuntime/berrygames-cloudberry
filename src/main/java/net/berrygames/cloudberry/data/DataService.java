package net.berrygames.cloudberry.data;

import com.google.gson.JsonElement;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface DataService {

    JsonElement getPlayer(String name) throws IOException, InterruptedException, ParseException;

    void updatePlayer(String data);
}
