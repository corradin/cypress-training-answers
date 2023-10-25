package org.tests;

import com.microsoft.playwright.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;

public class ApiTest {

    // Shared between all tests in this class.
    private Playwright playwright;
    private APIRequestContext request;

    void createPlaywright() {
        playwright = Playwright.create();
    }

    void createAPIRequestContext() {
        Map<String, String> headers = new HashMap<>();

        request = playwright.request().newContext();

        // Alternatively, set common parameters for the request context:
        // request = playwright.request().newContext(new APIRequest.NewContextOptions()
        // // All requests we send go to this API endpoint.
        // .setBaseURL("http://localhost:8081")
        // .setExtraHTTPHeaders(headers));
    }

    @BeforeSuite
    void beforeAll() {
        createPlaywright();
        createAPIRequestContext();
    }

    void disposeAPIRequestContext() {
        if (request != null) {
            request.dispose();
            request = null;
        }
    }

    void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }

    @AfterSuite
    void afterAll() {
        disposeAPIRequestContext();
        closePlaywright();
    }

    @Test
    void retrieveTheSongsFromTheAPI() throws JSONException {
        APIResponse issues = request.get("http://localhost:8081/songs");
        Assert.assertTrue(issues.ok());

        // Another option is to deserialise to a type safe data with a library like
        // Jackson, then do normal asserts on the fields
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("title", "Nevermind");
        map.put("artist", "Nirvana");
        map.put("genre", "Alternative Rock");
        map.put("album", "Nevermind");
        map.put("albumImageUrl",
                "https://is3-ssl.mzstatic.com/image/thumb/Features/d0/cc/62/dj.nanioukp.jpg/268x0w.jpg");
        map.put("youtubeId", "m-ofL_3EZyE");
        map.put("lyrics", "");
        map.put("tab", "");
        map.put("createdAt", "2018-02-13T12:56:24.432Z");

        JSONObject expected = new JSONObject(map);

        JSONArray actual = new JSONArray(issues.text());

        JSONAssert.assertEquals(expected, actual.getJSONObject(0), JSONCompareMode.LENIENT);
    }

}