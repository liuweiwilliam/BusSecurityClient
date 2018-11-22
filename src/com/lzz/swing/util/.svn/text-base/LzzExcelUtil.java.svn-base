package com.lzz.swing.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LzzExcelUtil {
	public static String getCellValue(Cell cell){
		String value= null;
		switch (cell.getCellType()) {
	      case HSSFCell.CELL_TYPE_NUMERIC: // 数字
	          //如果为时间格式的内容
	          if (HSSFDateUtil.isCellDateFormatted(cell)) {      
	             //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
	             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	             value=sdf.format(HSSFDateUtil.getJavaDate(cell.
	             getNumericCellValue())).toString();                                 
	               break;
	           } else {
	               value = cell.getNumericCellValue() + "";
	           }
	          break;
	      case HSSFCell.CELL_TYPE_STRING: // 字符串
	          value = cell.getStringCellValue();
	          break;
	      case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
	          value = cell.getBooleanCellValue() + "";
	          break;
	      case HSSFCell.CELL_TYPE_FORMULA: // 公式
	          value = cell.getCellFormula() + "";
	          break;
	      case HSSFCell.CELL_TYPE_BLANK: // 空值
	          value = "";
	          break;
	      case HSSFCell.CELL_TYPE_ERROR: // 故障
	          value = null;
	          break;
	      default:
	          value = null;
	          break;
		}
		
		return value;
	}

	/**
	 * 解析excel文件
	 * @param worksheet
	 * @param valueFilter
	 * @param start_row
	 * @return
	 */
	public static JSONObject parseExcelToJSON(XSSFSheet worksheet, String[] valueFilter, int start_row) {
		JSONObject rslt = new JSONObject();
		rslt.put("success", true);
		JSONArray data = new JSONArray();
		
		int last_row_num = worksheet.getLastRowNum()-start_row;
		if(last_row_num<=0){
			rslt.put("success", false);
			rslt.put("errMsg", "未找到有效数据！");
		}else{
			boolean fromat_error = false;
			for(int col=0; col<valueFilter.length; col++){
				Cell cell =  worksheet.getRow(start_row).getCell(col);
				String value = cell.getStringCellValue();
				JSONObject filter = JSONObject.fromObject(valueFilter[col]);
				if(!value.equals(filter.getString("name"))){
					fromat_error = true;
					break;
				}
			}
			
			if(worksheet.getRow(start_row).getPhysicalNumberOfCells()!=valueFilter.length
					|| fromat_error){
				rslt.put("success", false);
				rslt.put("errMsg", "数据表格式错误！");
			}else{
				for (int row=start_row+1; row<=worksheet.getLastRowNum(); row++) {
					JSONObject sin = new JSONObject();
					for(int col=0; col<valueFilter.length; col++){
						JSONObject filter = JSONObject.fromObject(valueFilter[col]);
						Cell cell =  worksheet.getRow(row).getCell(col);
						if(null==cell) break;
						String value = LzzExcelUtil.getCellValue(cell);
						sin.put(filter.getString("parName"), value);
					}
					if(!sin.isEmpty()){
						data.add(sin);
					}
				}
			}
		}
		
		rslt.put("data", data);
		return rslt;
	}
	
	/**
	 * 创建excel，并写指定范围里的数据值（连续的）
	 * @param file_path excel文件路径
	 * @param startX  开始写的位置的行号
	 * @param startY 开始写的位置的列号
	 * @param endX 结束写的位置的行号
	 * @param endY 结束写的位置的列号
	 * @param values indices对应的字符数据值
	 */
	public static HSSFWorkbook createExcelByRange(
			String file_path, 
			int startX, 
			int startY, 
			int endX, 
			int endY, 
			List<String> values) {
		List<Hashtable<String, Integer>> indices = getRowColRange(startX, startY, endX, endY);
		return createExcelByPoses(file_path, indices, values);
	}
	
	/**
	 * 创建excel，并写指定范围里的数据值（连续的）
	 * @param file_path excel文件路径
	 * @param startX  开始写的位置的行号
	 * @param startY 开始写的位置的列号
	 * @param endX 结束写的位置的行号
	 * @param endY 结束写的位置的列号
	 * @param values indices对应的字符数据值
	 */
	public static HSSFWorkbook createExcelFromStart(
			String file_path, 
			int col, 
			List<String> values) {
		List<Hashtable<String, Integer>> indices = getRowColRange(0, 0, (values.size()/col)-1, col-1);
		return createExcelByPoses(file_path, indices, values);
	}
	
	/**
	 * 创建excel并指定单个位置的数据值
	 * @param file_path excel文件路径
	 * @param x	指定的行号
	 * @param y	指定的列号
	 * @param value 要写入的值
	 */
	public static HSSFWorkbook createExcelByPos(
			String file_path, 
			int x,
			int y, 
			String value) {
		List<String> values = new ArrayList<String>();
		values.add(value);
		List<Hashtable<String, Integer>> indices = getRowColRange(x, y, x, y);
		return createExcelByPoses(file_path, indices, values);
	}
	
	/**
	 * 创建excel并指定单个位置的数据值
	 * @param file_path excel文件路径
	 * @param x	指定的行号
	 * @param y	指定的列号
	 * @param value 要写入的值
	 */
	private static HSSFWorkbook createExcelByPoses(
			String file_path, 
			List<Hashtable<String, Integer>> indices,
			List<String> values) {
		if (indices.size()!=values.size()) return null;
		HSSFWorkbook xlsx_workbook = null;
		try {
			File export_file = new File(file_path);
			if(!export_file.exists()){
				export_file.createNewFile();
			}
			// convert it into a POI object
			
			xlsx_workbook = new HSSFWorkbook();
			xlsx_workbook.createSheet(export_file.getName());
			// read excel sheet that needs to be updated
			HSSFSheet worksheet = xlsx_workbook.getSheetAt(0);
			
			for (int i=0; i<indices.size(); i++) {
				int row = indices.get(i).get("Row");
				int column = indices.get(i).get("Column");
				String value = values.get(i);
				
				// access the cell first to update the value
				HSSFRow s_row = worksheet.getRow(row);
				if(null==s_row){
					worksheet.createRow(row);
				}
				
				Cell cell =  worksheet.getRow(row).getCell(column);
				if(null==cell){
					cell = worksheet.getRow(row).createCell(column);
				}
				// update cell value
				cell.setCellValue(value);
			}
			
			// open FileOutputStream to write updates
			FileOutputStream output_file =new FileOutputStream(new File(file_path));
			// write changes
			xlsx_workbook.write(output_file);
			// close the stream
			output_file.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return xlsx_workbook;
	}
	
	/**
	 * 获取excel操作的范围
	 * @param x1  开始写的位置的行号
	 * @param y1 开始写的位置的列号
	 * @param x2 结束写的位置的行号
	 * @param y2 结束写的位置的列号
	 * @return
	 */
	private static List<Hashtable<String, Integer>> getRowColRange(int x1, int y1, int x2, int y2){
		List<Hashtable<String, Integer>> indeices = new ArrayList<Hashtable<String, Integer>>();
		for(int x=x1; x<=x2; x++){
			for(int y=y1; y<=y2; y++){
				Hashtable<String, Integer> h1 = new Hashtable<String, Integer>();
				h1.put("Row", x);
				h1.put("Column", y);
				indeices.add(h1);
			}
		}
		return indeices;
	}
}
