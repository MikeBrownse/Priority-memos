package data;

public class QuickSort {
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
 
 
    public static void main(String[] arrrgs){
//        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};  
    	Events[] arr = new Events[100];
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        
    }
}
