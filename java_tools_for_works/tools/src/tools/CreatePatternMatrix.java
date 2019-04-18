package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreatePatternMatrix {
	
	public static void main (String... args) {
		
		//—v‘f
		List<List<String>>master = new ArrayList<>();
		List<String> x = Arrays.asList("x","1","2","3");
		List<String> y = Arrays.asList("y","4","5");
		List<String> z = Arrays.asList("z","6","7","8","9");
		master.add(x);
		master.add(y);
		master.add(z);
		
		//Matrix
		String header = createHeader(master);
		String result = createMatrix(master);
		
		//Œ‹‰Êo—Í
		System.out.println(header);
		System.out.println(result);
		
	}
	
	public static String createHeader(List<List<String>> master) {
		//’è”
		String STR_RENKETU="\t";
		String STR_MARUBATU="No";
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(STR_MARUBATU);
		for(List<String> tarList:master) {
			sb.append(STR_RENKETU);
			sb.append(tarList.get(0));
		}
		return sb.toString();
	}
	
	public static String createMatrix(List<List<String>> master) {
		
		//’è”
		String STR_RENKETU="\t";
		String STR_KAIGYOU="\r\n";
		
		//index‚Ì‰Šú’lİ’è
		int[] ArrayOfIndex = new int[master.size()];
		for (int i=0; i<ArrayOfIndex.length; i++) {
			ArrayOfIndex[i] = 1;
		}
		
		StringBuilder sb = new StringBuilder();
		boolean isFinish = false;
		int no = 1;
		while(!isFinish) {
			sb.append(no);
			for (int iterator=0; iterator < master.size(); iterator++) {
				sb.append(STR_RENKETU);
				sb.append(master.get(iterator).get(ArrayOfIndex[iterator]));
				
			}
			no++;
			sb.append(STR_KAIGYOU);
			for (int i=ArrayOfIndex.length-1; i >= 0; i--) {
				if (ArrayOfIndex[i] == master.get(i).size()-1) {
					if (i==0) {
						isFinish=true;
					}
					ArrayOfIndex[i]=1;
					continue;
				} else {
					ArrayOfIndex[i]+=1;
					break;
				}
			}
		}
		return sb.toString();
		
	}

}
