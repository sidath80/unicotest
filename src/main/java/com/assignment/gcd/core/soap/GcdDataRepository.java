package com.assignment.gcd.core.soap;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class GcdDataRepository {
	private static final List<Integer> gcds = new ArrayList<Integer>();

	@PostConstruct
	public void initData() {
		gcds.add(23);
		
	}
	public int getGcd() {
		Assert.notNull(gcds, "The country's name must not be null");
		return gcds.get(0);
	}
	
	public int getGcdList() {
		Assert.notNull(gcds, "The country's name must not be null");
		return gcds.get(0);
	}
	
	public int getGcdSum() {
		Assert.notNull(gcds, "The country's name must not be null");
		return gcds.get(0);
	}
	
	public  int findGcd(int... numbers) {
		 
        //Find the smallest integer in the number list
         
        int smallest = numbers[0];
 
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < smallest) {
                smallest = numbers[i];
            }
        }
 
        //Find the GCD
        while (smallest > 1) {
             
            int counter = 0;
            int modTot = 0;
             
            while (counter < numbers.length) {
 
                modTot += numbers[counter] % smallest;
                counter++;
 
            }
 
            if (modTot == 0) {
                //Return the gcd if any
                return smallest;
            }
 
            //System.out.print(" "+ smallest);
            smallest--;
 
        }
        //return -1 if there is no gcd
        return -1;
    }
 

}
