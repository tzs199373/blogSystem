package com.example.common.tool.resolve;

import com.example.common.tool.json.JsonUtils;
import com.example.common.tool.string.StringUtil;
import java.lang.reflect.Method;

public class ServiceMsgConverter 
{
	
	/**
	 * @param method
	 * @param dataVals
	 * @return
	 * @throws Exception
	 */
	public static Object[] argsTypeConvertor(Method method, Object[] dataVals)
			throws Exception
	{

		Class[] paramTypes = method.getParameterTypes();
		int paramTypesL = paramTypes.length;
		int dataValsL = dataVals.length;

		if (paramTypesL != dataValsL) 
		{
			throw new Exception("ServiceMsgConverter: paramter converter is error:" + " method paramters length:" + paramTypesL + " joinParam length:" + dataValsL);
		}

		Object[] objects = new Object[dataValsL];
		for (int i = 0; i < paramTypesL; i++) 
		{
			Class dataType = paramTypes[i];
			String typeName = dataType.getName();
			String valTypeName = dataVals[i].getClass().getName();
			String dataVal = dataVals[i].toString();
			
			if ("int".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Integer(dataVal);
			} 
			else if ("byte".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Byte(dataVal);
			} 
			else if ("short".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Short(dataVal);
			} 
			else if ("long".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal))
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Long(dataVal);
			} 
			else if ("float".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Float(dataVal);
			}
			else if ("double".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Double(dataVal);
			} 
			else if ("java.lang.Integer".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Integer(dataVal);
			}
			else if ("java.lang.Byte".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Byte(dataVal);
			}
			else if ("java.lang.Short".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Byte(dataVal);
			} 
			else if ("java.lang.Long".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Long(dataVal);
			} 
			else if ("java.lang.Float".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception(
							"ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Long(dataVal);
			} 
			else if ("java.lang.Double".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Double(dataVal);
			}
			else if ("java.lang.Float".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if (!StringUtil.checkStringIsNum(dataVal)) 
				{
					throw new Exception("ServiceMsgConverter: paramter is error," + "converType:" + typeName + ",value:" + dataVal);
				}
				objects[i] = new Float(dataVal);
			} 
			else if ("java.lang.String".equals(typeName) && checkBaseDataType(dataVals[i])) 
			{
				if(dataVal == null || "null".equals(dataVal) || "".equals(dataVal) || "\"\"".equals(dataVal))
				{
					dataVal = "";
				}
				objects[i] = dataVal;
			}
			else 
			{
				objects[i] = JsonUtils.beanToJson(dataVals[i]);
			}
		}
		return objects;
	}

	/**
	 * 判断对象是否数据基本类型
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean checkBaseDataType(Object obj) 
	{
		boolean flag = false;
		String typeName = obj.getClass().getName();
		if ("int".equals(typeName)) 
		{
			flag = true;
		}
		else if ("byte".equals(typeName)) 
		{
			flag = true;
		} 
		else if ("short".equals(typeName)) 
		{
			flag = true;
		} 
		else if ("long".equals(typeName)) 
		{
			flag = true;
		}
		else if ("float".equals(typeName)) 
		{
			flag = true;
		} 
		else if ("double".equals(typeName)) 
		{
			flag = true;
		}
		else if (("java.lang.Integer").equals(typeName)) 
		{
			flag = true;
		}
		else if ("java.lang.Byte".equals(typeName)) 
		{
			flag = true;
		}
		else if ("java.lang.Short".equals(typeName)) 
		{
			flag = true;
		} 
		else if ("java.lang.Long".equals(typeName)) 
		{
			flag = true;
		}
		else if ("java.lang.Double".equals(typeName)) 
		{
			flag = true;
		} 
		else if ("java.lang.Float".equals(typeName)) 
		{
			flag = true;
		}
		else if ("java.lang.String".equals(typeName)) 
		{
			flag = true;
		} 
		else if ("java.lang.Boolean".equals(typeName)) 
		{
			flag = true;
		}
		else if ("java.lang.Character".equals(typeName)) 
		{
			flag = true;
		}
		return flag;
	}


}
