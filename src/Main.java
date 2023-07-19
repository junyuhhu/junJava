import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        scanner.close();

        int result = getMinimumOperations(nums);
        System.out.println("Minimum operations: " + result);
    }

    private static int getMinimumOperations(int[] nums) {
        int n = nums.length;

        // 统计每个元素出现的频率
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 找到出现频率最高的元素
        int maxFrequency = 0;
        int maxFrequencyElement = 0;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int element = entry.getKey();
            int frequency = entry.getValue();
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                maxFrequencyElement = element;
            }
        }

        // 计算最少的操作次数
        int operations = 0;
        for (int num : nums) {
            operations += Math.abs(num - maxFrequencyElement);
        }

        return operations;
    }
}
