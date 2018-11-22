package com.lzz.swing.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

/**
 *处理分页
 */
public class LzzPaging {
	/**
	 *liuwei
	 *2018-04-27
	 *获取分页的结果
	 *list：需要进行分页的数据
	 *pageNo：被获取的页码
	 *pageSize：每页数据条数
	 */
	public static List get(List list,int pageNo,int pageSize){
		
		if(list!=null){
			List rsl = new ArrayList();
			int start = (pageNo-1)*pageSize;
			int end  = pageNo*pageSize-1;
			for(int i=start;i<=end;i++){
				if(i>=0&&i<list.size()){
					rsl.add(list.get(i));
				}
			}
			return rsl;
		}
		return null;
	}
	
	/**
	 *liuwei
	 *2018-04-27
	 *获取分页的结果
	 *list：需要进行分页的数据
	 *pageNo：被获取的页码
	 *pageSize：每页数据条数
	 */
	public static JSONArray get(JSONArray list, int pageNo, int pageSize){
		
		if(list!=null){
			JSONArray rsl = new JSONArray();
			int start = (pageNo-1)*pageSize;
			int end  = pageNo*pageSize-1;
			for(int i=start;i<=end;i++){
				if(i>=0&&i<list.size()){
					rsl.add(list.get(i));
				}
			}
			return rsl;
		}
		return null;
	}
	
	/**
	 *liuwei
	 *2018-04-17
	 *list：需要进行分页的数据
	 *start_index：开始的下标
	 *pageSize：获取的个数
	 */
	public static List getFromIndex(List list, int start, int pageSize){
		
		if(list!=null){
			List rsl = new ArrayList();
			int end  = start+pageSize-1;
			for(int i=start;i<=end;i++){
				if(i>=0&&i<list.size()){
					rsl.add(list.get(i));
				}
			}
			return rsl;
		}
		return null;
	}
	
	/**
	 *liuwei
	 *@since 2018-04-27
	 *获取数据的总页数
	 *list:总数据
	 *pageSize：每页长度
	 */
	public static int getPageNum(List list,int pageSize){
		if(list==null || list.size()==0){
			return 1;
		}else{
			int size = list.size();
			double num = size*1.0/pageSize;
			int zhengshu = (int) num;
			if(num-zhengshu>0){
				zhengshu++;
			}
			return zhengshu;
		}
	}
	
	/**
	 *liuwei
	 *@since 2018-04-27
	 *获取数据的总页数
	 *list:总数据
	 *pageSize：每页长度
	 */
	public static int getPageNum(JSONArray list,int pageSize){
		if(list==null || list.size()==0){
			return 1;
		}else{
			int size = list.size();
			double num = size*1.0/pageSize;
			int zhengshu = (int) num;
			if(num-zhengshu>0){
				zhengshu++;
			}
			return zhengshu;
		}
	}
	
	/**
	 * 获取列表经过分页以后还剩下多少条数据
	 * @param list
	 * @param currentNo
	 * @param size
	 */
	public static int getLeaveCount(List list, int currentNo,int size) {
		int endindex  = currentNo+size-1;//分页出去结尾一条的下标
		int endcount = endindex+1;//分页出去结尾一条对应的个数
		if(list==null){
			return 0;
		}else{
			if(list.size()-endcount>=0){
				return list.size()-endcount;
			}else{
				return 0;
			}
		}
	}
	
	
	/**
	 * 微微车险的专用分页，添加型
	 * @param list
	 * @param currentNo
	 * @param size
	 * @return
	 */
	public static List getPage(List list, int currentNo,int size){
		if(list!=null){
			List rsl = new ArrayList();
			int start = currentNo;
			int end  = currentNo+size-1;
			for(int i=start;i<=end;i++){
				if(i>=0&&i<list.size()){
					rsl.add(list.get(i));
				}
			}
			return rsl;
		}
		return null;
	}
/*	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		System.out.println(get(list,2,3));
		System.out.println(getPageNum(list, 3));
	}*/
}
