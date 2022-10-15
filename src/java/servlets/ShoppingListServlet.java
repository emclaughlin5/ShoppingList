/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eric
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession();
        if (s.getAttribute("user") == null || s.getAttribute("user").equals("")) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } else if (request.getParameter("action") != null && request.getParameter("action").equals("logout")) {
            s.setAttribute("user", null);
            s.setAttribute("list", null);
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession();
        ArrayList<String> list = (ArrayList<String>) s.getAttribute("list");

        if (request.getParameter("action").equals("register")) {
            s.setAttribute("user", request.getParameter("username"));
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
        } else if (request.getParameter("action").equals("logout")) {
            s.setAttribute("user", null);
            list.clear();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } else if (request.getParameter("action").equals("add") && request.getParameter("item") != null && !request.getParameter("item").equals("")) {
            if (list == null) {
                list = new ArrayList<String>();
            }
            list.add(request.getParameter("item"));
            s.setAttribute("list", list);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
        } else if (request.getParameter("action").equals("delete") && list.contains(request.getParameter("item"))) {
            list.remove(request.getParameter("item"));
            s.setAttribute("list", list);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
        }
    }
}
