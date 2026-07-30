package com.example.shoplocalbrand.controller;

import com.example.shoplocalbrand.model.KhachHang;
import com.example.shoplocalbrand.repository.KhachHangRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "KhachHangServlet", value = "/khach-hang")
public class KhachHangServlet extends HttpServlet {
    private KhachHangRepository khRepo = new KhachHangRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("delete".equals(action)) {
            khRepo.delete(Integer.parseInt(idStr));
            response.sendRedirect(request.getContextPath() + "/khach-hang");
            return;
        } else if ("edit".equals(action)) {
            request.setAttribute("khEdit", khRepo.findById(Integer.parseInt(idStr)));
        }

        request.setAttribute("listKhachHang", khRepo.getAll());
        request.getRequestDispatcher("/khach-hang.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        KhachHang kh = new KhachHang();
        kh.setMaKh(request.getParameter("maKh"));
        kh.setTenKh(request.getParameter("tenKh"));
        kh.setSdt(request.getParameter("sdt"));
        kh.setEmail(request.getParameter("email"));
        kh.setGioiTinh(request.getParameter("gioiTinh"));
        kh.setTrangThai(Integer.parseInt(request.getParameter("trangThai")));

        if (idStr == null || idStr.isEmpty()) {
            khRepo.insert(kh);
        } else {
            kh.setId(Integer.parseInt(idStr));
            khRepo.update(kh);
        }
        response.sendRedirect(request.getContextPath() + "/khach-hang");
    }
}