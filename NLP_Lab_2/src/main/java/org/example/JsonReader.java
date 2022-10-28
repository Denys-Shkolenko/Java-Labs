package org.example;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        String url = "https://api.mova.institute/udpipe/process?tokenizer&tagger&parser&model=uk&data=";
        String inputText = "веселився";
        JSONObject jsonObject = readJsonFromUrl(url + inputText);

        String output = jsonObject.getString("result");

        try {
            Map<String, String> translation = new ObjectMapper().readValue(
                    new File("src/main/resources/translation.json"), new TypeReference<>() {});

            for (var entry : translation.entrySet()) {
                output = output.replace(entry.getKey(), entry.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(output);
    }
}
