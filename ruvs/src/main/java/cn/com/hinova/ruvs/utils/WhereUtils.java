package cn.com.hinova.ruvs.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.common.cache.Cache;

public class WhereUtils {
	
	private static Logger logger =LoggerFactory.getLogger(WhereUtils.class);

	public static void where(StringBuilder sql,List<Object> params){
		
		//参数索引
		
		int paramIndex=1;
		
		//从线程中拿到req参数
		Map<String ,String[]> paramMap=Cache.paramMapLocal.get();
		if(paramMap==null){return;}
		for (String key : paramMap.keySet()) {
			//key的格式为——符号：单位：字段1|字段2|...
			String[] keys=key.split(":");
			if(keys.length!=3){
				continue;
			}
			//判断表达式是否被支持
			if(!Config.whereSymbolSet.contains(keys[0])){
				logger.error(StringUtils.strs("条件表达式 [ ",keys[0]," ] 不被支持").toString());
				continue;
			} 
			//获取到值
			String[] paramValues =paramMap.get(key);
			Object param=null;
			//将值转换成对象
			if(paramValues.length==1){
				List<Object> objs=new ArrayList<Object>();
				//将string数组根据相关类型进行转换
				for (String str : paramValues) {
					objs.add(parserValue(keys,str));
				}
				
				if(objs.size()==1){
					//判断是否为like表达式
					if(keys[0].equals("like")){
						//like 运算符，字符串前后要有%
						if(keys[1].equals("String")){
							param=StringUtils.strs("%",(String)objs.get(0).toString().trim(),"%");
						}else{
							logger.error(StringUtils.strs("like运算符只能用到String类型上，您用到了",keys[1]));
						}
					}else{
						param=objs.get(0);
					}
				}else if(objs.size()>1){
					param=objs;
					if(!keys[0].equals("=")){
						keys[0]="in";
						logger.error(StringUtils.strs("参数字段 [ ",key+" ] 运算符异 [ ", keys[0]," ] 改变为 [ in ]"));
					}
				}
			}
			
			String[] columns=keys[2].split("\\|");
			sql.append(" and ");
			if(columns.length>1){
				sql.append(" ( ");
			}
			for (int i=0;i<columns.length;i++) {
				String column=columns[i];
				if(i>0){
					sql.append(" or ");
				}
				sql.append(column).append(" ").append(keys[0].replace('_', '='));
				if(keys[0].equals("in")||keys[0].equals("not in")){
					sql.append(" (:_").append(paramIndex++).append(") ");
					params.add(param);
				}else if(keys[0].equals("is")||keys[0].equals("is not")){
					sql.append(" ").append(param).append(" ");
				}else{
					sql.append(" :_").append(paramIndex++).append(" ");
					params.add(param);
				} 
			}
			if(columns.length>1){
				sql.append(" ) ");
			}
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static void param(Query query,List<Object> params){

		for (int i=0;i<params.size();i++) {
			Object obj=params.get(i);
			if(obj instanceof List){
				query.setParameterList("_"+(i+1), (List<Object>)obj);
			}else if(obj!=null&&obj.toString().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){

				try {
					obj=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(obj.toString());
					query.setParameter("_"+(i+1), obj);
				} catch (ParseException e) {
				}
			}else if(obj!=null&&obj.toString().matches("\\d{4}-\\d{2}-\\d{2}")){

				try {
					obj=new SimpleDateFormat("yyyy-MM-dd").parse(obj.toString());
					query.setParameter("_"+(i+1), obj);
				} catch (ParseException e) {
				}
			}else{
				query.setParameter("_"+(i+1), obj);
			}
		}
	}
		
		public static Object parserValue(String[] keys,String _value){
			Object value=null;
			if(keys[1].startsWith("Date")){
				value =DateUtils.str2date(_value);
			}else if(keys[1].startsWith("Integer")){
				value =NumberUtils.str2int(_value);
			}else if(keys[1].startsWith("Long")){
				value=NumberUtils.str2long(_value);
			}else if(keys[1].startsWith("String")){
				value=_value;
			}else if(keys[1].startsWith("Float")){
				value=NumberUtils.str2float(_value);
			}else if(keys[1].startsWith("Double")){
				value=NumberUtils.str2double(_value);
			}
			return value;
		}
}
