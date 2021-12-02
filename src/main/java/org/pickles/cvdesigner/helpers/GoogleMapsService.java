package org.pickles.cvdesigner.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.ImageResult;
import com.google.maps.StaticMapsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class GoogleMapsService {
    private static final JSONParser parser = new JSONParser();

    private static String pathToTargetResources;

    private static String getApiKey() throws IOException, ParseException {
        String path = Objects.requireNonNull(Main.class.getResource("api-key.json")).getPath();
        path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        path = new File(path).getPath();
        pathToTargetResources = new File(path).getParent();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(path));
        return (String) jsonObject.get("key");
    }

    private static JSONObject getLatLngFromGeocodingResultJson(String geocodingResult) throws ParseException {
        JSONObject jsonObject = (JSONObject) parser.parse(geocodingResult);
        return (JSONObject) jsonObject.get("location");
    }

    // TODO: add json parsing of the stored address values - awaiting for storage creation
    public static String getStaticMapPath() throws IOException, ParseException, InterruptedException, ApiException {
        String apiKey = getApiKey();

        GeoApiContext geoApiContext = new GeoApiContext.Builder().apiKey(apiKey).build();
        GeocodingResult[] geocodingResults = GeocodingApi
                .geocode(geoApiContext, "Polska, Łódź, ul. Lucjana Rydla 7a").await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JSONObject latLngJson = getLatLngFromGeocodingResultJson(gson.toJson(geocodingResults[0].geometry));
        LatLng latLng = new LatLng((Double) latLngJson.get("lat"), (Double) latLngJson.get("lng"));

        StaticMapsRequest.Markers markers = new StaticMapsRequest.Markers();
        markers.addLocation(latLng);
        markers.size(StaticMapsRequest.Markers.MarkersSize.small);

        StaticMapsRequest staticMapsRequest = new StaticMapsRequest(geoApiContext);
        ImageResult imageResult = staticMapsRequest
                .center(latLng)
                .maptype(StaticMapsRequest.StaticMapType.hybrid)
                .markers(markers)
                .visible(latLng)
                .size(new Size(1400, 1400))
                .scale(2)
                .zoom(18)
                .await();

        geoApiContext.shutdown();

        ByteArrayInputStream bis = new ByteArrayInputStream(imageResult.imageData);
        BufferedImage image = ImageIO.read(bis);
        File imageFile = new File(pathToTargetResources, "map.png");
        ImageIO.write(image, "png", imageFile);

        return imageFile.getPath();
    }
}
