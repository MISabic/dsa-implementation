#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

void build_tree(vector<int>& arr, vector<int>& tree, int node, int start, int end) {
    if (start == end) {
        // Leaf node will have a single element
        tree[node] = arr[start];
    } else {
        int mid = (start + end) / 2;
        // Recurse on the left child
        build_tree(arr, tree, 2 * node, start, mid);
        // Recurse on the right child
        build_tree(arr, tree, 2 * node + 1, mid + 1, end);
        // Internal node will have the sum of both of its children
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }
}

int query_tree(vector<int>& tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
        // Range represented by a node is completely outside the given range
        return 0;
    }
    if (left <= start && end <= right) {
        // Range represented by a node is completely inside the given range
        return tree[node];
    }
    // Range represented by a node is partially inside and partially outside the given range
    int mid = (start + end) / 2;
    int sum_left = query_tree(tree, 2 * node, start, mid, left, right);
    int sum_right = query_tree(tree, 2 * node + 1, mid + 1, end, left, right);
    return sum_left + sum_right;
}

void update_tree(vector<int>& tree, int node, int start, int end, int index, int value) {
    if (start == end) {
        // Leaf node
        tree[node] = value;
    } else {
        int mid = (start + end) / 2;
        if (index <= mid) {
            // Recurse on the left child
            update_tree(tree, 2 * node, start, mid, index, value);
        } else {
            // Recurse on the right child
            update_tree(tree, 2 * node + 1, mid + 1, end, index, value);
        }
        // Internal node will have the sum of both of its children
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }
}

int main() {
    // Input array
    vector<int> arr = {1, 3, 5, 7, 9, 11};
    int n = arr.size();

    // Height of the segment tree
    int h = (int)ceil(log2(n));
    // Maximum size of the segment tree
    int max_size = 2 * (int)pow(2, h) - 1;

    // Create and initialize the segment tree
    vector<int> tree(max_size);
    build_tree(arr, tree, 1, 0, n - 1);

    // Query the segment tree for the sum of elements in the range [1, 3]
    int sum = query_tree(tree, 1, 0, n - 1, 1, 3);
    cout << "Sum of elements in range [1, 3]: " << sum << endl;

    // Update the value at index 2 to 6
    update_tree(tree, 1, 0, n, 2, 6);

    // Query the segment tree for the sum of elements in the range [1, 5]
    sum = query_tree(tree, 1, 0, n - 1, 1, 5);
    cout << "Sum of elements in range [1, 5]: " << sum << endl;
}

