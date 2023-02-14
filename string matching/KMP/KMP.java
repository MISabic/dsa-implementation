package stringMatching;

public class KMP {
	
	public static int[] kmpTable(String pattern) {
	    // Create an array to store the values of the longest proper prefix that is also a suffix of the substring for each position in the pattern.
	    int[] table = new int[pattern.length()];

	    // Initialize the left and right pointers to zero and one, respectively.
	    int left = 0;
	    int right = 1;

	    // Iterate over the pattern from left to right.
	    while (right < pattern.length()) {
	        // If the character at the right pointer is equal to the character at the left pointer, increment both pointers and set the value of the table at the right pointer to the value of the left pointer.
	        if (pattern.charAt(right) == pattern.charAt(left)) {
	            left++;
	            table[right] = left;
	            right++;
	        } else {
	            // If the characters are not equal, move the left pointer back to the position in the table corresponding to the previous longest proper prefix that is also a suffix, and continue checking for a match.
	            if (left != 0) {
	                left = table[left - 1];
	            } else {
	                // If there is no previous longest proper prefix that is also a suffix, set the value of the table at the right pointer to zero and move the right pointer forward.
	                table[right] = 0;
	                right++;
	            }
	        }
	    }

	    return table;
	}

	public static int kmpSearch(String text, String pattern) {
	    int[] table = kmpTable(pattern);

	    // Initialize the text and pattern pointers to zero.
	    int textIndex = 0;
	    int patternIndex = 0;

	    // Iterate over the text.
	    while (textIndex < text.length()) {
	        // If the characters match, increment both pointers.
	        if (text.charAt(textIndex) == pattern.charAt(patternIndex)) {
	            textIndex++;
	            patternIndex++;
	        }

	        // If the pattern is found, return the index of the start of the match.
	        if (patternIndex == pattern.length()) {
	            return textIndex - patternIndex;
	        }

	        // If the characters do not match, move the pattern pointer back to the position in the table corresponding to the previous longest proper prefix that is also a suffix.
	        if (textIndex < text.length() && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
	            if (patternIndex != 0) {
	                patternIndex = table[patternIndex - 1];
	            } else {
	                textIndex++;
	            }
	        }
	    }

	    // If the pattern is not found, return -1.
	    return -1;
	}


	public static void main(String[] args) {
		String text = "Hello, world!";
	    String pattern = "world";
	    int pos = kmpSearch(text, pattern);
	    
	    if (pos == -1) {
	    	System.out.println("Pattern not found");
	    } else {
	    	System.out.println("Pattern found at position " + pos);
	    }
	}
}
