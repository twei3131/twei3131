package com.twei3131.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

public class ContextPathHandler extends Handler {

	@SuppressWarnings("deprecation")
	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		// TODO Auto-generated method stub
		request.setAttribute("CONTEXT_PATH", request.getContextPath());
        nextHandler.handle(target, request, response, isHandled);
	}

}
