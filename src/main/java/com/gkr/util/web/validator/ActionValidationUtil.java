package com.gkr.util.web.validator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;

/**
 * 验证普通数据类型
 * 
 * @author Nate
 *
 */
public class ActionValidationUtil {
	private HttpServletRequest request;
	private String rule;// 验证规则
	private MessageSource messagesource;	
	Map<String, String> errors = new HashMap<String, String>();
	
	public ActionValidationUtil(HttpServletRequest request, String rule, MessageSource messagesource) {
		this.request = request;
		this.rule = rule;
		this.messagesource = messagesource;
		
		//调用验证方法
		handleValidator();
	}
	
	// 实现验证方法
	private void handleValidator() {
		String ruleResult[] = this.rule.split("\\|");
		for (int x = 0; x < ruleResult.length; x++) {
			String temp[] = ruleResult[x].split("\\:");
			String parameterValue = this.request.getParameter(temp[0]);// temp[0]表是参数名，temp[1]表示数据类型
			switch(temp[1]){
			case "int":
				if (!ValueRuleValidator.isInt(parameterValue)) {
					this.errors.put(temp[0], this.messagesource.getMessage("validation.int.msg", null, null));
				}
				break;
			case "long":
				if (!ValueRuleValidator.isLong(parameterValue)) {
					this.errors.put(temp[0], this.messagesource.getMessage("validation.long.msg", null, null));
				}
				break;
			case "double":
				if (!ValueRuleValidator.isDouble(parameterValue)) {
					this.errors.put(temp[0], this.messagesource.getMessage("validation.double.msg", null, null));
				}
				break;
			case "string":
				if (!ValueRuleValidator.isString(parameterValue)) {
					this.errors.put(temp[0], this.messagesource.getMessage("validation.string.msg", null, null));
				}
				break;
			case "date":
				if (!ValueRuleValidator.isDate(parameterValue)) {
					this.errors.put(temp[0], this.messagesource.getMessage("validation.date.msg", null, null));
				}
				break;
			case "datetime":
				if (!ValueRuleValidator.isDatetime(parameterValue)) {
					this.errors.put(temp[0], this.messagesource.getMessage("validation.datetime.msg", null, null));
				}
				break;
			case "rand":
				if (!ValueRuleValidator.isRand(parameterValue,
						(String) this.request.getSession().getAttribute("rand"))) { //从Session中取出验证码
					this.errors.put(temp[0], this.messagesource.getMessage("validation.rand.msg", null, null));
				}
				break;
			case "boolean":
				if (!ValueRuleValidator.isBoolean(parameterValue)) {
					this.errors.put(temp[0], this.messagesource.getMessage("validation.boolean.msg", null, null));
				}
				break;
			}
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

}
