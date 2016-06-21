package com.brownfield.pss.search.apigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;


public class CustomZuulFilter extends ZuulFilter{

	@Override
	public Object run() {	
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.getRequest().setAttribute("custom-attribute", "custom-value");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
 		return 1;
	}

	@Override
	public String filterType() {
 		return "pre";
	}

	
} 