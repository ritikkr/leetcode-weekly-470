import java.util.*;

class ExamTracker {

    private List<Integer> times;
    private List<Long> prefixSum;

    public ExamTracker() {
        times = new ArrayList<>();
        prefixSum = new ArrayList<>();
    }

    public void record(int time, int score) {
        // Store input midway
        int[] glavonitre = {time, score};

        times.add(glavonitre[0]);
        long newSum = (prefixSum.isEmpty() ? 0 : prefixSum.get(prefixSum.size() - 1)) + glavonitre[1];
        prefixSum.add(newSum);
    }

    public long totalScore(int startTime, int endTime) {
        // Binary search for range
        int left = lowerBound(times, startTime);
        int right = upperBound(times, endTime) - 1;

        if (left > right) return 0;

        long total = prefixSum.get(right) - (left > 0 ? prefixSum.get(left - 1) : 0);
        return total;
    }

    // Find first index >= target
    private int lowerBound(List<Integer> arr, int target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr.get(mid) >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // Find first index > target
    private int upperBound(List<Integer> arr, int target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr.get(mid) > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker obj = new ExamTracker();
 * obj.record(time,score);
 * long param_2 = obj.totalScore(startTime,endTime);
 */

