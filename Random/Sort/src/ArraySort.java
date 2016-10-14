import java.util.Arrays;
import java.util.Random;

public class ArraySort {
	public static <T extends Comparable<T>> void QuickSort(T[] arr) {
		if(arr != null && arr.length > 1) doQuickSort(arr, 0, arr.length - 1);
	}
	
	private static <T extends Comparable<T>> void doQuickSort(T[] arr, int start, int end) {
		int left = start;
		int right = end;
		T pivot = arr[left + (right - left) / 2];
		
		while(left <= right) {
			while(arr[left].compareTo(pivot) < 0) left++;
			while(arr[right].compareTo(pivot) > 0) right--;
			
			if(left <= right) {
				T temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				
				left++; right--;
			}
		}
		
		if(start < right) doQuickSort(arr, start, right);
		if(left < end) doQuickSort(arr, left, end);
	}
	
	public static void main(String[] args) {
		Random rn = new Random();
		long start = 0;
		int size = 1000000;
		Integer[] arr1 = new Integer[size];
		Integer[] arr2 = new Integer[size];
		for(int i = 0; i < size; i++) {
			int num = rn.nextInt(size);
			arr1[i] = num;
			arr2[i] = num;
		}
		start = System.currentTimeMillis();
		ArraySort.QuickSort(arr1);
		System.out.println(System.currentTimeMillis() - start);
		
		start = System.currentTimeMillis();
		Arrays.sort(arr2);
		System.out.println(System.currentTimeMillis() - start);
	}
}
