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
        //temp���ǻ�׼λ
        temp = arr[low];
 
        while (i<j) {
            //�ȿ��ұߣ���������ݼ�
            while (temp.getDeadline().compareTo(arr[j].getDeadline()) == -1 && i<j) {
                j--;
            }
            //�ٿ���ߣ��������ҵ���
            while (temp.getDeadline().compareTo(arr[i].getDeadline()) != -1 && i<j) {
                i++;
            }
            //������������򽻻�
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
 
        }
        //��󽫻�׼λ��i��j���λ�õ����ֽ���
         arr[low] = arr[i];
         arr[i] = temp;
        //�ݹ�����������
        quickSort(arr, low, j-1);
        //�ݹ�����Ұ�����
        quickSort(arr, j+1, high);
    }
	
	public static Events[] Q(Events[] arr, int arrcnt) {
		Queue<Events> queue = new LinkedList<Events>();
		int cntQ = 0, newarrcnt = 0;
		Events[] newarr = new Events[arrcnt];
		
		for(int cnt = 0; cnt < arrcnt; cnt++) {
			if(arr[cnt].getState().equals("���")) {
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
