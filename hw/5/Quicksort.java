
class QuickSort{
/*
   * Method QuickSort 
   * recursive implementation of quicksort 
   * utilizes partion method to allow recursive indexes
   * and swaps similar to merge sort 
   *
   */
  public int[] quickSort(int[] random, int start, int end){
    
    if (start < end){
      int index = partition(random, start, end);

      quickSort(random, start, index -1);
      quickSort(random, index+1, end);
    }
    return random;
  }
  public int partition(int[] ray, int start, int end){
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