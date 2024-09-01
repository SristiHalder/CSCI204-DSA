public class Problem2a {
    public static <T> boolean is_symm(T[] nums) {      //   Cost     Time (Worst Case) (2c)
        int length = nums.length;                      //    C1        1
        for (int i = 0; i < length / 2; i++) {         //    C2        n/2
            if (!nums[i].equals(nums[length - i - 1])) //    C3        n/2
                return false;                          //    C4         1
        }
        return true;                                   //    C5         1
    }

    public static void main(String[] args) {
        // Sample data
        Integer[] A1 = {1, 4, 3, 4, 1};
        Integer[] A2 = {1, 4, 4, 1};
        Integer[] A3 = {1, 4, 3, 1, 4};
        Integer[] A4 = {1, 4, 1, 1};

        System.out.println("Symmetric: " + is_symm(A1));
        System.out.println("Symmetric: " + is_symm(A2));
        System.out.println("Non-symmetric: " + is_symm(A3));
        System.out.println("Non-symmetric: " + is_symm(A4));
    }
}

// Time Complexity Analysis:
// T(n) = C1(1) + C2(n/2) + C3(n/2) + C4(1) + C5(1)
// T(n) = C1 + C4 + C5 + n/2(C2+C3)
// Let a = C1 + C4 + C5, b = C2 + C3
// T(n) = a + b(n/2)