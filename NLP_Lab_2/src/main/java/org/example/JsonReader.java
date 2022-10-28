package org.example;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

    public static final String url = "https://api.mova.institute/udpipe/process?tokenizer&tagger&parser&model=uk&data=";

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

    public static String getPosTagging(String inputString) throws IOException {
        inputString = inputString.replace(" ", "%20");
        JSONObject jsonObject = readJsonFromUrl(url + inputString);
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

        return output;
    }

    public static void main(String[] args) throws IOException, JSONException {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Текст ('вихід' - для завершення): ");
            String inputLine = input.nextLine();

            if ("вихід".equalsIgnoreCase(inputLine)) {
                break;
            }

            System.out.println(getPosTagging(inputLine));
        }
    }
}
