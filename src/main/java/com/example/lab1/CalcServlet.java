package com.example.lab1;

import lombok.SneakyThrows;


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
                rezultat = String.valueOf(parameter1) + " " + operation + " " + String.valueOf(parameter2) + " " +
                        " = " + "Nie dziel przez zero";
                history.add(rezultat+"<br>");
                odpowiedz(rezultat, response);

            }
            else{
                rezultat = String.valueOf(parameter1) + " " + operation + " " + String.valueOf(parameter2) + " " +
                        " = " + String.valueOf(oblicz(parameter1, parameter2, operation));
                history.add(rezultat+"<br>");
                odpowiedz(rezultat, response);
            }
        }
        else{
            odpowiedz(rezultat, response);
            history.add("Błędne dane<br>");
        }

        List<String> oldHistory = (List<String>) session.getAttribute("history");
        List<String> newHistory = Stream.concat(oldHistory.stream(), history.stream()).collect(Collectors.toList());
        session.setAttribute("history", newHistory);


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
    private void odpowiedz(String odpowiedz, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Calculator</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<a href=http://localhost:8080/Lab1/>Powrót do formularza</a>");
        out.println("<a href=????>czysc historie</a>");
        out.println("<h1>Wynik</h1>");
        out.println("<h2>" + odpowiedz + "</h2>");
        out.println("<h1>Historia</h1>");
        out.println("</body>");
        out.println("</html>");
    }

}
