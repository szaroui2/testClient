package org.example.Parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.example.train.Input;
import org.example.train.Output;


import java.io.*;

public class JsonParser {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static Input fromFile(String filePath) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(filePath));
        return gson.fromJson(reader, Input.class);
    }

    public static void toFile(String filePath, Output output) throws IOException {
        Writer writer = new FileWriter(filePath);
        gson.toJson(output, writer);
        writer.flush();
        writer.close();
    }

}
