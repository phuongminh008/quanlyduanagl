package com.example.shoplocalbrand.controller;

import com.example.shoplocalbrand.model.NhanVien;
import com.example.shoplocalbrand.repository.NhanVienRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NhanVienServlet", value = "/nhan-vien")
public class NhanVienServlet extends HttpServlet {
    private NhanVienRepository nvRepo = new NhanVienRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("delete".equals(action)) {
            nvRepo.delete(Integer.parseInt(idStr));
            response.sendRedirect(request.getContextPath() + "/nhan-vien");
            return;
        } else if ("edit".equals(action)) {
            NhanVien nv = nvRepo.findById(Integer.parseInt(idStr));
            request.setAttribute("nvEdit", nv);
        }

        request.setAttribute("listNhanVien", nvRepo.getAll());
        request.getRequestDispatcher("/nhan-vien.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        NhanVien nv = new NhanVien();
        nv.setMaNv(request.getParameter("maNv"));
        nv.setTenNv(request.getParameter("tenNv"));
        nv.setGioiTinh(request.getParameter("gioiTinh"));
        nv.setSdt(request.getParameter("sdt"));
        nv.setTenDangNhap(request.getParameter("tenDangNhap")); // Chỉ dùng khi thêm mới
        nv.setIdChucVu(Integer.parseInt(request.getParameter("idChucVu")));
        nv.setTrangThai(Integer.parseInt(request.getParameter("trangThai")));

        if (idStr == null || idStr.isEmpty()) {
            nvRepo.insert(nv);
        } else {
            nv.setId(Integer.parseInt(idStr));
            nvRepo.update(nv);
        }
        response.sendRedirect(request.getContextPath() + "/nhan-vien");
    }
}