package lt.viko.eif.vvilimaite.project.constructor;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Random;

public class FilmDao {
    /**
     * responsible to get film data from API
     * @param id looking film name by id
     * @return film info
     */
    static public Film getFilmData(String id) throws IOException, InterruptedException {
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://online-movie-database.p.rapidapi.com/title/get-images?tconst="+id))
                .header("rapidapi-host", "online-movie-database.p.rapidapi.com")
                .header("rapidapi-key", "1484a8592cmsh8e8327930cc58b3p142ed7jsnaa21b2abe1a8")
                .build();
        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        if(response.body().equals("null")){
            return null;
        }
        JSONObject obj = new JSONObject(response.body());
        if(obj.getInt("totalImageCount")==0){
            return null;
        }
        String url=obj.getJSONArray("images").getJSONObject(0).getString("url");
        String name=obj.getJSONArray("images").getJSONObject(0).getString("caption");
        Film film=new Film(id,url,name);
        System.out.println(film+" Filmdao");
        return film;

    }
    protected static ArrayList<Film> films;

    public static ArrayList<Film> fetchRandomFilms(int size) throws IOException, InterruptedException {
        Random rand = new Random();
        String code;
        films = new ArrayList<>();
        while (films.size() != size) {
            code = "tt0" + rand.nextInt(1000000);
            Film film = FilmDao.getFilmData(code);
            if (film != null) {
                films.add(FilmDao.getFilmData(code));
            }
        }
        return films;
    }
}
