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

public class Consumer {

    public static final String[] REQUEST = {"http://192.168.103.141:3000/aptos/aptostatus","http://192.168.103.141:3000/aptos/changeAptoStatusBody",""};
    public static ArrayList<RoomLight> room_status;

    private static void loadRoomsStatus(){
        ArrayList<RoomLight> rooms = new ArrayList<>();

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
                    }
                }catch (MalformedURLException E){

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException ex){

                }
            }
        });
    }

    public void updateLights(int index){
        RoomLight rl = room_status.get(index);
        rl.setStatus(!rl.getStatus());
        generateJSON();
    }

    public String generateJSON(){
        String new_json = "{\"luzBano\":" +room_status.get(0).getStatus()+
                ",\"luzCocina\":true" +room_status.get(1).getStatus()+
                ",\"luzHabitacion1\":" +room_status.get(2).getStatus()+
                ",\"luzHabitacion2\":" +room_status.get(3).getStatus()+
                ",\"luzHabitacion3\":" +room_status.get(4).getStatus()+
                ",\"luzHabitacion4\":" +room_status.get(5).getStatus()+
                "}";
     return new_json;
    }

    public void UpdateLights(final String data){
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
                    wr.writeBytes(data);
                    wr.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    if(httpURLConnection.getResponseCode() == 200){
                    }

                }catch (MalformedURLException ex1){

                }catch (ProtocolException ex2){

                }catch (IOException ex3){

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
