package test1;

import java.io.IOException;
import java.util.Stack;

public class reorder {
	
	public static void main(String[] arg) {
		int array[]=new int[] {1,5,8,6,2,8,7,4};
		int date = 0;
		for(int i = 0;i<array.length;i++){
			if(5 == array[i]){
				 date = i;
			}
		}
	
	System.out.println(date);	
		
		
		find(array,date);
		
	/*	int res[]=findMax(array);
		
		
		for(int num:res) {
			System.out.println(num);
		}*/
		
	}
	public static int[] findMax(int[] array) {
		int len =array.length;
		Stack<Integer> st = new Stack<Integer>();
		int res[]=new int[len];
		int i=0;
		while(i<len) {
			if(st.isEmpty()||array[i]<=array[st.peek()]) {
				st.push(i);
				i++;
			}else {
				res[st.pop()]=array[i];
				
			}
		}
		while(!st.isEmpty()) {
			res[st.pop()]=-1;
		}
		return res;
	}
	public static void find(int[] array,int x){
		int  i = 0;
		int len = array.length;
		int j = len -1;
		
		int[] intArray = new int[j];
		
		while(i<=j){
			
			//  array[i];
			
		}
	}
	/*public static void find(int[] array,int x){
		int  i = 0;
		int len = array.length;
		int j = len -1;
		int res = j;
		while(i<=j){
			int mid = (i+j)/2;
			        if (x<mid){
			        	
			        	res = mid;
				            j = mid -1;
				            }
			            
			        else{
			            i = mid+1;
			            }
			   
			
		}
		 System.out.println(array[res]);
	}*/
	

}
