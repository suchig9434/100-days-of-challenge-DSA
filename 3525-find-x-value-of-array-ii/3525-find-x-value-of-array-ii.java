class Solution {

    class Node {
        int[] cnt;
        int product;

        Node() {
            cnt = new int[k];
            product = 1 % k;
        }
    }

    int n;
    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update nums[index]
            update(1, 0, n - 1, index, value);

            // Query nums[start ... n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            result[i] = res.cnt[x];
        }

        return result;
    }

    // Build segment tree
    void build(int node, int l, int r, int[] nums) {

        if (l == r) {
            tree[node] = new Node();

            int rem = nums[l] % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one position
    void update(int node, int l, int r, int index, int value) {

        if (l == r) {
            tree[node] = new Node();

            int rem = value % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Query range [ql, qr]
    Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node leftPart = query(node * 2, l, mid, ql, qr);
        Node rightPart = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(leftPart, rightPart);
    }

    // Merge two consecutive segments
    Node merge(Node A, Node B) {

        Node C = new Node();

        // Product of complete segment
        C.product = (A.product * B.product) % k;

        // Prefixes entirely inside A
        for (int r = 0; r < k; r++) {
            C.cnt[r] += A.cnt[r];
        }

        // Prefixes containing all of A
        // and a prefix of B
        for (int r = 0; r < k; r++) {

            int newRemainder = (A.product * r) % k;

            C.cnt[newRemainder] += B.cnt[r];
        }

        return C;
    }
}