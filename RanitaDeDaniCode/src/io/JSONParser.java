package io;

import gameObjects.Constants;
import gameObjects.Player;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONParser {
    public static ArrayList<ScoreData> readFile() throws FileNotFoundException {
        ArrayList<ScoreData> dataList = new ArrayList<ScoreData>();

        File file = new File(Constants.SCORE_PATH);
        if(!file.exists() || file.length() == 0){
            return dataList;
        }

        JSONTokener parser = new JSONTokener(new FileInputStream(file));
        JSONArray JSONList = new JSONArray(parser);

        for(int i = 0; i < JSONList.length(); i++){
            JSONObject obj = (JSONObject) JSONList.get(i);
            ScoreData data = new ScoreData();
            data.setScore(obj.getInt("score"));
            data.setDate(obj.getString("date"));
            dataList.add(data);
        }

        return dataList;
    }

    public static ArrayList<SkinData> readSkinFile() throws FileNotFoundException {
        ArrayList<SkinData> dataList = new ArrayList<SkinData>();

        File file = new File(Constants.SKIN_PATH);
        if(!file.exists() || file.length() == 0){
            return dataList;
        }
        JSONTokener parser = new JSONTokener(new FileInputStream(file));
        JSONArray JSONList = new JSONArray(parser);

        for(int i = 0; i < JSONList.length(); i++){
            JSONObject obj = (JSONObject) JSONList.get(i);
            SkinData data = new SkinData();

            data.setName(obj.getString("name"));
            data.setComprada(obj.getBoolean("comprada"));
            data.setIndex(obj.getInt("index"));
            dataList.add(data);
        }

        for (SkinData sk : dataList){
            if(sk.getComprada()){
                Player.unlocked_skins[sk.getIndex()] = true;
            }
        }
        return dataList;

    }

    public static ArrayList<MoneyData> readMoneyData() throws FileNotFoundException {
        ArrayList<MoneyData> dataList = new ArrayList<MoneyData>();

        File file = new File(Constants.MONEY_PATH);
        if(!file.exists() || file.length() == 0){
            return dataList;
        }
        JSONTokener parser = new JSONTokener(new FileInputStream(file));
        JSONArray JSONList = new JSONArray(parser);

        for(int i = 0; i < JSONList.length(); i++){
            JSONObject obj = (JSONObject) JSONList.get(i);
            MoneyData data = new MoneyData();

            data.setAmount(obj.getInt("amount"));
            dataList.add(data);
        }
        return dataList;

    }


    public static void writeFile(ArrayList<ScoreData> dataList) throws IOException {
        File output = new File(Constants.SCORE_PATH);
        output.getParentFile().mkdirs();
        output.createNewFile();

        JSONArray JSONList = new JSONArray();

        for(ScoreData data : dataList){
            JSONObject obj = new JSONObject();
            obj.put("score", data.getScore());
            obj.put("date", data.getDate());

            JSONList.put(obj);
        }

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(output.toURI()));
        JSONList.write(writer);
        writer.close();
    }

    public static void writeSkinFile(ArrayList<SkinData> dataList) throws IOException {
        File output = new File(Constants.SKIN_PATH);
        output.getParentFile().mkdirs();
        output.createNewFile();

        JSONArray JSONList = new JSONArray();

        for(SkinData data : dataList){
            JSONObject obj = new JSONObject();
            obj.put("name", data.getName());
            obj.put("comprada", data.getComprada());
            obj.put("index", data.getIndex());

            JSONList.put(obj);
        }

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(output.toURI()));
        JSONList.write(writer);
        writer.close();
    }

    public static void writeMoneyFile(ArrayList<MoneyData> dataList) throws IOException {
        File output = new File(Constants.MONEY_PATH);
        output.getParentFile().mkdirs();
        output.createNewFile();

        JSONArray JSONList = new JSONArray();

        for(MoneyData data : dataList){
            JSONObject obj = new JSONObject();
            obj.put("amount", data.getAmount());
            JSONList.put(obj);
        }

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(output.toURI()));
        JSONList.write(writer);
        writer.close();
    }
}
