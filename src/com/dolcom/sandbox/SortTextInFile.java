package com.dolcom.sandbox;

import java.io.*;
import java.util.*;

public class SortTextInFile {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            List<String> content = new ArrayList<>();
            Map<String, Integer> map = new HashMap<>();

            String line = reader.readLine();
            while (line != null) {
                content.add(line);
                map.put(line, map.get(line) != null ? map.get(line) + 1 : 1);
                line = reader.readLine();
            }

            switch (args[1]) {
                case "amount":
                    content.sort((a, b) -> a.length() > b.length() ? 1 : -1);
                    break;
                case "wordnum":
                    int wordNum = args[2] != null? Integer.parseInt(args[2]) : 0;
                    content.sort((a, b) -> a.split(" ")[wordNum].charAt(0) > b.split(" ")[wordNum].charAt(0) ? 1 : -1);
                    break;
                default:
                    content.sort((a, b) -> a.charAt(0) > b.charAt(0) ? 1 : -1);
                    break;
            }

            FileWriter file = new FileWriter("./result.txt");
            for (String s : content) {
                file.write(s + " " + map.get(s) + "\n");
            }
            file.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
