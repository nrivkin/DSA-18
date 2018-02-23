import java.util.Arrays;
import java.util.HashMap;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int count = 0;
        Arrays.sort(arr);
        for (int num : arr){
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < arr.length - 2; i++){
            int num = arr[i];
            if(num > sum){
                break;
            }
            if(freq.get(num) == 1){
                freq.remove(num);
            } else {
                freq.put(num, freq.get(num) - 1);
            }
            int tofind = sum - num;
            for (int j = i+1; j < arr.length - 1; j++){
                if(freq.containsKey(tofind - arr[j])){
                    if(tofind - arr[j] == arr[j]){
                        count += freq.get(tofind - arr [j]) - 1;
                    } else {
                        count += freq.get(tofind - arr[j]);
                        System.out.println(Integer.toString(num) + ' ' + Integer.toString(arr[j]));
                    }
                }
                if(arr[j] + arr[j+1] >= tofind){
                    j = arr.length;
                }
            }
        }
        return count;
    }
}
