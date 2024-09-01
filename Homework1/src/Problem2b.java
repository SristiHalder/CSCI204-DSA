public class Problem2b {
    public static <T> boolean is_symmetry(T[] nums, int i, int n) { //   Cost     Time (Worst Case) (2c)
        if (i > n / 2)                                              //    C1       1
            return true;
        else if (!nums[i].equals(nums[n - i - 1]) && i < n / 2)     //    C2       n/2
            return false;                                           //    C3        1
        else
            return is_symmetry(nums, i + 1, n);                  //     C4       n/2
    }

    public static void main(String[] args) {
        // Sample data
        Integer[] A1 = {1, 4, 3, 4, 1};
        Integer[] A2 = {1, 4, 4, 1};
        Integer[] A3 = {1, 4, 3, 1, 4};
        Integer[] A4 = {1, 4, 1, 1};

        System.out.println("Symmetric: " + is_symmetry(A1, 0, A1.length));
        System.out.println("Symmetric: " + is_symmetry(A2, 0, A2.length));
        System.out.println("Non-symmetric: " + is_symmetry(A3, 0, A3.length));
        System.out.println("Non-symmetric: " + is_symmetry(A4, 0, A4.length));
    }
}
//T(n) = C1(1) + C2(n/2) + C3(1) + C4(n/2)
//T(n) = C1+ C3 + n/2(C2+C4)
//C1+C3--> a, (C2+C4)--> b
//T(n) = a+b(n/2)
//Therefore, it is of linear complexity
