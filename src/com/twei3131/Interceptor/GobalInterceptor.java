package com.twei3131.Interceptor;

import org.apache.log4j.Logger;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.JFinal;

public class GobalInterceptor implements Interceptor {

    private static final Logger log = Logger.getLogger(GobalInterceptor.class);
	
	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		 try{
	            inv.invoke(); //һ��Ҫע�⣬�Ѵ������invoke֮����Ϊ����֮ǰ�Ļ����ǻ��ָ��
	        }catch (Exception e){
	            //log ����
	            logWrite(inv, e);
	        }finally {
	            //��¼��־�����ݿ⣬��δʵ��
	            try{

	            }catch (Exception ee){

	            }
	        }
	}
	
	private void logWrite(Invocation inv,Exception e){
        //����ģʽ
        if (JFinal.me().getConstants().getDevMode()){
            e.printStackTrace();
        }
        StringBuilder sb =new StringBuilder("\n---Exception Log Begin---\n");
        sb.append("Controller:").append(inv.getController().getClass().getName()).append("\n");
        sb.append("Method:").append(inv.getMethodName()).append("\n");
        sb.append("Exception Type:").append(e.getClass().getName()).append("\n");
        sb.append("Exception Details:");
        log.error(sb.toString(),e);

    }

}
