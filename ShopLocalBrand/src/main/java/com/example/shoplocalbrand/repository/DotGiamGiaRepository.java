package com.example.shoplocalbrand.repository;

import com.example.shoplocalbrand.model.DotGiamGia;
import com.example.shoplocalbrand.util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DotGiamGiaRepository {

    public List<DotGiamGia> getAll() {
        List<DotGiamGia> list = new ArrayList<>();
        String sql = "SELECT * FROM dot_giam_gia";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DotGiamGia dgg = new DotGiamGia();
                dgg.setId(rs.getInt("id"));
                dgg.setMaDotGiam(rs.getString("ma_dot_giam"));
                dgg.setTenDotGiam(rs.getString("ten_dot_giam"));
                dgg.setPhanTramGiam(rs.getInt("phan_tram_giam"));
                dgg.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                dgg.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                dgg.setTrangThai(rs.getInt("trang_thai"));
                list.add(dgg);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean insert(DotGiamGia dgg) {
        String sql = "INSERT INTO dot_giam_gia (ma_dot_giam, ten_dot_giam, phan_tram_giam, ngay_bat_dau, ngay_ket_thuc, trang_thai) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dgg.getMaDotGiam());
            ps.setString(2, dgg.getTenDotGiam());
            ps.setInt(3, dgg.getPhanTramGiam());
            ps.setDate(4, dgg.getNgayBatDau());
            ps.setDate(5, dgg.getNgayKetThuc());
            ps.setInt(6, dgg.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean doiTrangThai(int id, int trangThaiMoi) {
        String sql = "UPDATE dot_giam_gia SET trang_thai = ? WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, trangThaiMoi);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}