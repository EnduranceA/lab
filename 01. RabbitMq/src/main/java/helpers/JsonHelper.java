package helpers;

import dto.UserDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHelper {

    private JSONParser jsonParser;

    public JsonHelper() {
        this.jsonParser = new JSONParser();
    }
    public static String buildJsonFromUser(UserDto userDto) {
        JSONObject object = new JSONObject();
        object.put("firstName", userDto.getFirstName());
        object.put("lastName", userDto.getLastName());
        object.put("age", userDto.getAge());
        object.put("date", userDto.getDate());
        object.put("numberOfPassport", userDto.getNumberOfPassport());
        return JSONObject.toJSONString(object);
    }

    public JSONObject getUser(String msg) {
        try {
            return (JSONObject) jsonParser.parse(msg);
        } catch (ParseException e) {
            throw  new IllegalArgumentException(e);
        }
    }
}
