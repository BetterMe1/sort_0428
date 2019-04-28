package exercise.sort_0428;

import java.util.Arrays;

public class TestSort {
    public static void main(String[] args) {
        int[] array = {12,5,9,34,6,8,33,56,89,0,7,4,22,55,77};
        //insertSort(array);
        //shellSort(array);
        //selectSort(array);
        //heapSort(array);
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }
    /*
    直接插入排序:
    时间复杂度O（n^2)
    有序数据时为O(n),越有序越快
    稳定性：稳定
     */
    public static void insertSort(int[] array){
        for(int i=0,len = array.length; i<len; i++){
            int temp = array[i];
            int j=i-1;
            for(; j>=0 && array[j] > temp;j--){
                array[j+1] = array[j];
            }
            array[j+1] = temp;
        }
    }
    /*
   希尔排序
   时间复杂度O(n^1.3)~O(n^1.5)
   稳定性：不稳定
    */
    public static void shellSort(int[] array){
        int[] drr = {5,3,1};
        for(int i=0,len = drr.length; i<len; i++){
            shell(array, drr[i]);
        }
    }
    private static void shell(int[] array, int gap){
        for(int i=gap,len = array.length; i<len ;i++){
            int temp = array[i];
            int j=i-gap;
            for(; j>=0 && array[j] > temp; j-=gap){
                array[j+gap] = array[j];
            }
            array[j+gap] = temp;
        }
    }
    /*
    选择排序
    时间复杂度：O（n^2)
    空间复杂度：O(1)
    稳定性:不稳定
     */
    public static void selectSort(int[] array){
        int len = array.length;
        for(int i=0; i<len-1; i++){
            for(int j=i+1; j<len; j++){
                if(array[j] < array[i]){
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
    }

    /*
    时间复杂度：O(nlogn)
    空间复杂度：O(1)
    稳定性：不稳定
     */
    public static void heapSort(int[] array) {
        for(int i=(array.length-1-1)/2; i>=0; i--){
            adjust(array,i,array.length-1);
        }
        //上面的代码使得整棵树成为大根堆
        for(int j=0; j<array.length-1; j++){
            //交换
            int temp = array[0];
            array[0] = array[array.length-1-j];
            array[array.length-1-j] = temp;
            //再次调整
            adjust(array,0,array.length-1-j-1);
        }
    }
    public static void adjust(int[] array,int start,int end){
        int temp = array[start];
        for(int i=2*start+1; i<=end; i=2*i+1){
            if((i<end) && array[i]<array[i+1]){
                i++;//最大值的下标
            }
            if(array[i] > temp){
                array[start] = array[i];
                start = i;
            }else if(array[i] < temp){
                break;
            }
        }
        array[start] = temp;
    }

    /*
    快速排序
    时间复杂度：O(nlogn)
    空间复杂度：O(logn)
    稳定性：不稳定
     */
    public static void quickSort(int[] array){
        quick(array,0,array.length-1);
    }
    public static void quick(int[] array,int low,int high){
        if(low == high){
            return;
        }
        //优化：插入排序
        if(high-low+1 <= 10){
            insertSort1(array,low,high);
        }
        //优化:三数取中
        takeThreeNumber(array,low, high);
        int par = partion(array,low,high);
        //递归左边
        if(par >low+1){
            quick(array,low,par-1);
        }
        //递归右边
        if(par < high-1){
            quick(array,par+1,high);
        }
    }
    public static int  partion(int[] array,int low,int high){
        int tmp = array[low];
        while(low < high){
            while((low < high) && array[high] >= tmp){
                high--;
            }
            if(low == high){
                break;
            }else{
                array[low] = array[high];
            }
            while((low < high) && array[low] <= tmp){
                low++;
            }
            if(low == high){
                break;
            }else{
                array[high] = array[low];
            }
        }
        array[low] = tmp;
        return low;
    }
    public static void insertSort1(int[] array,int low, int high){
        for(int i=low+1; i<=high; i++){
            int temp = array[i];
            int j=i-1;
            for(; j>low && array[j] > temp;j--){
                array[j+1] = array[j];
            }
            array[j+1] = temp;
        }
    }
    public static void swap(int[] array,int low, int high){
        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;
    }
    public static void takeThreeNumber(int[] array,int low, int high){
        int mid = (low +high)>>1;
        if(array[mid] >array[low]){
            swap(array,low,mid);
        }
        if(array[mid] >array[high]){
            swap(array,low,high);
        }
        if(array[low] >array[high]){
            swap(array,low,high);
        }
    }
}

