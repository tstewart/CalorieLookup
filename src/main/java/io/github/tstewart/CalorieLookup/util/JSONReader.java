package io.github.tstewart.CalorieLookup.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

// The following code is owned by Roland Illig from StackOverflow Question
// URL: https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
public enum JSONReader {
    ;

    JSONReader() {
    }

    private static String readAll(final Reader rd) throws IOException {
        final StringBuilder sb = new StringBuilder();
        int cp;
        while (-1 != (cp = rd.read())) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(final String url) throws IOException, JSONException {
        try (final InputStream is = new URL(url).openStream()) {
            final BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            final String jsonText = JSONReader.readAll(rd);
            return new JSONObject(jsonText);
        }
    }

}
