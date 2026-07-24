package com.example.shoplocalbrand.controller;

import com.example.shoplocalbrand.repository.HoaDonChiTietRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HoaDonChiTietServlet", value = "/hoa-don-chi-tiet")
public class HoaDonChiTietServlet extends HttpServlet {
    private HoaDonChiTietRepository hdctRepo = new HoaDonChiTietRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idHoaDonStr = request.getParameter("idHoaDon");
            if (idHoaDonStr == null || idHoaDonStr.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/hoa-don");
                return;
            }

            int idHoaDon = Integer.parseInt(idHoaDonStr);
            request.setAttribute("idHoaDonGoc", idHoaDon);
            request.setAttribute("listHDCT", hdctRepo.getByHoaDonId(idHoaDon));

            request.getRequestDispatcher("/hoa-don-chi-tiet.jsp").forward(request, response);

        } catch (Exception e) {
            // Lệnh in lỗi ra màn hình nếu có sai sót
            response.setContentType("text/html;charset=UTF-8");
            e.printStackTrace(response.getWriter());
        }
    }
}
