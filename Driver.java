package stockersAssessment2;

import java.util.*;

public class Driver {
	
	//function for merge in ascending order
	public static void merge_ascending(double arr[], int left, int mid, int right) {
		// Find sizes of two sub arrays to be merged
		int n1 = mid - left + 1;
		int n2 = right - mid;

		/* Create temporary arrays */
		double leftArray[] = new double[n1];
		double rightArray[] = new double[n2];

		/* Copy data to temporary arrays */
		for (int i = 0; i < n1; ++i)
			leftArray[i] = arr[left + i];
		for (int j = 0; j < n2; ++j)
			rightArray[j] = arr[mid + 1 + j];

		/* Merge the temporary arrays */

		// Initial indexes of first and second sub-arrays
		int i = 0, j = 0;

		// Initial index of merged sub-array array
		int k = left;
		while (i < n1 && j < n2) {
			if (leftArray[i] <= rightArray[j]) {
				arr[k] = leftArray[i];
				i++;
			}
			else {
				arr[k] = rightArray[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = leftArray[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = rightArray[j];
			j++;
			k++;
		}
	}

	//function that sorts arr[l..r] using merge_ascending()
	public static void sort_asc(double[] arr, int left, int right) {
		if (left < right) {
			// Find the middle point
			int mid = left + (right - left)/2;

			// Sort first and second halves
			sort_asc(arr, left, mid);
			sort_asc(arr, mid + 1, right);

			// Merge the sorted halves
			merge_ascending(arr, left, mid, right);
		}
	}	

	//function for merge in descending order
	public static void merge_descending(double arr[], int left, int mid, int right) {
		// Find sizes of two sub arrays to be merged
		int n1 = mid - left + 1;
		int n2 = right - mid;

		/* Create temporary arrays */
		double leftArray[] = new double[n1];
		double rightArray[] = new double[n2];

		/* Copy data to temporary arrays */
		for (int i = 0; i < n1; ++i)
			leftArray[i] = arr[left + i];
		for (int j = 0; j < n2; ++j)
			rightArray[j] = arr[mid + 1 + j];

		/* Merge the temporary arrays */

		// Initial indexes of first and second sub-arrays
		int i = 0, j = 0;

		// Initial index of merged sub-array array
		int k = left;
		while (i < n1 && j < n2) {
			if (leftArray[i] >= rightArray[j]) {
				arr[k] = leftArray[i];
				i++;
			}
			else {
				arr[k] = rightArray[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = leftArray[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = rightArray[j];
			j++;
			k++;
		}
	}

	//function that sorts arr[l..r] using merge_descending()	
	public static void sort_des(double[] arr, int left, int right) {
		if (left < right) {
			// Find the middle point
			int mid = left + (right - left)/2;

			// Sort first and second halves
			sort_des(arr, left, mid);
			sort_des(arr, mid + 1, right);

			// Merge the sorted halves
			merge_descending(arr, left, mid, right);
		}
	}	

	//function for binary search
	public static int binarySearch(double arr[], int l, int r, double x)
	{
		if (r >= l) {
			int mid = l + (r - l) / 2;

			// If the element is present at the middle itself
			if (arr[mid] == x)
				return mid+1;

			// If element is smaller than mid, then it can only be present in left subarray
			if (arr[mid] > x)
				return binarySearch(arr, l, mid - 1, x);

			// Else the element can only be present in right subarray
			return binarySearch(arr, mid + 1, r, x);
		}

		// We reach here when element is not present in array
		return -1;
	}

	//------------------------------------------------MAIN FUNCTION----------------------------------------------------//
	public static void main(String[] args) {
		System.out.println("Enter the number of companies: ");
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();															//No. of companies
		double companies[] = new double [N];
		boolean stock_status[] = new boolean [N];
		for (int i=0; i<N; i++) {
			System.out.println("Please enter stock price of company "+(i+1)+": ");
			companies[i] = sc.nextDouble();
			System.out.println("Did company "+(i+1)+"'s stock price rise today?");
			stock_status[i] = sc.nextBoolean();
		}

		System.out.println(" ");														//List of operations
		String l1 = "1. Display the companies stock prices in ascending order";
		String l2 = "2. Display the companies stock prices in descending order";
		String l3 = "3. Display the total no. of companies for which stock prices rose today";
		String l4 = "4. Display the total no. of companies for which stock prices declined today";
		String l5 = "5. Search a specific stock price";
		String l6 = "6. Press 0 to exit";

		int choice;
		do{
			System.out.println("Please enter the operation to be performed:- ");							
			System.out.println(l1+"\n"+l2+"\n"+l3+"\n"+l4+"\n"+l5+"\n"+l6+"\n");
			choice = sc.nextInt();														//Choice of operations: l1 - l6	

			//TASK 1: Display stock prices in ascending order
			if(choice == 1) {
				sort_asc(companies, 0, companies.length-1);
				System.out.println("Stock prices in ascending order: ");
				for (int i=0; i<companies.length; i++) {
					System.out.println(companies[i]);
				}
				System.out.println("---------------------------------------------------");
				System.out.println(" ");
			}

			//TASK 2: Display stock prices in descending order
			else if (choice == 2){
				sort_des(companies, 0, companies.length-1);
				System.out.println("Stock prices in descending order: ");
				for (int i=0; i<companies.length; i++) {
					System.out.println(companies[i]);
				}
				System.out.println("---------------------------------------------------");
				System.out.println(" ");
			}

			//TASK 3: Display the total no. of companies for which stock prices rose
			else if (choice == 3){
				int status_rise = 0;
				for (int i=0; i<stock_status.length; i++) {
					if(stock_status[i] == true) {
						status_rise++;
					}
				}
				System.out.println("Total no. of companies whose stock price rose: "+status_rise);
				System.out.println("---------------------------------------------------");
				System.out.println(" ");
			}

			//TASK 4: Display the total no. of companies for which stock prices dropped
			else if (choice == 4){
				int status_drop = 0;
				for (int i=0; i<stock_status.length; i++) {
					if(stock_status[i] == false) {
						status_drop++;
					}
				}
				System.out.println("Total no. of companies whose stock price declined: "+status_drop);
				System.out.println("---------------------------------------------------");
				System.out.println(" ");
			}

			//TASK 5: Search a specific stock price
			else if (choice == 5){
				sort_asc(companies, 0, companies.length-1);							//Arrange price in increasing order to find price rank
				System.out.println("Please enter the key value: ");
				double key = sc.nextDouble();
				int result = binarySearch(companies, 0, companies.length - 1, key);
				if (result == -1)
					System.out.println("Searched stock price is ABSENT");
				else
					System.out.println("Searched stock price is PRESENT at rank " +result);
				System.out.println("---------------------------------------------------");
				System.out.println(" ");
			}

			//TASK 6: Press 0 to exit
			else if (choice == 0){
				System.out.println("Exited successfully");
				System.exit(0);
			}

			//DEFAULT: Invalid Input
			else if(choice != 0 || choice != 1 || choice != 2 || choice != 3 || choice != 4 || choice != 5) {
				System.out.println("Invalid operation chosen, please choose 0 to exit\n");
			}

		} while(choice != 0);
		sc.close();
	}
}