package data;

import java.util.LinkedList;
import java.util.Queue;

public class Sort {
	public static void quickSort(Events[] arr,int low,int high){
        int i,j;
        Events temp, t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];
 
        while (i<j) {
            //先看右边，依次往左递减
            while (temp.getDeadline().compareTo(arr[j].getDeadline()) == -1 && i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp.getDeadline().compareTo(arr[i].getDeadline()) != -1 && i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
 
        }
        //最后将基准位与i和j相等位置的数字交换
         arr[low] = arr[i];
         arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }
	
	public static Events[] Q(Events[] arr, int arrcnt) {
		Queue<Events> queue = new LinkedList<Events>();
		int cntQ = 0, newarrcnt = 0;
		Events[] newarr = new Events[arrcnt];
		
		for(int cnt = 0; cnt < arrcnt; cnt++) {
			if(arr[cnt].getState().equals("完成")) {
				queue.offer(arr[cnt]);
				cntQ++;
			}else {
				newarr[newarrcnt] = arr[cnt];
				newarrcnt++;
			}
		}
		for(int cnt = 0; cnt < cntQ; cnt++) {
			newarr[newarrcnt + cnt] = queue.poll();
		}
		
		return newarr;
	}
}
