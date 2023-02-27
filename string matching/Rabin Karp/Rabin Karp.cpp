#include <iostream>
#include <vector>
#include <string>
#include <cmath>

using namespace std;

vector<int> rabin_karp(string text, string pattern) {
    // Define a prime number for the hash function.
    long long prime = 31;
    long long mod = 1e9 + 9;

    // Pre-calculated the powers of 31
    vector<long long> pows(max(text.size(), pattern.size()));
    pows[0] = 1;
    for(int i = 1; i < pows.size(); i++) {
        pows[i] = (pows[i - 1] * prime) % mod;
    }

    // Compute the hash value of the pattern.
    long long pattern_hash = 0, pLen = pattern.length();
    for (int i = 0; i < pLen; i++) {
        pattern_hash = (pattern_hash + (pattern[i] * pows[i]) % mod) % mod;
    }

    // Compute the hash values of all substrings of the text that are the same length as the pattern.
    vector<long long> text_hashes;

    long long text_hash = 0;
    for (int i = 0; i < pLen; i++) {
        text_hash = (text_hash + (text[i] * pows[i]) % mod) % mod;
    }
    text_hashes.push_back(text_hash);

    for (int i = pLen; i < text.length(); i++) {
        text_hash -= text[i - pLen];
        text_hash = ((text_hash / prime) + (text[i] * pows[pLen - 1]) % mod) % mod;
        text_hashes.push_back(text_hash);
    }

    // Check if any of the substrings have the same hash value as the pattern.
    vector<int> indices;
    for (int i = 0; i < text_hashes.size(); i++) {
        if (text_hashes[i] == pattern_hash) {
            if (text.substr(i, pattern.length()) == pattern) {
                indices.push_back(i);
            }
        }
    }

    return indices;
}

int main() {
    string text = "Hello, world!";
    string pattern = "world";
    vector<int> pos = rabin_karp(text, pattern);

    if (pos.empty()) {
        cout << "Pattern not found" << endl;
    } else {
        cout << "Pattern found at position ";

        for(int i = 0; i < pos.size(); i++) {
            cout << pos[i];
            if(i != pos.size() - 1) cout << ", ";
        }
        cout << endl;
    }
    return 0;
}
