package com.example.shoplocalbrand.controller;

import com.example.shoplocalbrand.repository.ThongKeRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DoanhThuServlet", value = "/doanh-thu")
public class DoanhThuServlet extends HttpServlet {
    private ThongKeRepository tkRepo = new ThongKeRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("tongDoanhThuToanThoiGian", tkRepo.getTongDoanhThu());
            request.setAttribute("listThongKe", tkRepo.getDoanhThuTheoNgay());

            request.getRequestDispatcher("/doanh-thu.jsp").forward(request, response);
        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            e.printStackTrace(response.getWriter());
        }
    }
}