package com.example.shoplocalbrand.controller;

import com.example.shoplocalbrand.repository.HoaDonRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HoaDonServlet", value = "/hoa-don")
public class HoaDonServlet extends HttpServlet {
    private HoaDonRepository hdRepo = new HoaDonRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if ("updateStatus".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int status = Integer.parseInt(request.getParameter("status"));
                hdRepo.updateTrangThai(id, status);
                response.sendRedirect(request.getContextPath() + "/hoa-don");
                return;
            }

            request.setAttribute("listHD", hdRepo.getAll());
            request.getRequestDispatcher("/hoa-don.jsp").forward(request, response);
        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            e.printStackTrace(response.getWriter());
        }
    }
}