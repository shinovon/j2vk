package ru.curoviyxru.j2vk.api.objects;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

/**
 *
 * @author curoviyxru
 */
public class Geo extends VKSerializableObject {

    public String type;
    public Coordinates coords;
    public Place place;

    public VKSerializableObject deserialize(JSONObject json) {
        if (json == null) {
            return this;
        }

        type = json.optString("type");
        JSONArray array = json.optJSONArray("coordinates");
        if (array != null && array.length() == 2) {
            coords = new Coordinates(array.optDouble(0), array.optDouble(1));
        }
        JSONObject obj = json.optJSONObject("place");
        if (obj != null) {
            place = (Place) new Place().deserialize(obj);
        }

        return this;
    }

    public static class Coordinates extends VKSerializableObject {

        public double latitude, longitude;

        public Coordinates() {
        }

        public Coordinates(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public VKSerializableObject deserialize(JSONObject json) {
            if (json == null) {
                return this;
            }

            latitude = json.optDouble("latitude");
            longitude = json.optDouble("longitude");

            return this;
        }
    }

    public static class Place extends Coordinates {

        public int id, created;
        public String title, icon, country, city;

        public VKSerializableObject deserialize(JSONObject json) {
            super.deserialize(json);
            if (json == null) {
                return this;
            }

            id = json.optInt("id");
            created = json.optInt("created");
            title = json.optString("title");
            icon = json.optString("icon");
            country = json.optString("country");
            city = json.optString("city");

            return this;
        }
    }
}
