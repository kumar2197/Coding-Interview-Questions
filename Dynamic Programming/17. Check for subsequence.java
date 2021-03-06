/*
Company Tags:  Accolite Amazon Tesco Zoho
Practice Portal:
Geeksforgeeks: https://practice.geeksforgeeks.org/problems/check-for-subsequence4930/1
*/

// PARENT PROBLEM: Longest Common Subsequence BUT BUT BUT, this Q can easily be solved using 2 pointer pattern

/************************************************** Recursive Solution ************************************************/

class Solution{
    boolean isSubSequence(String A, String B){
        int lcs = lcs(A.length(), B.length(), A, B);
        if (lcs == A.length())
            return true;
        return false;
    }

    public int lcs(int p, int q, String s1, String s2){

        //Base case
        if (p==0 || q ==0)
            return 0;

        if (s1.charAt(p-1) == s2.charAt(q-1)) {
            return lcs(p-1, q-1, s1, s2) + 1;
        } else {
            return Math.max(lcs(p-1, q, s1, s2),
                    lcs(p, q-1, s1, s2));
        }
    }
}

/*
Time Complexity: The time complexity of this recursive approach is exponential as there is a case of overlapping subproblems
as explained in the recursive tree diagram above.
Auxiliary Space: O(1). No external space used for storing values apart from the internal stack space.
*/

/****************************************** Memoization Solution (Top-Down) *******************************************/

class Solution{
    boolean isSubSequence(String A, String B){
        int lcs = lcs(A.length(), B.length(), A, B);
        if (lcs == A.length())
            return true;
        return false;
    }

    public int lcs(int p, int q, String s1, String s2) {

        int[][] dp = new int[p+1][q+1];

        for (int i=0; i<p+1; i++) {
            for (int j=0; j<q+1; j++) {
                dp[i][j] = -1;
            }
        }

        return lcsHelper(p, q, s1, s2, dp);
    }

    public int lcsHelper(int p, int q, String s1, String s2, int[][] dp) {
        //Base case
        if (p==0 || q ==0)
            return 0;

        if (dp[p][q] != -1)
            return dp[p][q];

        if (s1.charAt(p-1) == s2.charAt(q-1)) {
            dp[p][q] = lcsHelper(p-1, q-1, s1, s2, dp) + 1;
        } else {
            dp[p][q] = Math.max(lcsHelper(p-1, q, s1, s2, dp),
                    lcsHelper(p, q-1, s1, s2, dp));
        }

        return dp[p][q];
    }
}

/*
Time Complexity: O(n^2)
Auxiliary Space: O(n^2) (Apart from the internal stack space)
*/

/************************************************** Bottom-up Solution ************************************************/

class Solution{
    boolean isSubSequence(String A, String B){
        int lcs = lcs(A.length(), B.length(), A, B);
        if (lcs == A.length())
            return true;
        return false;
    }

    public int lcs(int p, int q, String s1, String s2){

        int[][] dp = new int[p+1][q+1];

        for(int i=0; i<p+1; i++)
            dp[i][0] = 0;

        for(int j=0; j<q+1; j++)
            dp[0][j] = 0;

        for (int i=1; i<p+1; i++) {
            for (int j=1; j<q+1; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],
                            dp[i][j-1]);
                }
            }
        }

        return dp[p][q];
    }
}

/*
Time Complexity: O(n^2)
Auxiliary Space: O(n^2)
*/

/************************************************** BETTER 2 POINTER SOL ************************************************/

public static int checkForSubsequence(String s1, String s2, int p, int q) {
    int i=0, j=0;

    while (i<p && j<q) {
        if (s1.charAt(i) == s2.charAt(j)) {
            i++;
            j++;
        } else {
            j++;
        }
    }

    if (i == p) return 1;
    return 0;
}

/*
Time Complexity: O(n)
Auxiliary Space: O(1)
*/