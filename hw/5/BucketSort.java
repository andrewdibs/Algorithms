import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class BucketSort {
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
}