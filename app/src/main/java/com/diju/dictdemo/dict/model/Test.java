package com.diju.dictdemo.dict.model;

import java.io.BufferedReader;
import java.io.FileReader;

public class Test {
    public static void main(String[] args) throws Exception {
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader("app//src//main//assets//dict.json"));
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        String jsonData = sb.toString();
        Dict dict = Dict.parseJson(jsonData);

    }
}
