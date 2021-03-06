package zml.javaexcel;



import java.io.File;
import java.io.IOException;
import java.util.Date;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.junit.Test;

public class javaExcelTest {

	@Test
	public void jxlExport() throws IOException, RowsExceededException, WriteException {
		// 获得输出流，该输出流的输出介质是客户端浏览器
		/*
		 * OutputStream output=response.getOutputStream(); response.reset();
		 * response.setHeader("Content-disposition",
		 * "attachment;           filename=temp.xls");
		 * response.setContentType("application/msexcel");
		 */

		File file = new File("./jxlExcel.xls");
		WritableWorkbook wk = Workbook.createWorkbook(file);
		// /创建可写入的Excel工作表
		WritableSheet sheet = wk.createSheet("成绩表", 0);
		// 把单元格（column, row）到单元格（column1, row1）进行合并。
		// mergeCells(column, row, column1, row1);
		sheet.mergeCells(0, 0, 4, 0);// 单元格合并方法

		// 创建WritableFont 字体对象，参数依次表示黑体、字号12、粗体、非斜体、不带下划线、亮蓝色
		WritableFont titleFont = new WritableFont(
				WritableFont.createFont("黑体"), 12, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.LIGHT_BLUE);

		// 创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
		WritableCellFormat titleFormat = new WritableCellFormat();
		// 设置字体格式
		titleFormat.setFont(titleFont);
		// 设置文本水平居中对齐
		titleFormat.setAlignment(Alignment.CENTRE);
		// 设置文本垂直居中对齐
		titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 设置背景颜色
		titleFormat.setBackground(Colour.GRAY_25);
		// 设置自动换行
		titleFormat.setWrap(true);
		// 添加Label对象，参数依次表示在第一列，第一行，内容，使用的格式
		Label lab_00 = new Label(0, 0, "学员考试成绩一览表", titleFormat);
		
		// 将定义好的Label对象添加到工作表上，这样工作表的第一列第一行的内容为‘学员考试成绩一览表’并应用了titleFormat定义的样式
		sheet.addCell(lab_00);
		WritableCellFormat cloumnTitleFormat = new WritableCellFormat();
		cloumnTitleFormat.setFont(new WritableFont(WritableFont
				.createFont("宋体"), 10, WritableFont.BOLD, false));
		cloumnTitleFormat.setAlignment(Alignment.CENTRE);
		
		Label lab_01 = new Label(0, 1, "姓名", cloumnTitleFormat);
		Label lab_11 = new Label(1, 1, "班级", cloumnTitleFormat);
		Label lab_21 = new Label(2, 1, "笔试成绩", cloumnTitleFormat);
		Label lab_31 = new Label(3, 1, "上机成绩", cloumnTitleFormat);
		Label lab_41 = new Label(4, 1, "考试日期", cloumnTitleFormat);
		sheet.addCell(lab_01);
		sheet.addCell(lab_11);
		sheet.addCell(lab_21);
		sheet.addCell(lab_31);
		sheet.addCell(lab_41);
		sheet.addCell(new Label(0, 2, "李明"));
		sheet.addCell(new Label(1, 2, "As178"));
		// 定义数字格式
		NumberFormat nf = new NumberFormat("0.00");
		WritableCellFormat wcf = new WritableCellFormat(nf);
		// 类似于Label对象，区别Label表示文本数据，Number表示数值型数据
		/*Number numlab_22 = new Number(2, 2, 78, wcf);
		sheet.addCell((WritableCell) numlab_22);
		sheet.addCell(new Number(3, 2, 87, new WritableCellFormat(
				new NumberFormat("#.##"))));*/
		// 定义日期格式
		DateFormat df = new DateFormat("yyyy-MM-dd hh:mm:ss");
		// 创建WritableCellFormat对象
		WritableCellFormat datewcf = new WritableCellFormat(df);
		// 类似于Label对象，区别Label表示文本数据，DateTime表示日期型数据
		DateTime dtLab_42 = new DateTime(4, 2, new Date(), datewcf);
		sheet.addCell(dtLab_42);
		// 将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
		wk.write();
		// 操作完成时，关闭对象，释放占用的内存空间
		wk.close();

	}
}
