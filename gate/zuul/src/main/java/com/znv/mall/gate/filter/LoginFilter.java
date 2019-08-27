package com.znv.mall.gate.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.znv.mall.authclient.service.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @Auther: yf
 * @Date: 2019-6-3
 * @Description: 权限控制过滤器
 */
@Component
@RefreshScope
@Slf4j
public class LoginFilter extends ZuulFilter {

	@Value("${auth.onoff}")
	boolean isOnOff;

	@Autowired
    IAuthService iAuthService;

	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		if (isOnOff) {
			return true;
		}
		return false;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();

        // 解决跨域
        response.setHeader("Access-Control-Allow-Origin",
                request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "x-requested-with");

		String path = request.getRequestURI()
				.substring(request.getContextPath().length())
				.replaceAll("[/]+$", "");

		if ("swagger".equalsIgnoreCase(String.valueOf(request.getSession().getAttribute("username")))) {
			log.info("swagger open..");
			ctx.setSendZuulResponse(true);
			return null;
		}
		// 校验是否白名单的请求url
		if (iAuthService.ignoreAuthentication(path)) {
			log.info("ignoreAuthentication path: {}",path);
            ctx.setSendZuulResponse(true);
            return null;
        }

        // 校验是否有session信息
		HttpSession session = request.getSession();
		if (session.getAttribute(session.getId())!=null) {
			log.info("session check Ok: {}",session.getId());
			ctx.setSendZuulResponse(true);
			return null;
		}


		// 校验当前请求是否有权限
        String token = request.getHeader("Authorization");
        if (iAuthService.hasPermission(token,path,request.getMethod())) {
			log.info("permission check ok! token: {},path: {}",token, path);
            ctx.setSendZuulResponse(true);
            return null;
        }
        try {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Please Login First.");
        } catch (IOException e) {
            e.printStackTrace();
        }
		log.warn("permission check ng! token: {},path: {}",token, path);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ctx.setSendZuulResponse(false);
        return null;

        // 以下是原过滤器逻辑
//		// 内部测试，见DebugController
//		boolean isDebug = "admin_debug"
//				.equals(session.getAttribute("username"));
//		boolean allowPath = ALLOW_PATHS.contains(path);
//		boolean matches = Pattern.matches(regixStr, path);
//		// 第三方平台登录校验
//		boolean matches3rd = Pattern.matches(regixStr3rd, path);
//
//		if (matches3rd) {// 第三方平台,可视化调用接口
//            ctx.setSendZuulResponse(true);
//            return null;
//		} else if (allowPath || matches || isDebug) {
//			ctx.setSendZuulResponse(true);
//			return null;
//		} else {
//			String token = request.getHeader("Authorization");
//			log.debug("Authorization get Authorization:" + token);
//			log.debug("request.getRequestURI()"
//					+ request.getRequestURI());
//
//			// 给可视化的固定token，持有此token将不走鉴权服务
//			if ("12CBD9B708D887A41AFAB97DAC46AAC6".equals(token)) {
//				log.debug("可视化token，跳过鉴权");
//				return null;
//			}
////					String url = authUri + "?token=" + token;
////					JSONObject result = HttprequestHelper.httpRequest(url,
////							"GET", "");
//			JSONObject result = iTokenService.verifyToken(token);
//			log.debug("Authorization get result" + result);
//			// 0表示检验鉴权成功
//			if ("0".equals(result.getString("result"))) {
//				ctx.setSendZuulResponse(true);
//				return null;
//			} else {
//				try {
//					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
//							"Please Login First.");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//				ctx.setSendZuulResponse(false);
////					ctx.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
////					ctx.setResponseBody("Please Login First.");
//				return null;
//				}
//			}

	}
}
