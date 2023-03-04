1. Set a bit at a particular position: To set a bit at position i in an integer x, we can use the bitwise OR operator | with the bit mask `1 << i`. For example, to set the 3rd bit of x to 1, we can use the expression `x |= 1 << 3`.

2. Clear a bit at a particular position: To clear a bit at position i in an integer x, we can use the bitwise AND operator & with the bit mask `~(1 << i)`. For example, to clear the 3rd bit of x, we can use the expression `x &= ~(1 << 3)`.

3. Toggle a bit at a particular position: To toggle a bit at position i in an integer x, we can use the bitwise XOR operator ^ with the bit mask `1 << i`. For example, to toggle the 3rd bit of x, we can use the expression `x ^= 1 << 3`.

4. Check if a bit is set at a particular position: To check if a bit is set at position i in an integer x, we can use the bitwise AND operator & with the bit mask `1 << i`. If the result is non-zero, then the bit is set. For example, to check if the 3rd bit of x is set, we can use the expression `(x & (1 << 3)) != 0`.

5. Extract the nth bit of a number: To extract the nth bit of a number x, we can use the bitwise AND operator & with the bit mask `1 << n`, and then right-shift the result by n bits. For example, to extract the 3rd bit of x, we can use the expression `(x & (1 << 3)) >> 3`.

6. Set all bits of a number: To set all bits of an unsigned integer x, we can use the bitwise OR operator | with the bit mask 0xFFFFFFFF. For example, to set all bits of a 32-bit integer x, we can use the expression `x |= 0xFFFFFFFF`.

7. Clear all bits of a number: To clear all bits of an unsigned integer x, we can use the bitwise AND operator & with the bit mask 0x00000000. For example, to clear all bits of a 32-bit integer x, we can use the expression `x &= 0x00000000`.

8. Check if a number is a power of two: A number x is a power of two if and only if `x & (x - 1) == 0`. This is because a power of two has only one bit set, and subtracting one from it sets all lower bits to 1 and leaves the highest bit unchanged. For example, to check if a number x is a power of two, we can use the expression `(x & (x - 1)) == 0`.

9. Check if a number is even or odd: To check if a number is even or odd, you can use the AND operator (&) with 1. If the result is 0, the number is even. Otherwise, it's odd. For example, `(n & 1) == 0` checks if a number n is even.

10. Get the absolute value of a number: To get the absolute value of a number, you can use the AND operator (&) with the complement of the sign bit. For example, to get the absolute value of a number n, you can do `(n ^ (n >> 31)) - (n >> 31)`.

11. Swap two variables without using a temporary variable: To swap two variables a and b without using a temporary variable, you can use the XOR operator (^). For example, to swap a and b, you can do `a ^= b, b ^= a, a ^= b`.

12. Finding the maximum or minimum of two numbers: To find the maximum of two numbers `a` and `b` use the expression, `b & ((a - b) >> 31) | a & (~(a - b) >> 31)`

Here's how it works:

- `(a - b) >> 31` gives a 32-bit number with all bits set if a is less than b, and all bits unset otherwise.
- `~(a - b) >> 31` gives the opposite result.
- `b & ((a - b) >> 31)` is zero if a is greater than or equal to b, and b otherwise.
- `a & (~(a - b) >> 31)` is zero if a is less than b, and a otherwise.
- The bitwise OR operator | combines the two results to give the maximum of a and b.

Similarly, to find the minimum we use this expression, `a & ((a - b) >> 31) | b & (~(a - b) >> 31)`


