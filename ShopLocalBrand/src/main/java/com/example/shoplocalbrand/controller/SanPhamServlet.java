package com.example.shoplocalbrand.controller;

import com.example.shoplocalbrand.model.SanPham;
import com.example.shoplocalbrand.repository.DanhMucRepository;
import com.example.shoplocalbrand.repository.SanPhamRepository;
import com.example.shoplocalbrand.repository.ThuocTinhRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SanPhamServlet", value = "/san-pham")
public class SanPhamServlet extends HttpServlet {
    private SanPhamRepository spRepo = new SanPhamRepository();
    private DanhMucRepository dmRepo = new DanhMucRepository();
    private ThuocTinhRepository ttRepo = new ThuocTinhRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đẩy danh sách thuộc tính lên để làm thẻ <select>
        request.setAttribute("listDanhMuc", dmRepo.getAll());
        request.setAttribute("listChatLieu", ttRepo.getAllChatLieu());
        request.setAttribute("listKieuDang", ttRepo.getAllKieuDang());

        // Đẩy danh sách sản phẩm
        request.setAttribute("listSanPham", spRepo.getAll());
        request.getRequestDispatcher("/san-pham.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        SanPham sp = new SanPham();
        sp.setMaSanPham(request.getParameter("maSanPham"));
        sp.setTenSanPham(request.getParameter("tenSanPham"));
        sp.setIdDanhMuc(Integer.parseInt(request.getParameter("idDanhMuc")));
        sp.setIdChatLieu(Integer.parseInt(request.getParameter("idChatLieu")));
        sp.setIdKieuDang(Integer.parseInt(request.getParameter("idKieuDang")));
        sp.setTrangThai(Integer.parseInt(request.getParameter("trangThai")));

        spRepo.insert(sp);
        response.sendRedirect(request.getContextPath() + "/san-pham");
    }
}