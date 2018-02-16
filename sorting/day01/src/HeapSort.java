public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        if (leftChild(i) < size && (rightChild(i) < size )){
            int choice = heap[rightChild(i)] < heap[leftChild(i)] ? leftChild(i) : rightChild(i);
            if (heap[i] < heap[choice]) {
                swap(heap, i, choice);
                sink(choice);
            }
            return;
        }
        if (leftChild(i) < size){
            if (heap[i] < heap[leftChild(i)]){
                swap(heap, i, leftChild(i));
                sink(leftChild(i));
                return;
            }
        }
        if (rightChild(i) < size ){
            if (heap[i] < heap[rightChild(i)]){
                swap(heap, i, rightChild(i));
                sink(rightChild(i));
            }
        }
    }


    // overload sink to exclude end section of the heap (so can be left sorted)
    private void sink(int i, int len) {
        if (leftChild(i) < len && (rightChild(i) < len )){
            int choice = heap[leftChild(i)] < heap[rightChild(i)] ? leftChild(i) : rightChild(i);
            if (heap[i] < heap[choice]) {
                swap(heap, i, choice);
                sink(choice, len);
            }
        }
        if (leftChild(i) < len){
            if (heap[i] < heap[leftChild(i)]){
                swap(heap, i, leftChild(i));
                sink(leftChild(i), len);
                return;
            }
        }
        if (rightChild(i) < len ){
            if (heap[i] < heap[rightChild(i)]){
                swap(heap, i, rightChild(i));
                sink(rightChild(i), len);
            }
        }
    }


    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
        sink(0);
    }

    /**
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);
        for (int i=size-1; i>0; i--) {
            swap(heap, i, 0);
            sink(0,i);
        }
        return heap;
    }
}
