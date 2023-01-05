public class Combinations {

    public static int combinations(int group, int members) {
        if (members == 1) {
            // Base case: return group if members == 1
            // If you have n people and you are asked how many
            // unique single person groups you can make,
            // the answer will always be n groups.
            return group;
        } else if (members == group) {
            // Base case: return 1 if members == group
            // If you have have n people and are asked how many unique
            // groups of size n you can make, the answer will
            // always be 1.
            return 1;
        } else {
            // Recursive case: return C(g-1, m-1) + C(g-1, m)
            // if g > m and m > 1
            return combinations(group - 1, members - 1) + combinations(group - 1, members);
        }
    }

    public static void main(String[] args) {
        System.out.println("Suppose you are asked how many unique 5 member"
                + "panels can be made from a group of 20 students...");
        System.out.println("Combinations = " + combinations(20, 5));
    }
}
