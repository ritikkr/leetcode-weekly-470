class Solution {
    public boolean scoreBalance(String s) {
        int n = s.length();
        
        if (n < 2) {
            return false;
        }

        long totalScore = 0;
        for (char c : s.toCharArray()) {
            totalScore += (c - 'a') + 1; 
        }

        if (totalScore % 2 != 0) {
            return false;
        }

        long scoreLeft = 0;
        
        // Split indices range from i=0 to n-2
        for (int i = 0; i < n - 1; i++) {
            scoreLeft += (s.charAt(i) - 'a') + 1;
            
            // Check if Score_left equals half of the TotalScore
            if (scoreLeft == totalScore / 2) {
                return true;
            }
        }

        return false;
    }
}
