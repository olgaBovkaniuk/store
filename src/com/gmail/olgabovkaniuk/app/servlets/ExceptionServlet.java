package com.gmail.olgabovkaniuk.app.servlets;

import com.gmail.olgabovkaniuk.app.config.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("ExceptionServlet init");
    }

    @Override
    public void destroy() {
        System.out.println("ExceptionServlet destroy.");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer statusCode = (Integer) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String servletName = (String) req.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) req.getAttribute("javax.servlet.error_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        System.out.println("Error information");
        System.out.println("The status code: " + statusCode);
        System.out.println("Servlet name: " + servletName);
        System.out.println("Exception Type: " + throwable.getClass().getName());
        System.out.println("Request URI: " + requestUri);
        throwable.printStackTrace();

        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERRORS_PAGE_PATH);
        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }
}
