package com.movie.service;

import com.movie.models.Movie;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class OMDBService {

    @Value("${omdb.api.key}")
    private String apiKey;

    @Value("${omdb.api.url}")
    private String apiUrl;

   
   
    public String testConnection() {
        try {
            String url = apiUrl + "?t=Inception&apikey=" + apiKey;
            System.out.println("Testing URL: " + url);
            String response = callAPI(url);
            System.out.println("Response: " + response);
            return response;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

   
    public List<Movie> getPopularMovies() {
        System.out.println("Loading Popular Movies...");
        System.out.println("API Key: " + apiKey);
        System.out.println("API URL: " + apiUrl);

        String[] popularIds = {
            "tt0111161",  // Shawshank Redemption
            "tt0068646",  // The Godfather
            "tt0468569",  // The Dark Knight
            "tt1375666",  // Inception
            "tt0816692",  // Interstellar
            "tt0109830",  // Forrest Gump
            "tt0133093",  // The Matrix
            "tt0110912",  // Pulp Fiction
            "tt0120338",  // Titanic
            "tt4154796"   // Avengers Endgame
        };

        List<Movie> movies = new ArrayList<>();
        for (String imdbId : popularIds) {
            try {
                System.out.println("Fetching: " + imdbId);
                Movie movie = getMovieByImdbId(imdbId);
                if (movie != null) {
                    movies.add(movie);
                    System.out.println("✅ Got: " + movie.getTitle());
                }
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Total Movies: " + movies.size());
        return movies;
    }

 
    
    
    public List<Movie> searchMovies(String query) {
        List<Movie> movies = new ArrayList<>();
        try {
            String encodedQuery = query.replace(" ", "+");
            String url = apiUrl
                    + "?s=" + encodedQuery
                    + "&type=movie"
                    + "&apikey=" + apiKey;

            System.out.println("Search URL: " + url);
            String response = callAPI(url);
            System.out.println("Search Response: " + response);

            if (response == null || response.isEmpty()) {
                return movies;
            }

            JSONObject json = new JSONObject(response);

            if (json.optString("Response").equals("False")) {
                System.out.println("No Results: "
                    + json.optString("Error"));
                return movies;
            }

            if (!json.has("Search")) {
                return movies;
            }

            JSONArray search = json.getJSONArray("Search");
            for (int i = 0; i < search.length(); i++) {
                JSONObject obj = search.getJSONObject(i);
                Movie movie    = parseSearchResult(obj);
                movies.add(movie);
            }

        } catch (Exception e) {
            System.out.println("Search Error: " + e.getMessage());
            e.printStackTrace();
        }
        return movies;
    }

   
    
    
    public Movie getMovieByImdbId(String imdbId) {
        try {
            if (imdbId == null || imdbId.isEmpty()) {
                return null;
            }

            String url = apiUrl
                    + "?i=" + imdbId
                    + "&plot=full"
                    + "&apikey=" + apiKey;

            String response = callAPI(url);

            if (response == null || response.isEmpty()) {
                return null;
            }

            JSONObject json = new JSONObject(response);

            if (json.optString("Response").equals("False")) {
                return null;
            }

            return parseFullMovie(json);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

   
    
    
    public Movie getMovieByTitle(String title) {
        try {
            String encodedTitle = title.replace(" ", "+");
            String url = apiUrl
                    + "?t=" + encodedTitle
                    + "&type=movie"
                    + "&plot=full"
                    + "&apikey=" + apiKey;

            String response = callAPI(url);

            if (response == null || response.isEmpty()) {
                return null;
            }

            JSONObject json = new JSONObject(response);

            if (json.optString("Response").equals("False")) {
                return null;
            }

            return parseFullMovie(json);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

   
    
    
    public List<Movie> getMoviesByGenre(String genre) {
        String[] ids;

        switch (genre.toLowerCase()) {
            case "action":
                ids = new String[]{
                    "tt0468569",
                    "tt0133093",
                    "tt4154796",
                    "tt0848228",
                    "tt2911666"
                };
                break;
            case "comedy":
                ids = new String[]{
                    "tt0382932",
                    "tt0435761",
                    "tt1049413",
                    "tt2096673",
                    "tt0317219"
                };
                break;
            case "drama":
                ids = new String[]{
                    "tt0111161",
                    "tt0068646",
                    "tt0108052",
                    "tt0137523",
                    "tt0109830"
                };
                break;
            case "horror":
                ids = new String[]{
                    "tt0167404",
                    "tt0081505",
                    "tt0103064",
                    "tt0082971",
                    "tt0073195"
                };
                break;
            case "sci-fi":
                ids = new String[]{
                    "tt1375666",
                    "tt0133093",
                    "tt0816692",
                    "tt0083658",
                    "tt0209144"
                };
                break;
            case "romance":
                ids = new String[]{
                    "tt0109830",
                    "tt0120338",
                    "tt0407887",
                    "tt0482571",
                    "tt0172495"
                };
                break;
            default:
                ids = new String[]{
                    "tt1375666",
                    "tt0468569",
                    "tt0111161"
                };
        }

        List<Movie> movies = new ArrayList<>();
        for (String id : ids) {
            try {
                Movie movie = getMovieByImdbId(id);
                if (movie != null) {
                    movies.add(movie);
                }
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return movies;
    }


    
    
    private Movie parseSearchResult(JSONObject obj) {
        Movie movie = new Movie();

        movie.setImdbId(obj.optString("imdbID", ""));
        movie.setTitle(obj.optString("Title", "Unknown"));

        String year = obj.optString("Year", "0");
        try {
            if (year.length() >= 4) {
                movie.setReleaseYear(
                    Integer.parseInt(year.substring(0, 4))
                );
            }
        } catch (NumberFormatException e) {
            movie.setReleaseYear(0);
        }

        String poster = obj.optString("Poster", "N/A");
        movie.setPosterPath(
            (poster.equals("N/A") || poster.isEmpty())
            ? "https://via.placeholder.com/300x450?text=No+Poster"
            : poster
        );

        return movie;
    }

    
    
    private Movie parseFullMovie(JSONObject obj) {
        Movie movie = new Movie();

        movie.setImdbId(obj.optString("imdbID", ""));
        movie.setTitle(obj.optString("Title", "Unknown"));
        movie.setGenre(obj.optString("Genre", "N/A"));
        movie.setDirector(obj.optString("Director", "N/A"));
        movie.setActors(obj.optString("Actors", "N/A"));
        movie.setDescription(obj.optString("Plot", "N/A"));
        movie.setImdbRating(obj.optString("imdbRating", "N/A"));
        movie.setRated(obj.optString("Rated", "N/A"));
        movie.setRuntime(obj.optString("Runtime", "N/A"));
        movie.setLanguage(obj.optString("Language", "N/A"));
        movie.setCountry(obj.optString("Country", "N/A"));
        movie.setAwards(obj.optString("Awards", "N/A"));

        String poster = obj.optString("Poster", "N/A");
        movie.setPosterPath(
            (poster.equals("N/A") || poster.isEmpty())
            ? "https://via.placeholder.com/300x450?text=No+Poster"
            : poster
        );

        String year = obj.optString("Year", "0");
        try {
            if (year.length() >= 4) {
                movie.setReleaseYear(
                    Integer.parseInt(year.substring(0, 4))
                );
            }
        } catch (NumberFormatException e) {
            movie.setReleaseYear(0);
        }

        return movie;
    }

   
    
    
    private String callAPI(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = conn.getResponseCode();
        System.out.println("HTTP Code: " + responseCode);

        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.out.println("HTTP Error: " + responseCode);
            return null;
        }

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream())
        );

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        conn.disconnect();

        return response.toString();
    }
}