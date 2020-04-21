import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class SortDriver{
  public static void main(String [] args){
    int m = 1000000000;
    int n = 100;

    int[] random = new int[n];

    for (int i = 0; i < random.length; i++){
      random[i] = (int)(Math.random() * m);
    }
    // quickSort
    float qstart = System.nanoTime();
    int[] quickSorted = quickSort(random,0,random.length-1);
    float quickSortTime = System.nanoTime() - qstart;
    

    // bucketSort
    float bstart = System.nanoTime();
    List<Integer> bucketSorted = bucketSort(random, m);
    float bucketSortTime = System.nanoTime() - bstart;
    

    // System.out.println("Bucket Sort: ");
    // for (int x : bucketSorted){
    //   System.out.println(x);
    // }
    // System.out.println("Quick Sort: ");
    // for (int x : quickSorted){
    //   System.out.println(x);
    // }

    System.out.println("bucketSortTime: " + bucketSortTime);
    System.out.println("QuickSortTime: " + quickSortTime);
  }

  
  static List<Integer> bucketSort(int[] random, int max){
    int numBuckets = (int)Math.sqrt(random.length);
    List<List<Integer>> buckets = new ArrayList<List<Integer>>();
    for (int i=0; i<numBuckets;i++){
      buckets.add(new ArrayList<>());
    }

    for (int x: random){
      buckets.get(findBucket(x, max, numBuckets)).add(x);
    }
    Comparator<Integer> comparator = Comparator.naturalOrder();
    for (List<Integer> bucket: buckets){
      bucket.sort(comparator);
    } 
    List<Integer> sorted = new ArrayList<>();
    for (List<Integer> bucket: buckets){
      sorted.addAll(bucket);
    }
    return sorted; 
  }

  static int findBucket(int i, int max, int numBuckets){
    return (int)((double) i / max * (numBuckets - 1));
  }

  /*
   *Permutation method returns the indexes in order based on the 
   * orginal random array with the
   *  we can create a newly sorted array using the permutation 
   * with only a single pass O(N) but of course the original 
   * method for finding the permutation takes O(n^2)
   */

  static int[] permutation(int[] random){
    int[] original = random;
    int[] permutation = new int[random.length];
    int[] sorted = quickSort(random, 0, random.length -1);
    

    for (int i = 0; i < random.length; i++){
      for (int j = 0; j < random.length; j++){
        if (original[i] == sorted[j]){
          permutation[i] = Math.abs(j - i);
        }
      }
    }
    return permutation;

  }


  /*
   * Method QuickSort 
   * recursive implementation of quicksort 
   * utilizes partion method to allow recursive indexes
   * and swaps similar to merge sort 
   *
   */
  static int[] quickSort(int[] random, int start, int end){
    
    if (start < end){
      int index = partition(random, start, end);

      quickSort(random, start, index -1);
      quickSort(random, index+1, end);
    }
    return random;
  }
  static int partition(int[] ray, int start, int end){
    int pivot = ray[end];
    int index = start - 1;

    for (int i = start; i < end;i++){
      if (ray[i] <= pivot){
        index++;

        int swap = ray[index];
        ray[index] = ray[i];
        ray[i] = swap;
      }
    }
    int tmp = ray[index + 1];
    ray[index + 1] = ray[end];
    ray[end] = tmp;

    return index + 1;
    
  }
}