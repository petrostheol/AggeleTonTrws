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
	
	public static ArrayList<Byte> byteList = new ArrayList<Byte>();
	public static Modem modem = new Modem();
	public static boolean flag = false;
	public static int j;
	
	public static void createImage(String code, String filename) throws Exception {
		 modem.write(code.getBytes());		 
		 for(;;) {
			 try {
				 j = modem.read();
				 if(flag==false) {
	 				 if(j!=255) {
	 					 continue;
	 				 }
	 				 else {
	 					 flag = true;
	 				 }
				 }
				 if(flag==true) {
	 				 if(j==-1)
	 					 break;
	 				 if(j == 255) {
	 					 byteList.add((byte) j);
	 					 int u = modem.read();
	 					 byteList.add((byte) u);
	 					 if(u == 217) {
	 						 break;
	 					 }
	 				 }else {
	 					 byteList.add((byte )j); 
	 				 }
				 }
				 
			 }catch (Exception e) {
				 break;
			 }
		 }
        
        
        
        byte[] img = new byte[byteList.size()];
        for(int x=0; x<byteList.size(); x++)
       	 img[x] = byteList.get(x);
        FileOutputStream test = new FileOutputStream(filename);
        test.write(img);
        test.close();
        byteList.clear();
	}

	public static void main(String[] args) throws Exception {
		
		 //Modem modem = new Modem();
         modem.setTimeout(2000);
         modem.setSpeed(100000);
         modem.open("ithaki");
         int k;
         String erc = "E9505\r";
         String image_free = "M4270\r";
         String image_error = "G5342\r";
         String gps = "P9127R=1003040\r";
         long time = 0;
         int counter = 1;
         //boolean flag = false;
        
         
//         XSSFWorkbook workbook= new XSSFWorkbook();
// 		 FileOutputStream out= new FileOutputStream(new File("./Xceldemo.xlsx"));
// 	   	 XSSFSheet Spreadsheet = workbook.createSheet("Sheet1");
// 		 //workbook.write(out);
// 		 
// 		
// 		 
// 		 Row headerRow = Spreadsheet.createRow(0);
//		 Cell cell0= headerRow.createCell(0);
//		 Cell cell1 = headerRow.createCell(1);
//		 cell1.setCellValue("packet_time");
//		 cell0.setCellValue("time");
// 		 workbook.write(out);
// 		 
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
// 		 out.close();
//		 workbook.close();
// 		 
// 		 
// 		 createImage(image_free, "/home/petros/Desktop/final");
//         
//         System.out.println("Image created, starting new image");
//         flag=false;
//         
//        createImage(image_error, "/home/petros/Desktop/final_error");
//         
//         
//        String gpsstring = "";
//        int counterlistsize=0;
//        boolean gpsflag = false;
//        ArrayList<String> gpslist = new ArrayList<String>();
//        modem.write(gps.getBytes());
// 		 for(;;) {
// 			 try { 
// 				j = modem.read();
// 				System.out.print((char)j);
// 				if(j==-1)
// 					break;
// 				if(gpsflag==false) {
// 					if(j!='$') {
// 						
// 						continue;
// 					}
// 					else
// 						gpsflag = true;
// 				}
// 				if(gpsflag == true){
//	 				 
//	 				 if(counterlistsize < 75) {
//	 					 gpsstring = gpsstring + (char)j;
//	 					 counterlistsize++;
//	 				 }
//	 				 else {
//	 					 gpslist.add(gpsstring);
//	 					 gpsstring = "";
//	 					 counterlistsize = 0;
//	 				 }
// 				
// 				
// 				
// 				}
// 			}
// 			 catch(Exception e) {
// 				 break;
// 			 }
// 		 }
// 		 
// 		 String mapcode ="P9127";
// 		 String north;
// 		 String northSec;
// 		 String east;
// 		 String eastSec;
// 		 int northSecInt, eastSecInt;
// 		 for(int i=0; i<gpslist.size(); i+=10) {
// 			 north = gpslist.get(i).substring(18, 22);
// 			 northSec = gpslist.get(i).substring(23,27);
// 			 east = gpslist.get(i).substring(31,35);
// 			 eastSec = gpslist.get(i).substring(36,40);
// 			 northSecInt = Integer.parseInt(northSec);
// 			 northSecInt = (int) Math.round(northSecInt*0.006);
// 			 eastSecInt = Integer.parseInt(eastSec);
// 			 eastSecInt = (int) Math.round(eastSecInt*0.006);
// 			 northSec = Integer.toString(northSecInt);
// 			 eastSec = Integer.toString(eastSecInt);
// 			 mapcode = mapcode+"T="+east+eastSec+north+northSec;
// 		 }
// 		 mapcode = mapcode + "\r";
// 		 
// 		 createImage(mapcode, "/home/petros/Desktop/map");
//         System.out.println(mapcode);
//         System.out.println("Map Created");
 	
 		 String ack = "Q5337\r";
 		 String nack ="R5384\r";
 		 XSSFWorkbook workbook_arq= new XSSFWorkbook();
		 FileOutputStream out_arq= new FileOutputStream(new File("./arq.xlsx"));
	   	 XSSFSheet Spreadsheet_arq = workbook_arq.createSheet("Sheet1");
 		 
 		 Row headerRow_arq = Spreadsheet_arq.createRow(0);
		 Cell cell0_arq= headerRow_arq.createCell(0);
		 Cell cell1_arq = headerRow_arq.createCell(1);
		 cell1_arq.setCellValue("packet_time");
		 cell0_arq.setCellValue("time");
 		 workbook_arq.write(out_arq);
 		 
 		 String code="";
 		 String fcs="";
 		 boolean dataflag=false;
 		 boolean fcsflag=false;
 		 ArrayList<String> datalist = new ArrayList<String>();
 		 ArrayList<String> fcslist = new ArrayList<String>();
 		 while(time<5000) {
 			 
 			 
 			 
 			 
 			 for(;;) {
 				 try {
 					 long time_now = System.currentTimeMillis();
 					 j=modem.read();
 					 if(j==-1) {
 						 
 						 modem.write(ack.getBytes());
 						 long time_later=System.currentTimeMillis();
 						 time+=time_later-time_now;
 						 break;
 					 }
 					 System.out.print((char)j);
 					 if(j=='<') {
 						 dataflag=true;
 						 j=modem.read();
 						 System.out.print((char)j);
 					 }
 					 if(dataflag==true) {
 						 if(j!='>')
 							 code=code+(char)j;
 					 }
 					 if(j=='>') {
 						 datalist.add(code);
 						 code="";
 						 dataflag=false;
 						 for(int a=0;a<2;a++) {
 							 j=modem.read();
 							 System.out.print((char)j);	
 						 }
 						 fcsflag=true;
 						 
 					 }
 					 if(fcsflag==true) {
 						 fcs=fcs+(char)j;
 						 if(fcs.length()==3) {
 							 fcslist.add(fcs);
 							 fcs="";
 							 fcsflag=false;
 						 }
 					 }
 				 }catch (Exception x) {
 					 break;
 				 }
 			 }
 		 }
 			 for(int i=0;i<datalist.size();i++)
 				 System.out.println("Data list"+i+datalist.get(i));
 			for(int i=0;i<fcslist.size();i++)
				 System.out.println("FCS list"+i+fcslist.get(i));
			 
 			modem.write(ack.getBytes());
			 for(;;) {
				 try {
					 j=modem.read();
					 if(j==-1)
						 break;
					 System.out.print((char)j);
				 }catch (Exception x) {
					 break;
				 }
			 }
 			 
 		 //}
 		 
 		 out_arq.close();
 		 workbook_arq.close();
 		 
         
		
 		 modem.close();
 		 
 		 
	}
}


