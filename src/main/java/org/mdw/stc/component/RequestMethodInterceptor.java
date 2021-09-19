package org.mdw.stc.component;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mdw.stc.entity.log.LogEntity;
import org.mdw.stc.util.UtilDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestMethodInterceptor implements HandlerInterceptor {

	@Value("${server.servlet.context-path}")
	String nombreProyecto;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler)
			throws Exception, ParseException {
		// TODO Auto-generated method stub
		LogEntity log = new LogEntity();

		String fInput = UtilDate.getDateHour();

		log.setDateInput(fInput);
		log.setPathController("STC");
		log.setIpApplicationClient(req.getLocalAddr());
		log.setProyectName(nombreProyecto);
		req.setAttribute("log", log);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
