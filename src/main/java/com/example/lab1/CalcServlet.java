package com.example.lab1;

import lombok.SneakyThrows;
import lombok.val;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "calcServlet", value = "/calc-servlet")
public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> history = new ArrayList<>();
        List<String> oldHisotry = (List<String>) request.getSession().getAttribute("history");
        String s = oldHisotry.get(oldHisotry.size() - 1);
        request.getSession().setAttribute("history", history);
        odpowiedz(s, response, request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> history = new ArrayList<>();
        HttpSession session = request.getSession();
        String rezultat="";
        Double parameter1=null;
        Double parameter2=null;
        String operation=null;

        try {
            parameter1 = Double.valueOf(request.getParameter("parameter1"));
        }
        catch (NumberFormatException | NullPointerException e) {
            rezultat = rezultat + "parameter1 musi byc liczbą <br>" ;
        }
        try {
            parameter2 = Double.valueOf(request.getParameter("parameter2"));
        }
        catch (NumberFormatException | NullPointerException e) {
            rezultat = rezultat + "parameter2 musi byc liczbą <br>" ;
        }
        try {
            operation = request.getParameter("operation");
        }
        catch (NumberFormatException | NullPointerException e) {
            rezultat = rezultat + "błędny operator \n" ;
        }

        if(rezultat.equals("")){
            if(parameter2==0 && Objects.equals(operation, "/")) {
                List<String> oldHistory = (List<String>) session.getAttribute("history");
                if(oldHistory==null) oldHistory = new ArrayList<>();
                rezultat = String.valueOf(parameter1) + " " + operation + " " + String.valueOf(parameter2) + " " +
                        " = " + "Nie dziel przez zero";
                history.add(rezultat+"<br>");
                List<String> newHistory = Stream.concat(oldHistory.stream(), history.stream()).collect(Collectors.toList());
                session.setAttribute("history", newHistory);
                odpowiedz(rezultat, response, request);

            }
            else{
                List<String> oldHistory = (List<String>) session.getAttribute("history");
                if(oldHistory==null) oldHistory = new ArrayList<>();
                rezultat = String.valueOf(parameter1) + " " + operation + " " + String.valueOf(parameter2) + " " +
                        " = " + String.valueOf(oblicz(parameter1, parameter2, operation));
                history.add(rezultat+"<br>");
                List<String> newHistory = Stream.concat(oldHistory.stream(), history.stream()).collect(Collectors.toList());
                session.setAttribute("history", newHistory);
                odpowiedz(rezultat, response, request);
            }
        }
        else{
            List<String> oldHistory = (List<String>) session.getAttribute("history");
            if(oldHistory==null) oldHistory = new ArrayList<>();
            List<String> newHistory = Stream.concat(oldHistory.stream(), history.stream()).collect(Collectors.toList());
            session.setAttribute("history", newHistory);
            odpowiedz(rezultat, response, request);
        }




    }

    private Double oblicz(Double a, Double b, String operation){
        Double result;
        switch (operation){
            case "+":
                result = a+b;
                break;
            case "-":
                result = a-b;
                break;
            case "/":
                result = a/b;
                break;
            case "*":
                result = a*b;
                break;
            default: result = null;
        }
        return result;
    }
    @SneakyThrows
    private void odpowiedz(String odpowiedz, HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Calculator</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<a href=http://localhost:8080/Lab1/>Powrót do formularza</a>");
        out.println("<a href=?clear=true >czysc historie</a>");
        out.println("<h1>Wynik</h1>");
        out.println("<h2>" + odpowiedz + "</h2>");
        out.println("<h1>Historia</h1>");
        List<String> history = (List<String>) request.getSession().getAttribute("history");
        history.forEach(e -> out.println(e));
        out.println("</body>");
        out.println("</html>");
    }

}
