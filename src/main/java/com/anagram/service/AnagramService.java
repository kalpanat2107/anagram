package com.anagram.service;

import com.anagram.exception.AnagramRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnagramService {

    public void checkAnagram(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new AnagramRuntimeException("File empty");
        } else {
            String content = new String(file.getBytes());
            String[] contentArr = content.split("\n");
            printAnagrams(contentArr);
        }
    }

    /**
     * Method to check whether the given array of string are anagrams.
     *
     * @param contentArr
     */
    private static void printAnagrams(String contentArr[])
    {
        HashMap<String, List<String> > anagramMap
                = new HashMap<>();
        // iterate over all words
        for (int i = 0; i < contentArr.length; i++) {

            // convert to char array, sort and
            // then convert to string
            String word = contentArr[i];
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            String newWord = new String(letters);
            // after sorting check if the work exists in the map
            if (anagramMap.containsKey(newWord)) {

                anagramMap.get(newWord).add(word);
            }
            else {
                // adding a word if it does not exist
                List<String> words = new ArrayList<>();
                words.add(word);
                anagramMap.put(newWord, words);
            }
        }

        // print all the anagrams where size >1
        // just print the non-anagrams having size = 1
        for (String s : anagramMap.keySet()) {
            List<String> output = anagramMap.get(s).stream().distinct().collect(Collectors.toList());
            if(output.size()>1){
                System.out.println(output);
            }
            if (output.size() == 1) {
                System.out.println(output);
            }
        }
    }
}
