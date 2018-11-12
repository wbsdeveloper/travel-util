package com.gkr.util.web.interceptor;

import java.lang.reflect.Method;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.MessageSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.gkr.util.web.validator.ActionMimeValidationUtil;
import com.gkr.util.web.validator.ActionValidationUtil;

/**
 * spring mvc拦截器
 * 
 * @author Nate
 *
 */
public class ValidationInterceptor implements HandlerInterceptor {

	// 注入资源
	@Resource
	private MessageSource messageSource;// 注入资源读取

	/**
	 * 在执行控制器方法之后
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	/**
	 * 在执行控制器方法时
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	/**
	 * 在执行控制器方法之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("===============调用拦截器preHandle===============");
		
		if(handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
		    Object actionObject = handlerMethod.getBean();
		    Method method = handlerMethod.getMethod();
			String key = null;
			if(actionObject.getClass().getSimpleName().contains("$$")) {
				key=actionObject.getClass().getSimpleName().substring(0,
					actionObject.getClass().getSimpleName().indexOf("$$"))+"."+method.getName();
			}else {
				key = actionObject.getClass().getSimpleName()+"."+method.getName();
			}
			String rule = null;
			try {
				rule = this.messageSource.getMessage(key,null,null); 
			} catch (Exception e) {		
			}	
			
			String errorURL = null;
			if(rule!=null) {
				//a.验证普通类型
				ActionValidationUtil avu = new ActionValidationUtil(request, rule, messageSource);
				if(avu.getErrors().size()>0) {
					request.setAttribute("errors",avu.getErrors());
					try {
						errorURL = this.messageSource.getMessage(key+".error.page",null,null);
					} catch (Exception e) {
						errorURL = this.messageSource.getMessage(".error.page",null,null);
					}
					request.getRequestDispatcher(errorURL).forward(request, response);	
					return false;  //拦截被截停
				}else {
					 //b.验证文件类型
					ActionMimeValidationUtil amvu = new ActionMimeValidationUtil(request, key, messageSource);
					if(amvu.getErrors().size()>0) {
						request.setAttribute("errors",amvu.getErrors());
						try {
							errorURL = this.messageSource.getMessage(key+".error.page",null,null);
						} catch (Exception e) {
							errorURL = this.messageSource.getMessage(".error.page",null,null);
						}
						request.getRequestDispatcher(errorURL).forward(request, response);	
						return false;//拦截被截停
					}
				}
			}
		}		
		return true; //继续放行
	}

}
