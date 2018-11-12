package org.touchbit.testrail4j.helpful;

import java.util.HashMap;
import java.util.Map;

public class MockResponse {

    public Map<String, String> headers = new HashMap<>();
    public String body = "";
    public Integer code = 200;

}