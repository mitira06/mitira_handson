package com.example.app.mockito.basic;

public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        String result = externalApi.getData("default");
        externalApi.log("fetchData called with: default");
        return result;
    }

    public String fetchData(String param) {
        return externalApi.getData(param);
    }
}
