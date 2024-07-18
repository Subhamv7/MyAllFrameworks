package com.crm.contactTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContactwithPhoneNumber_DP_test {
	@Test (dataProvider =  "getData")
	public void createContactTest(String firstname, String lastName, long phoneNumber) {
		System.out.println("Firstname "+firstname+" lastmname "+lastName);
	}

	@DataProvider
	public Object[][] getData(){
		Object[][] objArr = new Object[3][3];
		objArr[0][0]="Asha";
		objArr[0][1]="R";
		objArr[0][2]= 9876567122l;
		
		objArr[1][0]="Chethan";
		objArr[1][1]="SD";
		objArr[1][2]= 9876567121l;
		
		objArr[2][0]="Kavya";
		objArr[2][1]="N";
		objArr[2][2]= 9876567120l;
		
		return objArr;
	}
}
