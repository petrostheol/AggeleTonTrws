import java.io.File;
import java.io.FileOutputStream;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ithakimodem.*;

public class Poutsa {

	public static void main(String[] args) {
		
		 Modem modem = new Modem();
         modem.setTimeout(2000);
         modem.setSpeed(1000);
         modem.open("ithaki");
         int k;
         String erc = "E4516\r";
         long time = 0;
         
		
         for(;;) {
				try {
	//				op.write(erc.getBytes());
	//				ip.read();
					long time_now = System.currentTimeMillis();
					modem.write(erc.getBytes());
					k = modem.read();
					if(k==-1) break;
					//System.out.print((char)k);
					
					
					long time_later = System.currentTimeMillis();
					long package_time = time_later-time_now;
					System.out.println(package_time);
					time+=package_time;
					//System.out.println(time);
					
					if(time>=240000) {
						
						break;
					}
				} catch(Exception x) {
					break;
				}
			}
         modem.close();
		
		
		
		
		
		
		
		
		
		try {
			XSSFWorkbook workbook= new XSSFWorkbook();
			FileOutputStream out= new FileOutputStream(new File("./Xceldemo.xlsx"));
			XSSFSheet Spreadsheet = workbook.createSheet("Sheet1");
			
			Row headerRow = Spreadsheet.createRow(0);
			Cell cell= headerRow.createCell(0);
			cell.setCellValue(55);
			
			
			
			workbook.write(out);
			out.close();
			workbook.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Excel created");

	}

}
