package com.example.shoplocalbrand.controller;

import com.example.shoplocalbrand.repository.HoaDonRepository;
import com.example.shoplocalbrand.repository.SanPhamChiTietRepository;
import com.example.shoplocalbrand.repository.HoaDonChiTietRepository;
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
    private HoaDonChiTietRepository hdctRepo = new HoaDonChiTietRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            jakarta.servlet.http.HttpSession session = request.getSession();
            
            
            if ("create".equals(action)) {
                hdRepo.taoHoaDonCho();
                response.sendRedirect(request.getContextPath() + "/ban-hang");
                return;
            }
            
            
            if ("select".equals(action)) {
                int idHd = Integer.parseInt(request.getParameter("id"));
                session.setAttribute("idHoaDonSelected", idHd);
                response.sendRedirect(request.getContextPath() + "/ban-hang");
                return;
            }
            
            
            Integer idHoaDonSelected = (Integer) session.getAttribute("idHoaDonSelected");

            
            if ("add".equals(action)) {
                if (idHoaDonSelected != null) {
                    int idSpct = Integer.parseInt(request.getParameter("idSpct"));
                    double donGia = Double.parseDouble(request.getParameter("gia"));
                    
                    hdctRepo.themVaoGioHang(idHoaDonSelected, idSpct, donGia);
                    hdRepo.capNhatTongTien(idHoaDonSelected);
                }
                response.sendRedirect(request.getContextPath() + "/ban-hang");
                return;
            }
            
            
            if ("pay".equals(action)) {
                if (idHoaDonSelected != null) {
                    hdRepo.thanhToan(idHoaDonSelected);
                    session.removeAttribute("idHoaDonSelected"); 
                }
                response.sendRedirect(request.getContextPath() + "/ban-hang");
                return;
            }

            
            request.setAttribute("listHoaDonCho", hdRepo.getHoaDonCho());
            request.setAttribute("listSPCT", spctRepo.getAll());
            
            
            if (idHoaDonSelected != null) {
                request.setAttribute("hoaDonDangChon", hdRepo.getById(idHoaDonSelected));
                request.setAttribute("listGioHang", hdctRepo.getByHoaDonId(idHoaDonSelected));
            }

            request.getRequestDispatcher("/ban-hang.jsp").forward(request, response);
            
        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            e.printStackTrace(response.getWriter());
        }
    }
}