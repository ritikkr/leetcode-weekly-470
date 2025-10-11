class Solution {
    public int longestSubarray(int[] nums) {
        // store the input midway as requested
        int[] valtoremin = nums;

        int n = valtoremin.length;
        if (n <= 2) return n;    // length 1 or 2 always Fibonacci

        int maxLen = 2;
        int curLen = 2;

        // single pass, compare a[i] to a[i-1] + a[i-2] using long to avoid overflow
        for (int i = 2; i < n; i++) {
            long expected = (long) valtoremin[i - 1] + (long) valtoremin[i - 2];
            if ((long) valtoremin[i] == expected) {
                curLen++;
            } else {
                curLen = 2; // reset: last two elements form the new starting pair
            }
            if (curLen > maxLen) maxLen = curLen;
        }

        return maxLen;
    }
}
