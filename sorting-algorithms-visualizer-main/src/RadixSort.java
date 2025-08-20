import java.util.ArrayList;
import java.util.Collections;

public class RadixSort {
	
	double increment = 0.05;
	
	// A utility function to get maximum value in arr[]
	private int getMax(ArrayList<Integer> arr) {
		int mx = arr.get(0);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i) > mx)
				mx = arr.get(i);
		}
		return mx;
	}
	
	// A function to do counting sort of arr[] according to
	// the digit represented by exp.
	private void countSort(ArrayList<Integer> arr, int exp, DrawRect rectArr, Visualizer vis) throws InterruptedException {
		ArrayList<Integer> output = new ArrayList<>(Collections.nCopies(arr.size(), 0));
		int i;
		int count[] = new int[10];
		
		// Store count of occurrences in count[]
		for (i = 0; i < arr.size(); i++) {
			count[(arr.get(i) / exp) % 10]++;
		}
		
		// Change count[i] so that count[i] now contains
		// actual position of this digit in output[]
		for (i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}
		
		// Build the output array
		for (i = arr.size() - 1; i >= 0; i--) {
			output.set(count[(arr.get(i) / exp) % 10] - 1, arr.get(i));
			count[(arr.get(i) / exp) % 10]--;
		}
		
		// Copy the output array to arr[], so that arr[] now
		// contains sorted numbers according to current digit
		for (i = 0; i < arr.size(); i++) {
			arr.set(i, output.get(i));
			Visualizer.noOfComparisons++;
			Thread.sleep(20);
			rectArr.setArr(arr);
			rectArr.paintImmediately(0, 230, Visualizer.WIDTH, 900);
			vis.setSize(Visualizer.WIDTH, (int) (Visualizer.HEIGHT + increment));
			increment = -increment;
		}
	}
	
	// The main function that sorts arr[] using Radix Sort
	public void sort(ArrayList<Integer> arr, DrawRect rectArr, Visualizer vis) throws InterruptedException {
		// Find the maximum number to know number of digits
		int m = getMax(arr);
		
		// Do counting sort for every digit. Note that instead
		// of passing digit number, exp is passed. exp is 10^i
		// where i is current digit number
		for (int exp = 1; m / exp > 0; exp *= 10) {
			countSort(arr, exp, rectArr, vis);
			System.out.println(arr);
		}
		
		vis.comparisons.setText("No. of Comparisons: " + Visualizer.noOfComparisons);
		Visualizer.noOfComparisons = 0;
	}
}
