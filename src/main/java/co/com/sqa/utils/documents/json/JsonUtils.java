package co.com.sqa.utils.documents.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import co.com.sqa.utils.properties.json.JsonProperties;

import java.io.*;
import java.util.List;

import static co.com.sqa.utils.constans.GeneralConstant.*;


public class JsonUtils {


    private  static ObjectMapper mapper = new ObjectMapper();

    public static File readJsonResponsePersonal(String jsonString, String stringJsonName) throws IOException {
        createFileJsonResponsePersonalName(jsonString,stringJsonName);
        return new File(JsonProperties.getJsonResponseDefaulthPaht() +
                String.format(ARCHIVO_JSON_FORMAT,stringJsonName));
    }

    public static void createFileJsonResponsePersonalName(String stringJson, String stringJsonName) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter(JsonProperties.getJsonResponseDefaulthPaht() +
                    String.format(ARCHIVO_JSON_FORMAT,stringJsonName));
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(stringJson);
            bfwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static File readJsonResponse(String jsonString) throws IOException {
        createFileJsonResponse(jsonString);
        return new File(JsonProperties.getJsonResponseDefaulthPaht() + ARCHIVO_JSON_DEFAULT);
    }


    public static void createFileJsonResponse(String stringJson) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter(JsonProperties.getJsonResponseDefaulthPaht() + ARCHIVO_JSON_DEFAULT);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(stringJson);
            bfwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createFileJsonRequestAndResponse(String stringJsonRequest,String stringJsonResponse, String stringJsonName) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter(JsonProperties.getJsonResponseDefaulthPaht() +
                    String.format(ARCHIVO_JSON_FORMAT,stringJsonName));
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(RESPONSE + stringJsonRequest + SEPARADOR + RESPONSE + stringJsonResponse);
            bfwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T> T jsonToModel(File file,Class<T> model) {

        T jsonData = null;
        try {
            jsonData = mapper.readValue(file, model);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public static String jsonModify(String filePath,List<String> tags, List<String> values){
        JsonObject jObject  = null;
        try {
            jObject = (JsonObject)(Object) new JsonParser().parse(new FileReader(filePath));
            for (int i = 0; i < tags.size(); i++) {
                jObject.addProperty(tags.get(i),values.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jObject.toString();
    }

    public static String jsonRead(String filePath){
        JsonObject jObject  = null;
        try {
            jObject = (JsonObject)(Object) new JsonParser().parse(new FileReader(filePath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jObject.toString();
    }


}
