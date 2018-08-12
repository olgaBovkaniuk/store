package com.gmail.olgabovkaniuk.app.servlets.filter;

import com.gmail.olgabovkaniuk.app.dao.model.UserRoleEnum;
import com.gmail.olgabovkaniuk.app.servlets.model.CommandEnum;
import com.gmail.olgabovkaniuk.app.servlets.model.UserPrincipal;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationFilter implements Filter {
    private static final Set<CommandEnum> USER_AVAILABLE = new HashSet<>();
    private static final Set<CommandEnum> ADMIN_AVAILABLE = new HashSet<>();
    private static final String LOGIN_PATH = "/index.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Authentication Filter initialized");
        USER_AVAILABLE.add(CommandEnum.USER_ORDERS);
        USER_AVAILABLE.add(CommandEnum.USER_PRODUCTS);
        USER_AVAILABLE.add(CommandEnum.ADD_ORDER);
        USER_AVAILABLE.add(CommandEnum.DELETE_USER_ORDER);

        ADMIN_AVAILABLE.add(CommandEnum.ADMIN_ORDERS);
        ADMIN_AVAILABLE.add(CommandEnum.ADMIN_PRODUCTS);
        ADMIN_AVAILABLE.add(CommandEnum.DELETE_ADMIN_ORDER);
        ADMIN_AVAILABLE.add(CommandEnum.USERS);
        ADMIN_AVAILABLE.add(CommandEnum.UPLOAD_XML);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String command = req.getParameter("command");
        if (session == null) {
            defaultRequest(request, response, chain, req, res, command);
        } else {
            UserPrincipal user = (UserPrincipal) session.getAttribute("user");
            if (user == null) {
                defaultRequest(request, response, chain, req, res, command);
            } else {
                CommandEnum commandEnum = CommandEnum.getCommand(command);
                UserRoleEnum role = user.getRole();
                switch (role) {
                    case USER:
                        if (USER_AVAILABLE.contains(commandEnum)) {
                            chain.doFilter(request, response);
                        } else {
                            session.removeAttribute("user");
                            res.sendRedirect(req.getContextPath() + LOGIN_PATH);
                        }
                        break;
                    case ADMIN:
                        if (ADMIN_AVAILABLE.contains(commandEnum)) {
                            chain.doFilter(request, response);
                        } else {
                            session.removeAttribute("user");
                            res.sendRedirect(req.getContextPath() + LOGIN_PATH);
                        }
                        break;
                    default:
                        session.removeAttribute("user");
                        res.sendRedirect(req.getContextPath() + LOGIN_PATH);
                        break;

                }
            }
        }
    }

    private void defaultRequest(ServletRequest request, ServletResponse response, FilterChain chain, HttpServletRequest req, HttpServletResponse res, String command) throws IOException, ServletException {
        if (req.getMethod().equals("POST")) {
            if (CommandEnum.getCommand(command) == CommandEnum.LOGIN) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + LOGIN_PATH);
            }
        } else {
            res.sendRedirect(req.getContextPath() + LOGIN_PATH);
        }
    }

    @Override
    public void destroy() {

    }
}
