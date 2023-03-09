package com.epam.mjc.nio;


import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


public class FileReader {

    public Profile getDataFromFile(File file) {
        List <String> list = new ArrayList<>();

        try (Stream<String> stream = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8).stream()) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

       String[] dataset = getCorrectValue(list);


        Profile profile = new Profile();
        profile.setName(dataset[0].trim());
        profile.setAge(parseInt(dataset[1].trim()));
        profile.setEmail(dataset[2].trim());
        profile.setPhone(parseLong(dataset[3].trim()));


        return profile;
    }



    private String [] getCorrectValue(List<String>dataset) {

        String[] setValueForProfile = new String[dataset.size()];
        for (int i = 0; i < dataset.size(); i++) {
            setValueForProfile[i] = dataset.get(i).replaceAll("^[^\\s]+", "");

        }
        return setValueForProfile;
    }

}
