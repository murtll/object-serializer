package com.len;

import com.len.serializer.ObjectSerializer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Test test = new Test("aboba",
                12,
                new float[]{0f, 1.20f},
                List.of(new Test("kek",
                        1,
                        new float[]{},
                        List.of(),
                        List.of(1, 2),
                        new String[]{"n"})),
                List.of(3, 4, 5),
                new String[]{"lol", "nelol"});
        System.out.println(ObjectSerializer.toJson(test));
        System.out.println(ObjectSerializer.toXml(test));

        try {
            var fw1 = new FileWriter("test.json");
            fw1.write(ObjectSerializer.toJson(test));
            fw1.flush();
            fw1.close();
            var fw2 = new FileWriter("test.xml");
            fw2.write(ObjectSerializer.toXml(test));
            fw2.flush();
            fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
