import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.Random;
public class FlopSortFinal{
  public static int comparisons;
  public static int swaps;

  public static void main(String args []){
    FlopSortFinal f = new FlopSortFinal();

    Random r = new Random();
    int[] arr = new int[10000];
    for (int i = 0; i < arr.length; i++){
      arr[i] = r.nextInt(100000);
    }

    //f.printArr(arr);

    long start = System.nanoTime();
    f.flopSort(arr, 0, arr.length - 1);
    long finish = System.nanoTime();

    long totalTime = (finish - start) / 1000;

    System.out.println("Total time: " + totalTime + " Microseconds \nPassed: " + f.sorted(arr));
    System.out.println("comparisons: " + comparisons + "\nSwaps: " + swaps);
    //f.printArr(arr);


  }

  public FlopSortFinal(){
    comparisons = 0;
    swaps = 0;
  }


  /*A recursive sorting algorithm that */
  public int[] flopSort(int[] arr, int s, int e){
    int i = s;
    int j = e;
    boolean iMove = true;
    int pos = 0;

    if ((e - s) < 1)
      return arr;

    while(i < j){
      comparisons++;
      if (arr[i] > arr[j]){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        swaps++;

        if(iMove)
          iMove = false;
        else
          iMove = true;
      }

      if(iMove)
        i++;

      if (!iMove)
        j--;

      pos = j;
    }

    arr = flopSort(arr, pos, e);
    arr = flopSort(arr, s, pos-1);

    return arr;
  }

  //Test to see an array is sorted correctly
  public boolean sorted(int[] arr){
    for(int i = 0; i < arr.length-1; i++){
      if (arr[i] > arr[i+1])
        return false;
    }

    return true;
  }

  //Print array...
  public void printArr(int[] arr){
    for(int i : arr)
      System.out.print(i + ", ");

    System.out.println("");
  }
}
