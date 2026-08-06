package com.example.shoplocalbrand.controller;

import com.example.shoplocalbrand.model.DotGiamGia;
import com.example.shoplocalbrand.repository.DotGiamGiaRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "DotGiamGiaServlet", value = "/khuyen-mai")
public class DotGiamGiaServlet extends HttpServlet {
    private DotGiamGiaRepository dggRepo = new DotGiamGiaRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            String idStr = request.getParameter("id");

            if ("change".equals(action)) {
                int id = Integer.parseInt(idStr);
                int trangThaiHienTai = Integer.parseInt(request.getParameter("tt"));
                int trangThaiMoi = (trangThaiHienTai == 1) ? 0 : 1;
                dggRepo.doiTrangThai(id, trangThaiMoi);
                response.sendRedirect(request.getContextPath() + "/khuyen-mai");
                return;
            }

            request.setAttribute("listDGG", dggRepo.getAll());
            request.getRequestDispatcher("/khuyen-mai.jsp").forward(request, response);

        } catch (Exception e) {
            // Lệnh này sẽ in thẳng lỗi đỏ lòm ra màn hình trình duyệt để chúng ta đọc được
            response.setContentType("text/html;charset=UTF-8");
            e.printStackTrace(response.getWriter());
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        DotGiamGia dgg = new DotGiamGia();
        dgg.setMaDotGiam(request.getParameter("maDotGiam"));
        dgg.setTenDotGiam(request.getParameter("tenDotGiam"));
        dgg.setPhanTramGiam(Integer.parseInt(request.getParameter("phanTramGiam")));
        dgg.setNgayBatDau(Date.valueOf(request.getParameter("ngayBatDau")));
        dgg.setNgayKetThuc(Date.valueOf(request.getParameter("ngayKetThuc")));
        dgg.setTrangThai(Integer.parseInt(request.getParameter("trangThai")));

        dggRepo.insert(dgg);
        response.sendRedirect(request.getContextPath() + "/khuyen-mai");
    }
}