function kmpTable(pattern) {
  // Create a table to store the values of the longest proper prefix that is also a suffix of the substring for each position in the pattern.
  const table = new Array(pattern.length).fill(0);

  // Initialize the left and right pointers to zero and one, respectively.
  let left = 0;
  let right = 1;

  // Iterate over the pattern from left to right.
  while (right < pattern.length) {
    // If the character at the right pointer is equal to the character at the left pointer, increment both pointers and set the value of the table at the right pointer to the value of the left pointer.
    if (pattern[right] === pattern[left]) {
      left++;
      table[right] = left;
      right++;
    } else {
      // If the characters are not equal, move the left pointer back to the position in the table corresponding to the previous longest proper prefix that is also a suffix, and continue checking for a match.
      if (left !== 0) {
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

function kmpSearch(text, pattern) {
  // Create a table to store the values of the longest proper prefix that is also a suffix of the substring for each position in the pattern.
  const table = kmpTable(pattern);
  const matches = [];

  let i = 0; // index for text
  let j = 0; // index for pattern

  while (i < text.length) {
    if (pattern[j] === text[i]) {
      i++;
      j++;
    }

    if (j === pattern.length) {
      // Found a match
      matches.push(i - j);
      j = table[j - 1];
    } else if (i < text.length && pattern[j] !== text[i]) {
      if (j !== 0) {
        j = table[j - 1];
      } else {
        i++;
      }
    }
  }

  return matches;
}

const text = "Hello, world!";
const pattern = "world";
const pos = kmpSearch(text, pattern);

if (pos == -1) {
	console.log("Pattern not found");
} else {
	console.log("Pattern found at position", pos);
}