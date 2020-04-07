import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import ithakimodem.*;

public class Poutsa {

	public static void main(String[] args) throws Exception {
		
		 Modem modem = new Modem();
         modem.setTimeout(2000);
         modem.setSpeed(100000);
         modem.open("ithaki");
         int k,j;
         String erc = "E0079\r";
         String image_free = "M2409\r";
         String image_error = "G2810\r";
         String gps = "P3897R=1003003\r";
         long time = 0;
         int counter = 1;
         boolean flag = false;
         ArrayList<Byte> byteList = new ArrayList<Byte>();
         ArrayList<Byte> byteListError = new ArrayList<Byte>();
         
         XSSFWorkbook workbook= new XSSFWorkbook();
 		 FileOutputStream out= new FileOutputStream(new File("./Xceldemo.xlsx"));
 	   	 XSSFSheet Spreadsheet = workbook.createSheet("Sheet1");
 		 //workbook.write(out);
 		 
 		
 		 System.out.println("Excel created");
         
 		 //modem.write(erc.getBytes());
 		 Row headerRow = Spreadsheet.createRow(0);
		 Cell cell0= headerRow.createCell(0);
		 Cell cell1 = headerRow.createCell(1);
		 cell1.setCellValue("packet_time");
		 cell0.setCellValue("time");
 		 workbook.write(out);
 		 
// 		 
// 		 while(time<2000) {
// 			 //long time_now = System.currentTimeMillis();
// 			 
// 			 for(;;) {
// 				 try {
// 					 long time_now = System.currentTimeMillis();
// 					 k = modem.read();
// 					 if(k==-1) {
// 						 long time_later = System.currentTimeMillis();
// 						 modem.write(erc.getBytes());
// 						 long packet_time = time_later - time_now;
// 						 time += packet_time;
// 						 headerRow = Spreadsheet.createRow(counter);
// 						 cell0= headerRow.createCell(0);
// 						 cell1 = headerRow.createCell(1);
// 						 cell1.setCellValue(packet_time);
// 						 cell0.setCellValue(time);
// 				 		 workbook.write(out);
// 						 
// 						 counter++;
// 						 break;
// 					 }
// 					 System.out.print((char)k);
// 				 }catch (Exception x) {
// 					 break;
// 				 }
// 			 }
// 			 
// 		 }
// 		 
// 		 modem.write(image_free.getBytes());		 
// 		 for(;;) {
// 			 try {
// 				 j = modem.read();
// 				 if(flag==false) {
// 					 System.out.println(j);
//	 				 if(j!=255) {
//	 					 continue;
//	 				 }
//	 				 else {
//	 					 flag = true;
//	 				 }
// 				 }
// 				 if(flag==true) {
//	 				 if(j==-1)
//	 					 break;
//	 				 if(j == 255) {
//	 					 byteList.add((byte) j);
//	 					 int u = modem.read();
//	 					 byteList.add((byte) u);
//	 					 if(u == 217) {
//	 						 break;
//	 					 }
//	 				 }else {
//	 					 byteList.add((byte )j); 
//	 				 }
// 				 }
// 				 
// 			 }catch (Exception e) {
// 				 break;
// 			 }
// 		 }
//         
//         
//         
//         byte[] img = new byte[byteList.size()];
//         for(int x=0; x<byteList.size(); x++)
//        	 img[x] = byteList.get(x);
//         FileOutputStream test = new FileOutputStream("/home/petros/Desktop/final");
//         test.write(img);
//         test.close();
//         
//         System.out.println("Image created, starting new image");
//         flag=false;
//         
//         modem.write(image_error.getBytes());	
//         System.out.println("Write succesfull.");
// 		 for(;;) {
// 			 try {
// 				 j = modem.read();
// 				 if(flag==false) {
// 					 //System.out.println(j);
//	 				 if(j!=255) {
//	 					 continue;
//	 				 }
//	 				 else {
//	 					 flag = true;
//	 				 }
// 				 }
// 				 if(flag==true) {
// 					 //System.out.println(j);
//	 				 if(j==-1)
//	 					 break;
//	 				 if(j == 255) {
//	 					 //System.out.println(j);
//	 					 byteListError.add((byte) j);
//	 					 int u = modem.read();
//	 					 //System.out.println(u);
//	 					 byteListError.add((byte) u);
//	 					 if(u == 217) {
//	 						 break;
//	 					 }
//	 				 }else {
//	 					 //System.out.println(j);
//	 					 byteListError.add((byte )j); 
//	 				 }
// 				 }
// 				 
// 			 }catch (Exception e) {
// 				 break;
// 			 }
// 		 }
// 		 
// 		byte[] img1 = new byte[byteListError.size()];
//        for(int x=0; x<byteListError.size(); x++) {
//        	img1[x] = byteListError.get(x);
//        }
//        FileOutputStream test1 = new FileOutputStream("/home/petros/Desktop/final_error");
//        test1.write(img1);
//        test1.close();
        
        String teststring = "";
        int counterlist=0;
        boolean gpsflag = false;
        ArrayList<String> testaki = new ArrayList<String>();
        modem.write(gps.getBytes());
 		 for(;;) {
 			 try {
 				 
 				j = modem.read();
 				if(j==-1)
 					break;
 				if(j==36) {
 					gpsflag=true;
 					teststring = teststring + (char)j;
 				}
 				teststring = teststring + (char)j;
 				if((j==10 || j==13)&&(gpsflag==true)) {
 						testaki.add(teststring);
 						System.out.println(teststring);
 						teststring="";
 						gpsflag=false;
 				}
 			}
 				
 
 				//System.out.print((char)j);
 			 }catch(Exception e) {
 				 break;
 			 }
 		 }
 		System.out.println(".....");
 		 for(int i=0;i<testaki.size();i++) {
 		  System.out.print(testaki.get(i)+i);
 		 }
 		System.out.println(".....");
         out.close();
 		 workbook.close();
		
 		 modem.close();
 		 //System.out.println(teststring);
	}

}
