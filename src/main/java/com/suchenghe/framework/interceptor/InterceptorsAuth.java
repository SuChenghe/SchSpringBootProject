package com.suchenghe.framework.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author SuChenghe
 * @date 2018/12/16 09:39
 */
@Component
public class InterceptorsAuth implements HandlerInterceptor {

    /**
     * 这个方法在业务处理请求之前被调用,在该方法中对用户request进行处理
     * 如果程序员决定该拦截器对请求进行拦截处理后还要调用其他的拦截器,或者业务逻辑器去进行处理,则返回true;
     * 如果程序员不需要调用其它的组件去处理请求,则返回false*，例如不会执行postHandle
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        return authCheck(httpServletRequest, httpServletResponse);
    }

    /**
     * 这个方法在业务处理器处理完成后,但是DispatcherServlet向客户端返回响应前被调用
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
            throws Exception {
        if (modelAndView != null) {
            HttpSession httpSession = httpServletRequest.getSession();
            String username = (String) httpSession.getAttribute("username");
            if (username != null) {
                modelAndView.addObject("_user", username);
            }
        }
    }

    /**
     * 这个方法在DispatcherServlet完全处理完请求后被调用,可以在该方法中进行一些资源清理的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 鉴权
     * 转发：httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest,httpServletResponse);
     *
     * @param httpServletRequest
     * @param httpServletResponse
     */
    private boolean authCheck(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws Exception {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");

        HttpSession httpSession = httpServletRequest.getSession();
        String username = (String) httpSession.getAttribute("username");
//    if (username != null) {
//      return true;
//    } else {
//      httpServletResponse.sendRedirect("/login");
//      return false;
//    }
        return true;
    }
}
