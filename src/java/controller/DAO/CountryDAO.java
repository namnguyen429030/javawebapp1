/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Country;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Mosena
 */
public class CountryDAO {
    public ArrayList<Country> getCountriesDetail(){
        ArrayList<Country> list=new ArrayList<>();
        try {
            // TODO code application logic here
            URL url=new URL("https://restcountries.com/v2/all?fields=name,callingCodes");
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Accept", "application/json");
            BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBuilder=new StringBuilder();
            String line;
            while((line=br.readLine())!=null){
                responseBuilder.append(line);
            }
            br.close();
            JSONArray countries=new JSONArray(responseBuilder.toString());
            for(int i=0;i<countries.length();i++){
                JSONObject country=countries.getJSONObject(i);
                String name=country.getString("name");
                JSONArray callingCodes=country.getJSONArray("callingCodes");
                String code=callingCodes.getString(0);
                Country c=new Country(name,code);
                list.add(c);
            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(CountryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
