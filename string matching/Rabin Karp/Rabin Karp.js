function rabin_karp(text, pattern) {
    // Define a prime number for the hash function.
    const prime = 31;
    const mod = 1e9 + 9;

    // Pre-calculated the powers of 31
    const pows = new Array(Math.max(text.length, pattern.length));
    pows[0] = 1;
    for (let i = 1; i < pows.length; i++) {
        pows[i] = (pows[i - 1] * prime) % mod;
    }

    // Compute the hash value of the pattern.
    let pattern_hash = 0;
    const pLen = pattern.length;
    for (let i = 0; i < pLen; i++) {
        pattern_hash = (pattern_hash + (pattern.charCodeAt(i) * pows[i]) % mod) % mod;
    }

    // Compute the hash values of all substrings of the text that are the same length as the pattern.
    const text_hashes = [];

    let text_hash = 0;
    for (let i = 0; i < pLen; i++) {
        text_hash = (text_hash + (text.charCodeAt(i) * pows[i]) % mod) % mod;
    }
    text_hashes.push(text_hash);

    for (let i = pLen; i < text.length; i++) {
        text_hash -= text.charCodeAt(i - pLen);
        text_hash = ((text_hash / prime) + (text.charCodeAt(i) * pows[pLen - 1]) % mod) % mod;
        text_hashes.push(text_hash);
    }

    // Check if any of the substrings have the same hash value as the pattern.
    const indices = [];
    for (let i = 0; i < text_hashes.length; i++) {
        if (text_hashes[i] == pattern_hash) {
            if (text.substr(i, pattern.length) == pattern) {
                indices.push(i);
            }
        }
    }

    return indices;
}

const text = "Hello, world!";
const pattern = "world";
const pos = rabin_karp(text, pattern);

if (pos.length === 0) {
    console.log("Pattern not found");
} else {
    console.log("Pattern found at position " + pos.join(", "));
}