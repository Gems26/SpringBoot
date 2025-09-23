package com.spring.flames;

import org.springframework.stereotype.Service;

@Service
public class FlamesService {
   
    public String checkFlames(String name1,String name2){
        name1 = name1.toLowerCase().replaceAll("\\s+", "");
        name2 = name2.toLowerCase().replaceAll("\\s+", "");
        StringBuilder sb1 = new StringBuilder(name1);
        StringBuilder sb2 = new StringBuilder(name2);

        for (int i = 0; i < sb1.length(); i++) {
            char c = sb1.charAt(i);
            int index = sb2.indexOf(String.valueOf(c));
            if (index != -1) {
                sb1.deleteCharAt(i);
                sb2.deleteCharAt(index);
                i--; // adjust index after deletion
            }
        }

        int count = sb1.length() + sb2.length();

        String flames = "FLAMES";
        StringBuilder result = new StringBuilder(flames);

        int pos = 0;
        while (result.length() > 1) {
            pos = (pos + count - 1) % result.length();
            result.deleteCharAt(pos);
        }

        char finalChar = result.charAt(0);
        String meaning;

        switch (finalChar) {
            case 'F': meaning = "Friends"; break;
            case 'L': meaning = "Love"; break;
            case 'A': meaning = "Affection"; break;
            case 'M': meaning = "Marriage"; break;
            case 'E': meaning = "Enemy"; break;
            case 'S': meaning = "Sister"; break;
            default:  meaning = "Unknown"; break;
        }
        return meaning;
    }
}
