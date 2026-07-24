package com.example.shoplocalbrand.controller;

import com.example.shoplocalbrand.repository.HoaDonRepository;
import com.example.shoplocalbrand.repository.SanPhamChiTietRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BanHangServlet", value = "/ban-hang")
public class BanHangServlet extends HttpServlet {
    private HoaDonRepository hdRepo = new HoaDonRepository();
    private SanPhamChiTietRepository spctRepo = new SanPhamChiTietRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            // Xử lý khi bấm nút "Tạo Hóa Đơn"
            if ("create".equals(action)) {
                hdRepo.taoHoaDonCho();
                response.sendRedirect(request.getContextPath() + "/ban-hang");
                return;
            }

            // Load danh sách Hóa Đơn Chờ (trạng thái = 0)
            request.setAttribute("listHoaDonCho", hdRepo.getHoaDonCho());

            // Load danh sách Sản Phẩm Chi Tiết
            request.setAttribute("listSPCT", spctRepo.getAll());

            request.getRequestDispatcher("/ban-hang.jsp").forward(request, response);
        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            e.printStackTrace(response.getWriter());
        }
    }
}