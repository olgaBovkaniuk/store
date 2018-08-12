package com.gmail.olgabovkaniuk.app.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilesServlet extends HttpServlet {

    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        request.getRequestDispatcher("/WEB-INF/pages/" + pathInfo).forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}
