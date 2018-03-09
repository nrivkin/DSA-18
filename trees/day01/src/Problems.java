public class Problems {

    public static int leastSum(int[] A) {
        if (A.length == 0) return 0;
        int[] counting = new int[10];
        for(int i : A) counting[i]++;
        int first = 0;
        int second = 0;
        if (A.length % 2 == 1){
            for (int i = 0; i < counting.length; i++){
                if (counting[i] != 0){
                    first += i;
                    counting[i]--;
                    i = counting.length;
                }
            }
        }
        boolean flag = true;
        for (int i = 0; i < counting.length; i++){
            if(counting[i] > 0){
                counting[i]--;
                if (flag){
                    first *= 10;
                    first += i;
                } else {
                    second *= 10;
                    second += i;
                }
                flag = !flag;
                i--;
            }
        }
        return first + second;
    }
}
