package com.example.shoplocalbrand.repository;

import com.example.shoplocalbrand.model.HoaDonChiTiet;
import com.example.shoplocalbrand.util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietRepository {

    public List<HoaDonChiTiet> getByHoaDonId(int idHoaDon) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        // Câu lệnh JOIN để lấy ra Tên SP, Màu Sắc, Kích Thước
        String sql = "SELECT hdct.id, hdct.id_hoa_don, hdct.id_san_pham_chi_tiet, hdct.so_luong, hdct.don_gia, " +
                "spct.ma_sp_chi_tiet AS maSanPham, sp.ten_san_pham AS tenSanPham, " +
                "ms.ten_mau AS tenMau, kt.ten_kich_thuoc AS tenSize " +
                "FROM hoa_don_chi_tiet hdct " +
                "JOIN san_pham_chi_tiet spct ON hdct.id_san_pham_chi_tiet = spct.id " +
                "JOIN san_pham sp ON spct.id_san_pham = sp.id " +
                "JOIN mau_sac ms ON spct.id_mau_sac = ms.id " +
                "JOIN kich_thuoc kt ON spct.id_kich_thuoc = kt.id " +
                "WHERE hdct.id_hoa_don = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setId(rs.getInt("id"));
                hdct.setIdHoaDon(rs.getInt("id_hoa_don"));
                hdct.setIdSpct(rs.getInt("id_san_pham_chi_tiet")); // Nhớ check tên cột này trong CSDL
                hdct.setSoLuong(rs.getInt("so_luong"));
                hdct.setDonGia(rs.getDouble("don_gia"));

                // Gán dữ liệu phụ
                hdct.setMaSanPham(rs.getString("maSanPham"));
                hdct.setTenSanPham(rs.getString("tenSanPham"));
                hdct.setTenMau(rs.getString("tenMau"));
                hdct.setTenSize(rs.getString("tenSize"));

                list.add(hdct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}