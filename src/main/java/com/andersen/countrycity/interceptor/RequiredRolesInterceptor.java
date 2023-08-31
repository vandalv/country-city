package com.andersen.countrycity.interceptor;

import com.andersen.countrycity.annotation.RequiredRoles;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequiredRolesInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            RequiredRoles requiredRolesAnnotation = handlerMethod.getMethod().getAnnotation(RequiredRoles.class);

            if (requiredRolesAnnotation != null) {
                String[] requiredRoles = requiredRolesAnnotation.value();
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (authentication != null) {
                    for (String requiredRole : requiredRoles) {
                        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(requiredRole))) {
                            return true;
                        }
                    }
                }

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }

        return true;
    }
}