package com.example.lab1;

import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "firstServlet", urlPatterns = {"/first-servlet"})
public class FirstServlet extends HttpServlet {

    private Date date1;
    private DateFormat dateFormat;

    @Override
    public void init() {
        date1 = new Date();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>First Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>First Servlet at " + request.getContextPath() +" </h1>");
        out.println("<h2>Dane serwera</h2>");
        out.println("<p>request.getServerName(): " + request.getServerName() + "</p>");
        out.println("<p>data z doGet " + new Date() + "</p>");
        out.println("<p>data z init " + dateFormat.format(date1) + "</p>");
        out.println("<p>request.getServerPort(): " + request.getServerPort() + "</p>");
        out.println("<p>request.getRemoteHost(): " + request.getRemoteHost() + "</p>");
        out.println("<p>request.getRemoteAddr(): " + request.getRemoteAddr() + "</p>");
        out.println("<h2>Szczegóły żądania</h2>");
        out.println("<p>request.getMethod(): " + request.getMethod() + " </p>");
        out.println("<p>request.getQueryString(): " + request.getQueryString() + "</p>");
        out.println("</body>");
        out.println("</html>");


    }



}
