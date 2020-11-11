package com.linuxense;
import java.io.*;
import java.util.Scanner;



public class JavaDBFReaderTest {
	public static void main( String args[]) {
		int n;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter S.No. : ");
		n=sc.nextInt();
		Object rdata[]=new Object[3];	
		DBFField fields[] = new DBFField[3];
		
		   
			fields[0] = new DBFField();
			fields[0].setName( "emp_code");
			fields[0].setDataType( DBFField.FIELD_TYPE_C);
			fields[0].setFieldLength( 10);

			fields[1] = new DBFField();
			fields[1].setField( "emp_name");
			fields[1].setDataType( DBFField.FIELD_TYPE_C);

			fields[1].setFieldLength( 20);

			fields[2] = new DBFField();
			fields[2].setField( "salary");
			fields[2].setDataType( DBFField.FIELD_TYPE_N);
			fields[2].setFieldLength( 12);
			fields[2].setDecimalCount( 2);
		

		DBFWriter writer = new DBFWriter();
		writer.setFields( fields);
		try{
			InputStream inputStream  = new FileInputStream( args[ 0]);
			DBFReader reader = new DBFReader( inputStream); 
			int numberOfFields = reader.getFieldCount();
			for( int i=0; i<numberOfFields; i++) {
				DBFField field = reader.getField( i);
				System.out.println( field.getName());
			}	
			Object []rowObjects;
			while( (rowObjects = reader.nextRecord()) != null) {
				for( int i=0; i<rowObjects.length; i++) {
					if(rowObjects[i][0]==n){
						rdata[0]=rowobjects[i][0];
						rdata[1]=rowobjects[i][1];
						rdata[2]=rowobjects[i][2];	
					}	
				}
			}
			writer.addRecord(rdata);
			inputStream.close();
			FileOutputStream fos = new FileOutputStream( args[0]);
			writer.write( fos);
			fos.close();
		}
		catch( DBFException e) {
			System.out.println( e.getMessage());
		}
		catch( IOException e) {
			System.out.println( e.getMessage());
		}
	}  
}  
