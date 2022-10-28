**Anagram Implementation:**

Added a new API endpoint to upload the test file to group words in the files into anagrams. Added Controller and Service layer to have the business logis separate.

**Controller:**

    1) Added a POST endpoint to upload a file with list of words.
    2) Call the service layer to check the logic to group the anagrams and print.
**Service:**

    1) Read the file to group the anagrams.
    2) Iterate through all the words in the file and group anagrams/non-anagrams.
    3) Approach followed is as below:
        a) Convert file content into string array
        b) Iterate through the words in the string array.
        c) Sort the word and check if the sorted word already exists in a map(map to store the word groups)
        d) If not exists then create a new group(list) to add the word into the map.
        e) If exists then add the new word to the existing group(list).
        f) Iterate over the output map to get the group of anagrams and non-anagrams
        g) if the group size is >1 then the group is anagram group and if size is 1 then the word is non-anagram.
        h) Print anagrams and non-anagrams.

        


**How to run the code?**

Start the application and hit the below API endpoint to test the code.

http://localhost:8080/api/v1/action/checkAnagram/upload

Upload example/test files as in the below screenshot.

![img.png](img.png)

Time Complexity:O(M+N)

I have chosen Map and List as data structures to get the group anagrams.
Map is used to check whether the sorted word already exists to confirm the word belongs to anagram group.
List is used to store the group of anagrams.

If more time given than I would add more testing and simplify the logic where I have used two loops and see whether I can do it with single loop.
Another approach could be to read file from a location and check for anagrams instead of an API endpoint.


