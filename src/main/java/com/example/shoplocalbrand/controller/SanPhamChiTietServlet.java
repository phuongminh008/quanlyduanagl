package com.example.shoplocalbrand.controller;

import com.example.shoplocalbrand.model.SanPhamChiTiet;
import com.example.shoplocalbrand.repository.SanPhamChiTietRepository;
import com.example.shoplocalbrand.repository.ThuocTinhRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SanPhamChiTietServlet", value = "/san-pham-chi-tiet")
public class SanPhamChiTietServlet extends HttpServlet {
    private SanPhamChiTietRepository spctRepo = new SanPhamChiTietRepository();
    private ThuocTinhRepository ttRepo = new ThuocTinhRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");
        String idSanPhamStr = request.getParameter("idSanPham");

        // Trường hợp bấm Xóa
        if ("delete".equals(action)) {
            spctRepo.delete(Integer.parseInt(idStr));
            response.sendRedirect(request.getContextPath() + "/san-pham-chi-tiet?idSanPham=" + idSanPhamStr);
            return;
        }
        // Trường hợp bấm Sửa (đổ dữ liệu lên form)
        else if ("edit".equals(action)) {
            SanPhamChiTiet spctEdit = spctRepo.findById(Integer.parseInt(idStr));
            request.setAttribute("spctEdit", spctEdit);
            idSanPhamStr = String.valueOf(spctEdit.getIdSanPham());
        }

        if (idSanPhamStr == null) {
            response.sendRedirect(request.getContextPath() + "/san-pham");
            return;
        }

        int idSanPham = Integer.parseInt(idSanPhamStr);
        request.setAttribute("idSanPhamGoc", idSanPham);
        request.setAttribute("listMauSac", ttRepo.getAllMauSac());
        request.setAttribute("listKichThuoc", ttRepo.getAllKichThuoc());
        request.setAttribute("listSPCT", spctRepo.getBySanPhamId(idSanPham));

        request.getRequestDispatcher("/san-pham-chi-tiet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        int idSanPham = Integer.parseInt(request.getParameter("idSanPham"));

        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setIdSanPham(idSanPham);
        spct.setIdMauSac(Integer.parseInt(request.getParameter("idMauSac")));
        spct.setIdKichThuoc(Integer.parseInt(request.getParameter("idKichThuoc")));
        spct.setMaSpChiTiet(request.getParameter("maSpChiTiet"));
        spct.setSoLuong(Integer.parseInt(request.getParameter("soLuong")));
        spct.setDonGia(Double.parseDouble(request.getParameter("donGia")));
        spct.setTrangThai(Integer.parseInt(request.getParameter("trangThai")));

        if (idStr == null || idStr.isEmpty()) {
            spctRepo.insert(spct);
        } else {
            spct.setId(Integer.parseInt(idStr));
            spctRepo.update(spct);
        }

        response.sendRedirect(request.getContextPath() + "/san-pham-chi-tiet?idSanPham=" + idSanPham);
    }
}