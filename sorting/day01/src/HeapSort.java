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

    // Recursively corrects the position of element indexed i: check children, and swap with larger child if necessary.
    public void heapify(int i) {
            if (leftChild(i)<size&&heap[leftChild(i)]>heap[i]){
                if(rightChild(i)<size&&heap[leftChild(i)]<heap[rightChild(i)]){
                    swap(rightChild(i),i);
                    heapify(rightChild(i));
            }else {
                swap(leftChild(i),i);
                heapify(leftChild(i));}}
            else if (rightChild(i)<size&&heap[rightChild(i)]>heap[i]){
                swap(rightChild(i),i);
                heapify(rightChild(i));
            }
        }
    public void adjustToHeap(int i) {
        if (i!=0 && heap[parent(i)]<heap[i]){
            swap(parent(i),i);
            adjustToHeap(parent(i));
        }
    }
    // Given the array, build a heap by correcting every non-leaf's position.
    public void buildHeapFrom(int[] array) {
        this.heap = array;
        this.size = array.length;
        for (int i = 0; i<array.length;i++){
            heap[i] = array[i];
            adjustToHeap(i);
        }
        }

    public void swap(int a, int b){
        int temp  = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
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
        buildHeapFrom(array);

//        for (int j : heap){
//            System.out.print(j);
//        }
//        System.out.println("a");
        for (int i=size-1; i>0; i--) {
            swap(i,0);
            size--;
            heapify(0);

//            for (int j : heap){
//                System.out.print(j);
//            }
//            System.out.println("a");
        }
        return heap;
    }
}
