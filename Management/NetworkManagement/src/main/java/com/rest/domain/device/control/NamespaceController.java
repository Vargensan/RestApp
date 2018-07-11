package com.rest.domain.device.control;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class NamespaceController {

	public static int minimalPositiveNumberNotInCollection(Collection<Integer> numberSet) {
		int next = 1;
		while(numberSet.contains(next)) {
			next++;
		}
		return next;
	}
	
	public static String makeIdentifier(int number, String... deviceTypes) {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < deviceTypes.length ; i++) {
			stringBuilder.append(deviceTypes[i]);
			stringBuilder.append("/");
		}
		return stringBuilder.toString()+Integer.toString(number);
	}
	
	
	
	
}
