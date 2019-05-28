package Json;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConventJsonFromString {
    public static void main(String[] args) {

        //String parameter = "{'testRailPath':'http://192.168.9.45/testrail2','username':'elden@mcgods.top','password':'Wesoft1234'}";
        String parameter = "{'testRailPath':'http://192.168.9.45/testrail2'}";
        parameter.replace("\'","\"");
        JsonParser parse =new JsonParser();  //创建json解析器
        try {
            //使用gson库从格式化字符串中生成JsonObject
            JsonObject gson_obj = parse.parse(parameter).getAsJsonObject();

            //parameter = JSONObject simple_obj = (JSONObject) JSONValue.parse(escaped_parameter);

            //System.out.println(gson_obj.get("testRailPath").getAsString());
            //System.out.println(simple_obj.get("testRailPath"));

        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println(e);
        }

    }
}
