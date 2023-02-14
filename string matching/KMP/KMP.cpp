#include <iostream>
#include <string>
#include <vector>

using namespace std;

// This function computes the partial match table for the given pattern
vector<int> kmpTable(string pattern) {
    int m = pattern.length();
    vector<int> pi(m);

    int k = 0;
    for (int i = 1; i < m; i++) {
        // This loop updates the partial match table based on the current character of the pattern
        // If there is a partial match, it updates the table with the length of the matching prefix
        // If there is no partial match, it backtracks to the longest prefix that has a partial match
        while (k > 0 && pattern[k] != pattern[i]) {
            k = pi[k-1];
        }
        if (pattern[k] == pattern[i]) {
            k++;
        }
        pi[i] = k;
    }
    return pi;
}

// This function searches for the given pattern in the given text using the KMP algorithm
int kmpSearch(string text, string pattern) {
    int n = text.length();
    int m = pattern.length();

    // Compute the partial match table for the pattern
    vector<int> pi = kmpTable(pattern);

    int i = 0, j = 0;
    while (i < n) {
        // This loop compares each character of the text and the pattern until a match is found or the end of the text is reached
        if (text[i] == pattern[j]) {
            i++;
            j++;
            if (j == m) {
                return i - j; // If the entire pattern is matched, return the starting position of the match
            }
        } else if (j > 0) {
            j = pi[j-1]; // If there is a partial match, backtrack to the longest prefix that has a partial match
        } else {
            i++; // If there is no partial match, move to the next character in the text
        }
    }
    return -1; // If the pattern is not found, return -1
}

int main() {
    string text = "Hello, world!";
    string pattern = "world";
    int pos = kmpSearch(text, pattern);
    if (pos == -1) {
        cout << "Pattern not found" << endl;
    } else {
        cout << "Pattern found at position " << pos << endl;
    }
    return 0;
}
