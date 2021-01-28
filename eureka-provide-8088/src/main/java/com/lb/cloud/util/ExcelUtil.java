package com.lb.cloud.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.lb.cloud.dto.Memu;
import com.lb.cloud.dto.Permission;
import com.lb.cloud.dto.Role;
import com.lb.cloud.dto.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
public class ExcelUtil {
	public static byte[] write2Excel(List<User> users/*根据实际需求进行修改*/){
		byte[] data=null;
		//内存字节输出流
		ByteArrayOutputStream out=null;
		
		try {
			//创建excel2003
			Workbook wb=new HSSFWorkbook();
			out=new ByteArrayOutputStream();
			//创建新的sheet
			Sheet sheet=wb.createSheet("人员信息统计表");
			//创建列数
			int columnCount=12;
			
			//合并第一列单元格
			CellRangeAddress region = new CellRangeAddress(0, 0, 0, 11);
			sheet.addMergedRegion(region);
			
			
			//创建第一栏(表头信息)
			Row row=sheet.createRow(0);
			String title="人员信息";
			Cell cell1=row.createCell(0);
			cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(0, 6000);
			sheet.setDefaultRowHeight((short) 2000);
			CellStyle style1=wb.createCellStyle();
		
			Font font1=wb.createFont();
			font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			short color1=HSSFColor.RED.index;
			font1.setColor(color1);
			style1.setFont(font1);
		    
			//设置字体大小
			font1.setFontHeightInPoints((short)20);
	        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
			
			
			/*cell1.setCellStyle(style1);
			cell1.setCellValue(title);*/
			
			//标题行高
			row.setHeight((short) 1000);
			//标题居中
			style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
			style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			//填写数据
			cell1.setCellStyle(style1);
			cell1.setCellValue(title);
			
			
			
			
			//创建第二栏
			Row row1=sheet.createRow(1);
			row1.setHeight((short) 600);
			//第一行的列名
			String[] titleArray={
					"用户名称",
					"密码",
					"性别",
					"状态",
					"创建时间",
					"更新时间",
					"创建id",
					"更新id",
					"角色",
					"权限",
					"菜单"
			};
			//循环把第一行的信息放进表格
			for(int i=0;i<columnCount;i++){
				//创建cell
				Cell cell=row1.createCell(i);
				//样式
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				sheet.setColumnWidth(i, 8000);
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				//short color=HSSFColor.RED.index;
				//font.setColor(color);
				font.setFontHeightInPoints((short)15);
				style.setFont(font);
				
				
				//
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				//填写数据
				cell.setCellStyle(style);
				cell.setCellValue(titleArray[i]);
			}
			//创建新行
			for(int j=0;j<users.size();j++){
				//从传入信息中分别获得每个信息
				User user=users.get(j);
				//创建列
				row=sheet.createRow(j+2);
				row.setHeight((short) 400);
				
				
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
				
				style.setFont(font);
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				//设置自适应
				for (int k = 0; k < titleArray.length; k++) {
		         //解决中文自适应      
		         sheet.setColumnWidth(k, titleArray[k].getBytes().length*2*256);
				 
				 }
				//解决英文自适应
				 sheet.autoSizeColumn(5); 
				 sheet.autoSizeColumn(11); 
				 
				//因为设置样式是cell设置，所以在设置样式是不能按row设置，要按cell设置 
				//分别在每一列中放入相关的信息
				//第一列的样式数据
				Cell cell0=row.createCell(0);
				cell0.setCellStyle(style);
				cell0.setCellValue(user.getName());
				
				//第二列的
				Cell cell11=row.createCell(1);
				cell11.setCellStyle(style);
				cell11.setCellValue(user.getPassword());
				
				//第三列的
				Cell cell2=row.createCell(2);
				cell2.setCellStyle(style);
				cell2.setCellValue(user.getSex());
				
				//第四列
				Cell cell3=row.createCell(3);
				cell3.setCellStyle(style);
				cell3.setCellValue(user.getState());

				//第五列
				Cell cell4=row.createCell(4);
				cell4.setCellStyle(style);
				
				cell4.setCellValue(user.getCreate_time());
				
				//第六列
				Cell cell5=row.createCell(5);
				cell5.setCellStyle(style);
				cell5.setCellValue(user.getUpdate_id());
				
				//第七列
				Cell cell6=row.createCell(6);
				cell6.setCellStyle(style);
				cell6.setCellValue(user.getCreate_id());
				
				//第八列
				Cell cell7=row.createCell(7);
				cell7.setCellStyle(style);
				cell7.setCellValue(user.getUpdate_id());
				//第九列
				if(user.getRole() !=null && user.getRole().size()>0){
					String roleName="";
					for(Role role : user.getRole()){
						roleName+=role.getName()+",";
						if(role.getPermission()!=null || role.getPermission().size()>0){
							String permissionName ="";
							for(Permission p :role.getPermission()){
								permissionName+=p.getName()+",";
								if(p.getMemu()!=null || p.getMemu().size()>0){
									String memuName="";
									for(Memu m :p.getMemu()){
										memuName+=m.getName()+",";
									}
									row.createCell(10).setCellValue(memuName.substring(0,memuName.length()-1));

								}else{
									row.createCell(10).setCellValue("无菜单");
								}
							}
							row.createCell(9).setCellValue(permissionName.substring(0,permissionName.length()-1));
						}else{
							row.createCell(9).setCellValue("无权限");
						}
					}
					row.createCell(8).setCellValue(roleName.substring(0,roleName.length()-1));

				}else{
					row.createCell(8).setCellValue("无角色");
				}
			}
			wb.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//
		data=out.toByteArray();
		return data;
	}
}
