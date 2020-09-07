package divide;

import java.util.Random;

/**
 *
 215. Kth Largest Element in an Array
 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 Example 1:

 Input: [3,2,1,5,6,4] and k = 2
 Output: 5
 Example 2:

 Input: [3,2,3,1,2,4,5,5,6] and k = 4
 Output: 4
 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class Q215_KthLargestElement {

    private Random random = new Random();

    private int findKthLargest(int[] nums, int k) {
        return findHelper(nums, 0, nums.length-1, nums.length-k);
    }

    private int findHelper(int[] nums, int l, int r, int k) {
        int idx = randomPartition(nums, l, r);
        if (idx == k) {
            return nums[idx];
        } else {
            return idx<k? findHelper(nums, idx+1, r, k): findHelper(nums, l, idx-1, k);
        }
    }
    // 认识到partition返回的下标不是固定，是取决于你的标杆数据
    private int randomPartition(int[] nums, int l, int r) {
        int idx = random.nextInt(r-l+1)+l;
        System.out.println((idx-l)+" "+idx);
        swap(nums, idx, r);
        return partition1(nums, l, r);
    }
    private int partition(int[] nums, int l, int r) {
        int comp = nums[r];
        int i = l-1; //i代表nums[i]>comp,会出现++i==j自交换的情况
        for (int j=l; j<r; j++) {
            if (nums[j] <= comp) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, i+1, r);
        return i+1;
    }
    private int partition1(int[] nums, int l, int r) {
        int comp = nums[r];
        while (l < r) {
            while (nums[l]<comp && l<r) {
                l++;
            }
            if (l < r) {
                nums[r] = nums[l];
                r--;
            }
            while (nums[r]>comp && l<r) {
                r--;
            }
            if (l < r) {
                nums[l] = nums[r];
                l++;
            }
        }
        nums[l] = comp;
        return l;
    }


    /**
     * 建立最大堆，使用数组作为树进行存储
     */
    private int findKthLargestHeap(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        // 删除大根堆中最大的元素
        for (int i=1; i<k; i++) {
            swap(nums, 0, heapSize-1);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }
    private void buildMaxHeap(int[] nums, int heapSize) {
        // 构建最大堆
        for (int i=heapSize/2; i>=0; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }
    private void maxHeapify(int[] nums, int i, int heapSize) {
        int left = 2*i+1;
        int right = 2*i+2;
        int largest = i;
        if (left<heapSize && nums[left]>nums[largest]) {
            largest = left;
        }
        if (right<heapSize && nums[right]>nums[largest]) {
            largest = right;
        }
        // 将最大值更新为根节点
        if (largest != i) {
            swap(nums, largest, i);
            // 从交换后的位置继续跟新大根堆
            maxHeapify(nums, largest, heapSize);
        }
    }

    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,5,6,4};
        int k = 2;
        Q215_KthLargestElement q215_kthLargestElement = new Q215_KthLargestElement();
        int ans = q215_kthLargestElement.findKthLargest(nums, k);
        System.out.println("quick sort:"+ans);

        int ans1 = q215_kthLargestElement.findKthLargestHeap(nums, k);
        System.out.println("heap:"+ans1);
    }
}
