import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * 名称: GsonUtil
 * 作者: 牛毅
 * 日期: 2018/6/11 13:31
 * 描述: gson工具类
 */
public class GsonUtil {

    private static Gson gson = new GsonBuilder().create();

    public static String bean2Json(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T json2Bean(String json, Class<T> objClass) {
        return gson.fromJson(json, objClass);
    }

    public static String jsonFormatter(String uglyJsonStr) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonElement je = parser.parse(uglyJsonStr);
        return gson.toJson(je);
    }
}
