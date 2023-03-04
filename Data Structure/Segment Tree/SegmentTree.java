package dataStructure;

import java.util.*;

public class SegmentTree {

    static void buildTree(List<Integer> arr, List<Integer> tree, int node, int start, int end) {
        if (start == end) {
            // Leaf node will have a single element
            tree.set(node, arr.get(start));
        } else {
            int mid = (start + end) / 2;
            // Recurse on the left child
            buildTree(arr, tree, 2 * node, start, mid);
            // Recurse on the right child
            buildTree(arr, tree, 2 * node + 1, mid + 1, end);
            // Internal node will have the sum of both of its children
            tree.set(node, tree.get(2 * node) + tree.get(2 * node + 1));
        }
    }

    static int queryTree(List<Integer> tree, int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            // Range represented by a node is completely outside the given range
            return 0;
        }
        if (left <= start && end <= right) {
            // Range represented by a node is completely inside the given range
            return tree.get(node);
        }
        // Range represented by a node is partially inside and partially outside the given range
        int mid = (start + end) / 2;
        int sumLeft = queryTree(tree, 2 * node, start, mid, left, right);
        int sumRight = queryTree(tree, 2 * node + 1, mid + 1, end, left, right);
        return sumLeft + sumRight;
    }

    static void updateTree(List<Integer> tree, int node, int start, int end, int index, int value) {
        if (start == end) {
            // Leaf node
            tree.set(node, value);
        } else {
            int mid = (start + end) / 2;
            if (index <= mid) {
                // Recurse on the left child
                updateTree(tree, 2 * node, start, mid, index, value);
            } else {
                // Recurse on the right child
                updateTree(tree, 2 * node + 1, mid + 1, end, index, value);
            }
            // Internal node will have the sum of both of its children
            tree.set(node, tree.get(2 * node) + tree.get(2 * node + 1));
        }
    }

    public static void main(String[] args) {
        // Input array
        List<Integer> arr = Arrays.asList(1, 3, 5, 7, 9, 11);
        int n = arr.size();

        // Height of the segment tree
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        // Maximum size of the segment tree
        int max_size = 2 * (int) Math.pow(2, h) - 1;

        // Create and initialize the segment tree
        List<Integer> tree = new ArrayList<>(Collections.nCopies(max_size, 0));
        buildTree(arr, tree, 1, 0, n - 1);

        // Query the segment tree for the sum of elements in the range [1, 3]
        int sum = queryTree(tree, 1, 0, n - 1, 1, 3);
        System.out.println("Sum of elements in range [1, 3]: " + sum);

        // Update the value at index 2 to 6
        updateTree(tree, 1, 0, n, 2, 6);

        // Query the segment tree for the sum of elements in the range [1, 5]
        sum = queryTree(tree, 1, 0, n - 1, 1, 5);
        System.out.println("Sum of elements in range [1, 5]: " + sum);
    }
}