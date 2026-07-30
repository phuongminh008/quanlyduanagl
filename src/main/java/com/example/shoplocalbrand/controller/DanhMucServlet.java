package com.example.shoplocalbrand.controller;

import com.example.shoplocalbrand.model.DanhMuc;
import com.example.shoplocalbrand.repository.DanhMucRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DanhMucServlet", value = "/danh-muc")
public class DanhMucServlet extends HttpServlet {

    private DanhMucRepository repository = new DanhMucRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("delete".equals(action)) {
            
            repository.delete(Integer.parseInt(idStr));
            response.sendRedirect(request.getContextPath() + "/danh-muc");
            return; 
        } else if ("edit".equals(action)) {
            
            DanhMuc dm = repository.findById(Integer.parseInt(idStr));
            request.setAttribute("dmEdit", dm);
        }

        
        List<DanhMuc> list = repository.getAll();
        request.setAttribute("listDanhMuc", list);
        request.getRequestDispatcher("/danh-muc.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        
        String idStr = request.getParameter("id"); 
        String ma = request.getParameter("maDanhMuc");
        String ten = request.getParameter("tenDanhMuc");
        int trangThai = Integer.parseInt(request.getParameter("trangThai"));

        DanhMuc dm = new DanhMuc();
        dm.setMaDanhMuc(ma);
        dm.setTenDanhMuc(ten);
        dm.setTrangThai(trangThai);

        if (idStr == null || idStr.isEmpty()) {
            
            repository.insert(dm);
        } else {
            
            dm.setId(Integer.parseInt(idStr));
            repository.update(dm);
        }

        response.sendRedirect(request.getContextPath() + "/danh-muc");
    }
}