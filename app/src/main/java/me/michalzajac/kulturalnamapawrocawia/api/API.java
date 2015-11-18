package me.michalzajac.kulturalnamapawrocawia.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import me.michalzajac.kulturalnamapawrocawia.models.Event;
import me.michalzajac.kulturalnamapawrocawia.models.POI;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

public class API {

    private static APIInterface apiInterface;
    private static String url = "http://frelia.org:3000";

    public static APIInterface getClient() {
        if (apiInterface == null) {
            Interceptor acceptJSON = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                    return chain.proceed(request);
                }
            };

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.interceptors().add(acceptJSON);

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
            apiInterface = client.create(APIInterface.class);
        }
        return apiInterface;
    }

    public interface APIInterface {

        // POI
        @GET("/pois")
        Call<List<POI>> getAllPOIs();

        @GET("/pois/{poi_id}")
        Call<POI> getPOI(@Path("poi_id") Integer poi_id);

        // Events
        @GET("/events")
        Call<List<Event>> getAllEvents();

        @GET("/pois/{poi_id}/events/{event_id}")
        Call<Event> getEvent(@Path("poi_id") Integer poi_id, @Path("event_id") Integer event_id);

        // Routes
        //@GET("/routes")
        //Call<List<Route>> getAllRoutes();
        //
        //@GET("/routes/{route_id}")
        //Call<Route> getRoute(@Path("route_id") Integer route_id);
    }

}
