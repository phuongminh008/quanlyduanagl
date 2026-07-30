package com.example.shoplocalbrand.repository;

import com.example.shoplocalbrand.model.ThongKeDoanhThu;
import com.example.shoplocalbrand.util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeRepository {

    
    public List<ThongKeDoanhThu> getDoanhThuTheoNgay() {
        List<ThongKeDoanhThu> list = new ArrayList<>();
        String sql = "SELECT CAST(thoi_gian_mua AS DATE) AS ngay, " +
                "COUNT(id) AS so_don, " +
                "SUM(tong_tien) AS tong_doanh_thu " +
                "FROM hoa_don " +
                "WHERE trang_thai = 1 " + 
                "GROUP BY CAST(thoi_gian_mua AS DATE) " +
                "ORDER BY ngay DESC";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ThongKeDoanhThu tk = new ThongKeDoanhThu();
                tk.setNgay(rs.getDate("ngay"));
                tk.setSoDonHang(rs.getInt("so_don"));
                tk.setTongDoanhThu(rs.getDouble("tong_doanh_thu"));
                list.add(tk);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    
    public double getTongDoanhThu() {
        String sql = "SELECT SUM(tong_tien) FROM hoa_don WHERE trang_thai = 1";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}