from typing import List

def rabin_karp(text: str, pattern: str) -> List[int]:
    """
    Implementation of the Rabin-Karp algorithm for string matching.

    The Rabin-Karp algorithm uses a hash function to efficiently compare a pattern to substrings of a text.

    Args:
        text (str): The text to search in.
        pattern (str): The pattern to search for.

    Returns:
        list: A list of indices where the pattern was found in the text.
    """

    # Define a prime number for the hash function.
    prime = 31
    mod = 10 ** 9 + 9

    # Pre-calculated the powers of 31
    pows = [1] * max(len(text), len(pattern))
    for i in range(1, len(pows)):
        pows[i] = (pows[i - 1] * prime) % mod

    # Compute the hash value of the pattern.
    pattern_hash = 0
    for i in range(len(pattern)):
        pattern_hash = (pattern_hash + (ord(pattern[i]) * pows[i]) % mod) % mod
        
    # Compute the hash values of all substrings of the text that are the same length as the pattern.
    text_hashes = []

    text_hash = 0
    for i in range(len(pattern)):
        text_hash = (text_hash + (ord(text[i]) * pows[i]) % mod) % mod
    text_hashes.append(text_hash)

    for i in range(len(pattern), len(text)):
        text_hash = (text_hash - ord(text[i - len(pattern)])) % mod        
        text_hash = ((text_hash // prime) + (ord(text[i]) * pows[len(pattern) - 1]) % mod) % mod
        text_hashes.append(text_hash)

    # Check if any of the substrings have the same hash value as the pattern.
    indices = []
    for i in range(len(text_hashes)):
        if text_hashes[i] == pattern_hash:
            if text[i:i+len(pattern)] == pattern:
                indices.append(i)

    return indices

# Example usage
text = "Hello, world!"
pattern = "world"
pos = rabin_karp(text, pattern)

if not pos:
    print("Pattern not found")
else:
    print("Pattern found at position", ", ".join(map(str, pos)))