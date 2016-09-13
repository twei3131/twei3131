package com.twei3131.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.twei3131.Controller.IndexController;
import com.twei3131.Controller.TeacherController;
import com.twei3131.Controller.TestController;
import com.twei3131.Controller.UserController;
import com.twei3131.Interceptor.GobalInterceptor;
import com.twei3131.common.model._MappingKit;
import com.twei3131.handler.ContextPathHandler;

public class MainConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		me.setViewType(ViewType.JSP);
		PropKit.use("config.properties");
		me.setDevMode(PropKit.getBoolean("devMode"));
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		me.add("/",IndexController.class);
		me.add("/user",UserController.class);
		me.add("/test",TestController.class);
		me.add("/teacher",TeacherController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"),PropKit.get("user"),PropKit.get("password"));
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(true);
		_MappingKit.mapping(arp);
		me.add(c3p0Plugin);
		me.add(arp);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.add(new GobalInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
        me.add(new ContextPathHandler());
	}
	
	public static void main(String[] args){
		JFinal.start("WebRoot", 8080, "/", 5);
	}

}
