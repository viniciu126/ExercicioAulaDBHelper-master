package br.usjt.desmob.paises;

import android.content.Context;
import android.net.ConnectivityManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Vinicius Maciel on 14/4/2018.
 * RA 816117960
 */

public class PaisDataNetwork {

    public static Pais[] buscarPaises(String url, String regiao) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        ArrayList<Pais> paises = new ArrayList<>();

        Request request = new Request.Builder()
                .url(url+regiao)
                .build();

        Response response = client.newCall(request).execute();

        String resultado = response.body().string();

        try {
            JSONArray vetor = new JSONArray(resultado);
            for(int i = 0; i < vetor.length(); i++){
                JSONObject item = (JSONObject) vetor.get(i);
                Pais pais = new Pais();
                try {
                    pais.setArea(item.getInt("area"));
                } catch (Exception e){
                    pais.setArea(0);
                }
                pais.setBandeira(item.getString("flag"));
                pais.setCapital(item.getString("capital"));
                pais.setNome(item.getString("name"));
                pais.setRegiao(item.getString("region"));
                pais.setCodigo3(item.getString("alpha3Code"));
                try {
                    pais.setGini(item.getDouble("gini"));
                } catch (Exception e) {
                    pais.setGini(0.0);
                }
                try {
                    pais.setPopulacao(item.getInt("population"));
                } catch (Exception e) {
                    pais.setPopulacao(0);
                }
                pais.setDemonimo(item.getString("demonym"));
                pais.setSubRegiao(item.getString("subregion"));

                JSONArray latlng = item.getJSONArray("latlng");

                try {
                    pais.setLatitude(latlng.getDouble(0));
                } catch (Exception e) {
                    pais.setLatitude(0);
                }
                try {
                    pais.setLongitude(latlng.getDouble(1));
                } catch (Exception e) {
                    pais.setLongitude(0);
                }

                JSONArray languages = item.getJSONArray("languages");
                ArrayList<String> idioma = new ArrayList<>();
                try {
                    for(int j = 0; j < languages.length(); j++){
                        idioma.add(languages.getString(j));
                    }
                    pais.setIdiomas(idioma);
                }catch (Exception e){
                    pais.setIdiomas(null);
                }

                JSONArray currencies = item.getJSONArray("currencies");
                ArrayList<String> moedas = new ArrayList<>();
                try {
                    for(int j = 0; j < currencies.length(); j++){
                        moedas.add(currencies.getString(j));
                    }
                    pais.setMoedas(moedas);
                }catch (Exception e){
                    pais.setMoedas(null);
                }

                JSONArray timezones = item.getJSONArray("timezones");
                ArrayList<String> fusos = new ArrayList<>();
                try {
                    for(int j = 0; j < timezones.length(); j++){
                        fusos.add(timezones.getString(j));
                    }
                    pais.setFusos(fusos);
                }catch (Exception e){
                    pais.setFusos(null);
                }

                JSONArray borders = item.getJSONArray("borders");
                ArrayList<String> fronteiras = new ArrayList<>();
                try {
                    for(int j = 0; j < borders.length(); j++){
                        fronteiras.add(borders.getString(j));
                    }
                    pais.setFronteiras(fronteiras);
                }catch (Exception e){
                    pais.setFronteiras(null);
                }

                JSONArray regionalBlocs = item.getJSONArray("regionalBlocs");
                ArrayList<String> dominios = new ArrayList<>();
                try {
                    for(int j = 0; j < regionalBlocs.length(); j++){
                        dominios.add(regionalBlocs.getString(j));
                    }
                    pais.setDominios(dominios);
                }catch (Exception e){
                    pais.setDominios(null);
                }



                paises.add(pais);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        return paises.toArray(new Pais[0]);
    }

    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
