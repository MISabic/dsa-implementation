import math

def build_tree(arr, tree, node, start, end):
    if start == end:
        # Leaf node will have a single element
        tree[node] = arr[start]
    else:
        mid = (start + end) // 2
        # Recurse on the left child
        build_tree(arr, tree, 2 * node, start, mid)
        # Recurse on the right child
        build_tree(arr, tree, 2 * node + 1, mid + 1, end)
        # Internal node will have the sum of both of its children
        tree[node] = tree[2 * node] + tree[2 * node + 1]

def query_tree(tree, node, start, end, left, right):
    if right < start or end < left:
        # Range represented by a node is completely outside the given range
        return 0
    if left <= start and end <= right:
        # Range represented by a node is completely inside the given range
        return tree[node]
    # Range represented by a node is partially inside and partially outside the given range
    mid = (start + end) // 2
    sum_left = query_tree(tree, 2 * node, start, mid, left, right)
    sum_right = query_tree(tree, 2 * node + 1, mid + 1, end, left, right)
    return sum_left + sum_right

def update_tree(tree, node, start, end, index, value):
    if start == end:
        # Leaf node
        tree[node] = value
    else:
        mid = (start + end) // 2
        if index <= mid:
            # Recurse on the left child
            update_tree(tree, 2 * node, start, mid, index, value)
        else:
            # Recurse on the right child
            update_tree(tree, 2 * node + 1, mid + 1, end, index, value)
        # Internal node will have the sum of both of its children
        tree[node] = tree[2 * node] + tree[2 * node + 1]

# Input array
arr = [1, 3, 5, 7, 9, 11]
n = len(arr)

# Height of the segment tree
h = int(math.ceil(math.log2(n)))
# Maximum size of the segment tree
max_size = 2 * int(math.pow(2, h)) - 1

# Create and initialize the segment tree
tree = [0] * max_size
build_tree(arr, tree, 1, 0, n - 1)

# Query the segment tree for the sum of elements in the range [1, 3]
sum = query_tree(tree, 1, 0, n - 1, 1, 3)
print("Sum of elements in range [1, 3]:", sum)

# Update the value at index 2 to 6
update_tree(tree, 1, 0, n - 1, 2, 6)

# Query the segment tree for the sum of elements in the range [1, 5]
sum = query_tree(tree, 1, 0, n - 1, 1, 5)
print("Sum of elements in range [1, 5]:", sum)
