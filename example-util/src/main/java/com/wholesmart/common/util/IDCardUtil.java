package com.wholesmart.common.util;

import java.util.Calendar;


public class IDCardUtil {

    /**
     *
     * @param century  19xx 年用 19，20xx 年用 20
     * @param idCardNo15 待转换的 15 位身份证号码
     * @return
     */
    public static String from15to18(int century, String idCardNo15) {

        String centuryStr = "" + century;
        if(century <0 || centuryStr.length() != 2)
            throw new IllegalArgumentException("世纪数无效！应该是两位的正整数。");
        if(!(isIdCardNo(idCardNo15) && idCardNo15.length() == 15)){
            //throw new IllegalArgumentException("旧的身份证号格式不正确！");
        	System.out.println("________________旧的身份证号格式不正确！");
        	return "CardId_Error";
        }	

        int[] weight = new int[] {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};

        // 通过加入世纪码, 变成 17 为的新号码本体.
        String newNoBody = idCardNo15.substring(0, 6) + centuryStr + idCardNo15.substring(6);

        //下面算最后一位校验码

        int checkSum = 0;
        for(int i=0; i< 17; i++) {
            int ai = Integer.parseInt("" + newNoBody.charAt(i)); // 位于 i 位置的数值
            checkSum = checkSum + ai * weight[i];
        }

        int checkNum = checkSum % 11;
        String checkChar = null;

        switch(checkNum) {
            case 0: checkChar = "1"; break;
            case 1: checkChar = "0"; break;
            case 2: checkChar = "X"; break;
            default: checkChar = "" + (12 - checkNum);
        }

        return newNoBody + checkChar;

    }


    public static String from18to15(String idCardNo18) {

        if(!(isIdCardNo(idCardNo18) && idCardNo18.length() == 18)){
        	System.out.println("________________身份证号参数格式不正确！");
        	return "CardId_Error";
        	// throw new IllegalArgumentException("身份证号参数格式不正确！");
        }	

        return idCardNo18.substring(0, 6) + idCardNo18.substring(8, 17);
    }

    /**
     * 判断给定的字符串是不是符合身份证号的要求
     * @param str
     * @return
     */
    public static boolean isIdCardNo(String str) {
        if(str == null)
            return false;
        int len = str.length();
        if(len != 15 && len != 18)
            return false;
        for(int i=0; i<(len==15?15:17); i++) {
            try {
                Integer.parseInt("" + str.charAt(i));
            }catch(NumberFormatException e) {
                return false;
            }
        }
		str = str.toLowerCase();
		if(len==18&&str.endsWith("x")==false){
			try{
				String strLast = str.toLowerCase().substring(17);
				Integer.parseInt(strLast);
				return true;
			}catch(NumberFormatException x){
				return false;
			}
		}
        return true;
	}
    
    
    public static String IdCar18to15(String idCard){  
        idCard = idCard.trim();  
        StringBuffer idCard15 =new StringBuffer(idCard);  
        if(idCard!=null&&idCard.length()==18){  
            idCard15.delete(17,18);  
            idCard15.delete(6,8);  
        }  
        return idCard15.toString();  
    }  
	
    public static String IdCar15to18(String idCard){  
        idCard = idCard.trim();  
        
        if(idCard.length()>=18){
        	return idCard;
        }
        
        
        StringBuffer idCard18 =new StringBuffer(idCard);  
        //加权因子  
        //校验码值  
        char[]  checkBit = {'1','0','X','9','8','7','6','5','4','3','2'};  
        int sum = 0;  
        if(idCard!=null&&idCard.length()==18){  
            idCard18.insert(6, "19");  
            for(int index=0;index<idCard18.length();index++){  
                char c  = idCard18.charAt(index);  
                int ai = Integer.parseInt(new  Character(c).toString());  
                //加权因子的算法  
                int Wi = ((int)Math.pow(2, idCard18.length()-index))%11;  
                sum = sum+ai*Wi;  
            }  
            int indexOfCheckBit = sum%11; //取模  
            idCard18.append(checkBit[indexOfCheckBit]);  
              
        }  
        return idCard18.toString();  
    }  
    
    /**
     * 根据身份编号获取年龄
     *
     * @param idCard
     *            身份编号
     * @return 年龄
     */  
    public static int getAge(String idCard) {
        int iAge = 0;
        Calendar cal = Calendar.getInstance();
        String year = idCard.substring(6, 10);
        int iCurrYear = cal.get(Calendar.YEAR);
        iAge = iCurrYear - Integer.valueOf(year);
        return iAge;
    }
    
    
    
    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(M-男，F-女，N-未知)
     */
    public static String getGender(String idCard) {
        String sGender = "未知";

        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = "男";//男
        } else {
            sGender = "女";//女
        }
        return sGender;
    }
    
  
    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日(yyyy-MM-dd)
     */
    public static String getBirth(String idCard) {
        return idCard.substring(6, 10)+"-"+idCard.substring(10, 12)+"-"+idCard.substring(12, 14);
    }

    
	public static void main(String a[]){
		System.out.println("340701198102253949");
		System.out.println(IDCardUtil.from15to18(19,"340701810225394"));
		
		
		System.out.println(IDCardUtil.getAge("340701198102253949"));
		System.out.println(IDCardUtil.getGender("340701198102253949"));
		System.out.println(IDCardUtil.getBirth("340701198102253949"));
	}

}


