package stringMatching;

import java.util.ArrayList;

public class RabinKarp {

    public static ArrayList<Integer> rabinKarp(String text, String pattern) {
        // Define a prime number for the hash function.
        long prime = 31;
        long mod = (long) 1e9 + 9;

        // Pre-calculated the powers of 31
        ArrayList<Long> pows = new ArrayList<>();
        pows.add(1L);
        for(int i = 1; i < Math.max(text.length(), pattern.length()); i++) {
            pows.add((pows.get(i - 1) * prime) % mod);
        }

        // Compute the hash value of the pattern.
        long patternHash = 0;
        int pLen = pattern.length();
        for (int i = 0; i < pLen; i++) {
            patternHash = (patternHash + (pattern.charAt(i) * pows.get(i)) % mod) % mod;
        }

        // Compute the hash values of all substrings of the text that are the same length as the pattern.
        ArrayList<Long> textHashes = new ArrayList<>();
        long textHash = 0;
        for (int i = 0; i < pLen; i++) {
            textHash = (textHash + (text.charAt(i) * pows.get(i)) % mod) % mod;
        }
        textHashes.add(textHash);
        for (int i = pLen; i < text.length(); i++) {
            textHash -= text.charAt(i - pLen);
            textHash = ((textHash / prime) + (text.charAt(i) * pows.get(pLen - 1)) % mod) % mod;
            textHashes.add(textHash);
        }

        // Check if any of the substrings have the same hash value as the pattern.
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < textHashes.size(); i++) {
            if (textHashes.get(i) == patternHash) {
                if (text.substring(i, i + pattern.length()).equals(pattern)) {
                    indices.add(i);
                }
            }
        }

        return indices;
    }

    public static void main(String[] args) {
        String text = "Hello, world!";
        String pattern = "world";
        ArrayList<Integer> pos = rabinKarp(text, pattern);

        if (pos.isEmpty()) {
            System.out.println("Pattern not found");
        } else {
            System.out.print("Pattern found at position ");
            for (int i = 0; i < pos.size(); i++) {
                System.out.print(pos.get(i));
                if (i != pos.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}