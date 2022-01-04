package org.pickles.cvdesigner.storage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Paths;

public abstract class JsonStorageTemplate {
    private static String sceneName;
    private final JSONObject jsonObjectSubData;
    private final JSONParser jsonParser = new JSONParser();

    public JsonStorageTemplate(String sceneName) {
        JsonStorageTemplate.sceneName = sceneName;
        jsonObjectSubData = new JSONObject();
    }

    public JSONObject getDataFromStorage() throws IOException, ParseException {
        File storageFile = new File(getPathToStorage());
        if (!storageFile.exists()) {
            return new JSONObject();
        }

        FileReader storageFileReader = new FileReader(storageFile);
        JSONObject jsonObjectRoot = (JSONObject) new JSONParser().parse(storageFileReader);
        return (JSONObject) jsonObjectRoot.get(sceneName);
    }

    public static String getPathToStorage() {
        String storageDirPath = Paths.get(System.getProperty("user.dir"), "designer", "src").toString();
        File storageFile = new File(storageDirPath, "storage.json");
        return storageFile.getAbsolutePath();
    }

    @SuppressWarnings("unchecked")
    public void writePartialDataToSubJson(String key, Object value) {
        jsonObjectSubData.put(key, value);
    }

    /**
     * @return an absolute path to the storage file
     * @throws IOException if I/O error occurred
     * @throws ParseException if JSONParser ParseException occurred
     */
    @SuppressWarnings("unchecked")
    public String writeToJsonStorage() throws IOException, ParseException {
        String storageFilePath = getPathToStorage();
        File storageFile = new File(storageFilePath);

        JSONObject jsonObjectParent = new JSONObject();
        if (!storageFile.createNewFile()) {
            FileReader storageFileReader = new FileReader(storageFilePath);
            jsonObjectParent = (JSONObject) jsonParser.parse(storageFileReader);
        }
        if (jsonObjectSubData.isEmpty()) {
            throw new IllegalArgumentException("jsonObjectSubData cannot be empty -" +
                    " provide at least one key/value pair");
        }
        jsonObjectParent.put(sceneName, jsonObjectSubData);

        FileWriter storageFileWriter = new FileWriter(storageFilePath);
        storageFileWriter.write(jsonObjectParent.toJSONString());
        storageFileWriter.flush();
        return storageFilePath;
    }
}
