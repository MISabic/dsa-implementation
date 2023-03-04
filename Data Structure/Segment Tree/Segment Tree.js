function buildTree(arr, tree, node, start, end) {
    if (start === end) {
        // Leaf node will have a single element
        tree[node] = arr[start];
    } else {
        const mid = Math.floor((start + end) / 2);
        // Recurse on the left child
        buildTree(arr, tree, 2 * node, start, mid);
        // Recurse on the right child
        buildTree(arr, tree, 2 * node + 1, mid + 1, end);
        // Internal node will have the sum of both of its children
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }
}

function queryTree(tree, node, start, end, left, right) {
    if (right < start || end < left) {
        // Range represented by a node is completely outside the given range
        return 0;
    }
    if (left <= start && end <= right) {
        // Range represented by a node is completely inside the given range
        return tree[node];
    }
    // Range represented by a node is partially inside and partially outside the given range
    const mid = Math.floor((start + end) / 2);
    const sumLeft = queryTree(tree, 2 * node, start, mid, left, right);
    const sumRight = queryTree(tree, 2 * node + 1, mid + 1, end, left, right);
    return sumLeft + sumRight;
}

function updateTree(tree, node, start, end, index, value) {
    if (start === end) {
        // Leaf node
        tree[node] = value;
    } else {
        const mid = Math.floor((start + end) / 2);
        if (index <= mid) {
            // Recurse on the left child
            updateTree(tree, 2 * node, start, mid, index, value);
        } else {
            // Recurse on the right child
            updateTree(tree, 2 * node + 1, mid + 1, end, index, value);
        }
        // Internal node will have the sum of both of its children
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }
}

// Input array
const arr = [1, 3, 5, 7, 9, 11];
const n = arr.length;

// Height of the segment tree
const h = Math.ceil(Math.log2(n));
// Maximum size of the segment tree
const maxSize = 2 * Math.pow(2, h) - 1;

// Create and initialize the segment tree
const tree = new Array(maxSize).fill(0);
buildTree(arr, tree, 1, 0, n - 1);

// Query the segment tree for the sum of elements in the range [1, 3]
const sum = queryTree(tree, 1, 0, n - 1, 1, 3);
console.log(`Sum of elements in range [1, 3]: ${sum}`);

// Update the value at index 2 to 6
updateTree(tree, 1, 0, n, 2, 6);

// Query the segment tree for the sum of elements in the range [1, 5]
const updatedSum = queryTree(tree, 1, 0, n - 1, 1, 5);
console.log(`Sum of elements in range [1, 5]: ${updatedSum}`);