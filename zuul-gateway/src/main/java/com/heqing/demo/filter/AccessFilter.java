package com.heqing.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author heqing
 * @date 2019/7/2 18:32
 */
public class AccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("--------------------------------");

        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        System.out.println("---> send : "+request.getMethod());
        System.out.println("---> to : "+request.getRequestURL().toString());
        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            rc.setSendZuulResponse(false);
            rc.setResponseStatusCode(401);
            System.out.println("---> fail ");
        } else {
            System.out.println("---> success ");
        }

        System.out.println("--------------------------------");
        return null;
    }
}
