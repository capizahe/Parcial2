package com.movil.parcial2;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Consumer {

    public static final String[] REQUEST = {"http://10.10.9.101:3000/aptos/aptostatus","http://10.10.9.101:3000/aptos/changeAptoStatusBody"};
    public static ArrayList<RoomLight> room_status = new ArrayList<>();

    public static void loadRoomsStatus(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try{

                    URL url = new URL(REQUEST[0]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");

                    InputStream inputStream = httpURLConnection.getInputStream();
                    if(httpURLConnection.getResponseCode() == 200 ){
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF8");
                        JSONObject jsonObject = new JSONObject(getStringFromInputStream(inputStream));
                        room_status.add(new RoomLight("luzBano",jsonObject.getBoolean("luzBano")));
                        room_status.add(new RoomLight("luzCocina",jsonObject.getBoolean("luzCocina")));
                        room_status.add(new RoomLight("luzHabitacion1",jsonObject.getBoolean("luzHabitacion1")));
                        room_status.add(new RoomLight("luzHabitacion2",jsonObject.getBoolean("luzHabitacion2")));
                        room_status.add(new RoomLight("luzHabitacion3",jsonObject.getBoolean("luzHabitacion3")));
                        room_status.add(new RoomLight("luzHabitacion4",jsonObject.getBoolean("luzHabitacion4")));
                        System.out.println(room_status.toString());
                    }
                }catch (MalformedURLException E){
                    E.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException ex){
                    ex.printStackTrace();
                }
            }
        });
        UpdateLights();
    }

    public static void updateLights(int index){
        RoomLight rl = room_status.get(index);
        rl.setStatus(!rl.getStatus());
        UpdateLights();
    }

    private static String generateJSON(){
        String new_json = "{\"luzBano\":" +room_status.get(0).getStatus()+
                ",\"luzCocina\":" +room_status.get(1).getStatus()+
                ",\"luzHabitacion1\":" +room_status.get(2).getStatus()+
                ",\"luzHabitacion2\":" +room_status.get(3).getStatus()+
                ",\"luzHabitacion3\":" +room_status.get(4).getStatus()+
                ",\"luzHabitacion4\":" +room_status.get(5).getStatus()+
                "}";
     return new_json;
    }

    public static void UpdateLights(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try{
                    URL urlservice = new URL(REQUEST[1]);
                    HttpURLConnection httpURLConnection =(HttpURLConnection)urlservice.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type","application/json");
                    httpURLConnection.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                    wr.writeBytes(generateJSON());
                    wr.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                        if(httpURLConnection.getResponseCode() == 200){
                    }

                }catch (MalformedURLException ex1){
                    Log.e("Error 1", ex1.getMessage());

                }catch (ProtocolException ex2){
                    Log.e("Error 2", ex2.getMessage());
                }catch (IOException ex3){
                    Log.e("Error 3", ex3.getMessage());
                    ex3.printStackTrace();


                }

            }
        });
    }

    public static String getStringFromInputStream(InputStream stream) throws IOException {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }



}
