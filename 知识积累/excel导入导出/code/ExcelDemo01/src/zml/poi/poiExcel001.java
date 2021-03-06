package zml.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

public class poiExcel001 {

/*说明：
	    常用组件：
	    HSSFWorkbook                      excel的文档对象
	    HSSFSheet                         excel的表单
	    HSSFRow                           excel的行
	    HSSFCell                          excel的格子单元
	    
	    HSSFFont                          excel字体
	    HSSFDataFormat                    日期格式
	    HSSFHeader                        sheet头
	    HSSFFooter                        sheet尾（只有打印的时候才能看到效果）
	    样式：
	    HSSFCellStyle                       cell样式
	    辅助操作包括：
	    HSSFDateUtil                        日期
	    HSSFPrintSetup                      打印
	    HSSFErrorConstants                  错误信息表
*/
	/**
	 * POI EXCEL的导出
	 * @throws IOException 
	 */
	
	@Test
	public void poiExportExcel() throws IOException{
		//excel
		HSSFWorkbook  wb = new HSSFWorkbook();
		//sheet 
		HSSFSheet sheet01 = wb.createSheet("shee1");
		HSSFSheet sheet02 = wb.createSheet("sheet2");
		//第一行  标题
		HSSFRow row01 =sheet01.createRow(0);
		HSSFCell cel01 = row01.createCell(0);
		cel01.setCellValue("学员成绩一览表");
		sheet01.addMergedRegion(new CellRangeAddress(0,0,0,3));//合并单元格
		
		//第二行 表头
		HSSFRow row02 =sheet01.createRow(1);
		row02.createCell(0).setCellValue("姓名");
		row02.createCell(1).setCellValue("班级");
		row02.createCell(2).setCellValue("笔试成绩");
		row02.createCell(3).setCellValue("机试成绩");
	
		//数据
		for(int i=2;i<10;i++){
			HSSFRow row =sheet01.createRow(i);
			row.createCell(0).setCellValue("小明00"+i);
			row.createCell(1).setCellValue("高一00"+i);
			row.createCell(2).setCellValue(70+i);
			row.createCell(3).setCellValue(80+i);
		}
		//样式
		//行高、列高
		
		
		//输出excel文件
		FileOutputStream output = new FileOutputStream("./poiExcel.xls");
		wb.write(output);
		wb.close();
		output.close();
		
	}
	@Test
	public void impotExcel() throws Exception{
		//文件
		FileInputStream fileInput = new FileInputStream("./poiExcel.xls");
		Workbook wb = new HSSFWorkbook(fileInput);
		Sheet sheet0 = wb.getSheetAt(0);
		for(int i =0;i<sheet0.getLastRowNum();i++){
			Row row = sheet0.getRow(i);
			if(row!=null){
				//标题表头
				if(i==0){
					System.out.println(row.getCell(0).getStringCellValue());
				}
				//表头
				if(i==1){
					System.out.print(row.getCell(0).getStringCellValue()+"   ");
					System.out.print(row.getCell(1).getStringCellValue()+"   ");
					System.out.print(row.getCell(2).getStringCellValue()+"   ");
					System.out.print(row.getCell(3).getStringCellValue()+"   ");
					System.out.println();
				}
				//数据
				if(i>1){
					System.out.print(row.getCell(0).getStringCellValue()+"   ");
					System.out.print(row.getCell(1).getStringCellValue()+"   ");
					System.out.print(row.getCell(2).getNumericCellValue()+"   ");
					System.out.print(row.getCell(3).getNumericCellValue()+"   ");
					System.out.println();
				}
			}
		
		}
	}
	/**
	 * 注意：
	 *  1、文件为null的判断
	 * 	2、考虑多个sheet
	 *  3、转换成对象
	 */
	
	//扩展：
    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    @SuppressWarnings("deprecation")
	public static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

}
