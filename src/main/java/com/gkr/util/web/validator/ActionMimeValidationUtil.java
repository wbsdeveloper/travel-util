package com.gkr.util.web.validator;
/**
 * 验证文件类型
 * @author Nate
 *
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

public class ActionMimeValidationUtil {
	private HttpServletRequest request;
	private MultipartResolver multipartResolver;
	private String key;
	private MessageSource messagesource;
	Map<String,String>errors = new HashMap<String,String>();
	public ActionMimeValidationUtil(HttpServletRequest request,String key,MessageSource messagesource) {
		this.request = request;
		this.messagesource = messagesource;
		this.key = key;
		this.multipartResolver = new CommonsMultipartResolver();
		
		//调用验证方法
		validateMime();
	}
	
	public void validateMime() {
		String rule = null;
		if(this.multipartResolver.isMultipart(request)) {//表单被封装了			
			try {
				rule = this.messagesource.getMessage(this.key + "." + "mime.rule", null, null);
			} catch (Exception e) {
				rule = this.messagesource.getMessage("mime.rule", null, null);
			}
		}
		if(request instanceof DefaultMultipartHttpServletRequest) {
			//上传请求的request

			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			//取得请求上传的文件
			Map<String,MultipartFile> fileMap = mRequest.getFileMap();
			if(fileMap.size()>0) {
				Iterator<Map.Entry<String, MultipartFile>> iterator = fileMap.entrySet().iterator();
				while(iterator.hasNext()) {
					Map.Entry<String, MultipartFile> me = iterator.next();
					if(me.getValue().getSize()>0) {//表示文件有内容
						if(!ValueRuleValidator.isMime(rule,me.getValue().getContentType())) {
							this.errors.put(me.getKey(),this.messagesource.getMessage("validation.mime.msg",null,null));
						}
					}
				}
			}
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	

}

