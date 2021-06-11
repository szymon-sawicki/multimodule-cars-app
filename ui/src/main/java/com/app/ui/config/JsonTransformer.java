package com.app.ui.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String render(Object o) throws Exception {
        return gson.toJson(o);
    }
}
