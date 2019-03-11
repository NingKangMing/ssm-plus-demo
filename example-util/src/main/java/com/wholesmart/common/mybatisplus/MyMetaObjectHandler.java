package com.wholesmart.common.mybatisplus;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.wholesmart.common.web.session.SessionUtil;

/**
 * <p>
 * 自动填充实体公共字段数据
 * </p>
 * @author kangming.ning
 * @since 2017-09-08
 * */
public class MyMetaObjectHandler extends MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		Object inputUser =null;
		try {
			inputUser = metaObject.getValue("inputUser");
			if (null == inputUser) {
				metaObject.setValue("inputUser", SessionUtil.getloginUserAccountName());
			}
		}catch(Exception e){
			//忽略 可能没这个字段
		}
		Object imputTime =null;
		try {
			imputTime = metaObject.getValue("inputTime");
			if (null == imputTime) {
				metaObject.setValue("inputTime", new Date(System.currentTimeMillis()));
			}
		}catch(Exception e) {}
		
		 
		 Object createtime=null;
		 try {
			 createtime= metaObject.getValue("createTime");
			 if(null==createtime) {
					metaObject.setValue("createTime", new Date(System.currentTimeMillis()));
				}
		 }catch(Exception e) {}
		 
		 Object createuser=null;
		 try {
			 createuser= metaObject.getValue("createUser");
			 if(null==createuser) {
					metaObject.setValue("createUser", SessionUtil.getloginUserAccountName());
				}
		 }catch(Exception e) {}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		Object lastUpdatetime=null;
		 try {
			 lastUpdatetime=getFieldValByName("updateTime",metaObject);
			 if(null==lastUpdatetime) {
				 setFieldValByName("updateTime", new Date(System.currentTimeMillis()),metaObject);
				}
		 }catch(Exception e) {}
		 
		 Object updateUser=null;
		 try {
			 updateUser= getFieldValByName("updateUser",metaObject);
			 if(null==updateUser) {
				 setFieldValByName("updateUser", SessionUtil.getloginUserAccountName(),metaObject);
				}
		 }catch(Exception e) {}
	}

}
