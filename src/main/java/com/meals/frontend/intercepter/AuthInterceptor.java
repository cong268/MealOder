package com.meals.frontend.intercepter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		if (!uri.endsWith("login") && !uri.endsWith("authentication")) {
			Integer userData = (Integer) request.getSession().getAttribute("userId");
			if (userData == null) {
				response.sendRedirect(request.getContextPath() + "/login");
				return false;
			}
			
//			if(!(Boolean)request.getSession().getAttribute("isSuperAdmin")){
//				@SuppressWarnings("unchecked")
//				Map<String,Map<String,Boolean>> map = (Map<String,Map<String,Boolean>>)request.getSession().getAttribute("accessUserMap");
//				if(uri.contains("/globalSite/")){
//					String siteId = uri.substring(uri.indexOf("/globalSite/") + "/globalSite/".length(),uri.length());
//					if(!map.get(FrontalKey.KURITA.MENU_SITES).get(FrontalKey.KURITA.PAGE_GLOBAL_SITE_VIEW)){
//						if(map.get(FrontalKey.KURITA.MENU_SITES).get(FrontalKey.KURITA.PAGE_MEASURING_POINTS)){
//							if(siteId != null && !siteId.isEmpty() && !siteId.equals("undefined")){
//								response.sendRedirect(request.getContextPath() + "/measuringPoint/"+siteId);
//							}
//							return true;
//						}else if(map.get(FrontalKey.KURITA.MENU_SITES).get(FrontalKey.KURITA.PAGE_MANUAL_ENTRY)){
//							if (siteId != null && !siteId.isEmpty() && !siteId.equals("undefined")) {
//								response.sendRedirect(request.getContextPath() + "/manualEntries/" + siteId);
//							} else {
//								return true;
//							}
//						}else{
//							return false;
//						}
//					}
//				}else if(uri.contains("/accessProfiles")){
//					if(!map.get(FrontalKey.KURITA.MENU_PARAMETERS).get(FrontalKey.KURITA.PAGE_ACCESS_PROFILES)){
//						if(map.get(FrontalKey.KURITA.MENU_PARAMETERS).get(FrontalKey.KURITA.PAGE_USER_SETTINGS)){
//							response.sendRedirect(request.getContextPath() + "/parameter/userSettings");
//						}else{
//							return false;
//						}
//					}
//				}else if(uri.contains("/analysis/")){
//					if(!map.get(FrontalKey.KURITA.MENU_ANALYSIS).get(FrontalKey.KURITA.PAGE_MY_REPORTS)){
//						return false;
//					}
//				}
//			}
		}
		return true;
	}
}
