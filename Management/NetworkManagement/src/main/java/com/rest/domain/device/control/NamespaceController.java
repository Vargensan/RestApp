package com.rest.domain.device.control;

import java.util.HashMap;
import java.util.Set;

public class NamespaceController {

	public static int getMinPositiveNumberNotInList(Set<Integer> numberSet) {
		int next = 1;
		while(numberSet.contains(next)) {
			next++;
		}
		return next;
	}
	
	public static String makeIdentifier(int number, String deviceType) {
		return deviceType+"-"+Integer.toString(number);
	}
	
	
	
	
}
