import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.Random;
public class FlopSort{
  public static int flopComparisons = 0;
  public static int flopSwaps = 0;

  public static int quickComparisons = 0;
  public static int quickSwaps = 0;

  public int[] quickSort(int[] arr, int min, int max){
    if (arr.length <= 1)
		  return arr;

    int tMin = min;
    int tMax = max;
    int piv = arr[min + (max - min)/2];

    while (tMin <= tMax) {

        while (arr[tMin] < piv){
            tMin++;
            quickComparisons++;
        }
        while (arr[tMax] > piv){
            tMax--;
            quickComparisons++;
        }

        quickComparisons++;
       	if (tMin <= tMax) {
          int temp = arr[tMin];
          arr[tMin] = arr[tMax];
          arr[tMax] = temp;
          tMin++;
          tMax--;
          quickSwaps++;
        }
    }

    if (min < tMax)
        quickSort(arr, min, tMax);
    if (tMin < max)
        quickSort(arr, tMin, max);

    return arr;
  }

  public int[] mergeSort(int [ ] a)
	{
		int[] tmp = new int[a.length];
		mergeSort(a, tmp,  0,  a.length - 1);
    return tmp;
	}


	private void mergeSort(int [ ] a, int [ ] tmp, int left, int right)
	{
		if( left < right )
		{
			int center = (left + right) / 2;
			mergeSort(a, tmp, left, center);
			mergeSort(a, tmp, center + 1, right);
			merge(a, tmp, left, center + 1, right);
		}
	}


    private void merge(int[ ] a, int[ ] tmp, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left] <= a[right])
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }

  public int[] flop(int[] arr, int s, int e){
    int i = s;
    int j = e;
    boolean iMove = true;
    int pos = 0;

    if ((e - s) < 1)
      return arr;

    while(i < j){
      flopComparisons++;
      if (arr[i] > arr[j]){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        flopSwaps++;

        if(iMove)
          iMove = false;
        else
          iMove = true;
      }

      if(iMove)
        i++;

      else if (!iMove)
        j--;

      pos = j;
    }

    arr = flop(arr, pos, e);
    return flop(arr, s, pos-1);

    //return arr;
  }

  public int[] middleOut(int arr[], int s ,int e){
    int i = 0;
    int j = arr.length-1;
    int p = i+1;

    while(i < j){

      if (arr[i] > arr[j]){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }

      while (p < j){
        if (arr[p] < arr[i]){
          int temp = arr[p];
          arr[p] = arr[i];
          arr[i] = temp;
        }
        else if (arr[p] > arr[j]){
          int temp = arr[p];
          arr[p] = arr[j];
          arr[j] = temp;
        }
        p++;
      }

      i++;
      j--;
      p=i+1;
    }

    return arr;
  }


  public static void main(String args []){
    FlopSort f = new FlopSort();
    long elapsed = 0;
    int flopCount = 0;
    int size = 2000000;


    for (int c = 0; c < 10; c++){
      int[] test1 = new int[size];
      int[] test2 = new int[size];
      int[] test3 = new int[size];
      size = test2.length;
      Random r = new Random();

      for (int i = 0; i < size; i++){
        int num = r.nextInt(1000000);
        test1[i] = num;
        test2[i] = num;
        test3[i] = num;
      }

      // System.out.println("For " + size + " elements: \n");


      // long start1 = System.nanoTime();
      // int[] arr = f.mergeSort(test1);
      // long mergeElapsed = (System.nanoTime() - start1)/1000;
      // System.out.println("Merge Sort took: " + mergeElapsed + " Microseconds \nPassed? " + f.testWin(arr) + "\n");

      //System.out.println("For " + size + " elements: \n");

      long start2 = System.nanoTime();
      int[] arr3 = f.quickSort(test3, 0, test3.length-1);
      long quickElapsed = (System.nanoTime() - start2)/1000;
      // System.out.println("Quick sort took: " + quickElapsed + " Microseconds\nPassed? " + f.testWin(arr3));
      // System.out.println("Quick comparisons: " + quickComparisons + "\nQuick swaps: " + quickSwaps + "\n");

      long start3 = System.nanoTime();
      int[] arr2 = f.flop(test2, 0, test2.length-1);
      long flopElapsed = (System.nanoTime() - start3)/1000;
      // System.out.println("Flop  sort took: " + flopElapsed + " Microseconds\nPassed? " + f.testWin(arr2));
      // System.out.println("Flop comparisons: " + flopComparisons + "\nFlop swaps: " + flopSwaps + "\n");






       if (flopElapsed < quickElapsed)
         flopCount++;
    }
       System.out.println("For " + size + " elements:");
       System.out.println("Flop wins: " + flopCount + "\nQuick wins: " + (10 - flopCount));





  }

  public void printArr(int[] arr){
    for(int i : arr)
      System.out.print(i + ",");

    System.out.println("\n");
  }


  public boolean testWin(int[] arr){
    for (int i = 0; i < arr.length -1; i++){
      if (arr[i] > arr[i+1])
        return false;
    }
    return true;
  }


}
