package com.example.shoplocalbrand.repository;

import com.example.shoplocalbrand.model.SanPham;
import com.example.shoplocalbrand.util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamRepository {

    public List<SanPham> getAll() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT sp.id, sp.ma_san_pham, sp.ten_san_pham, sp.trang_thai, " +
                "sp.id_danh_muc, sp.id_chat_lieu, sp.id_kieu_dang, " +
                "dm.ten_danh_muc, cl.ten_chat_lieu, kd.ten_kieu_dang " +
                "FROM san_pham sp " +
                "JOIN danh_muc dm ON sp.id_danh_muc = dm.id " +
                "JOIN chat_lieu cl ON sp.id_chat_lieu = cl.id " +
                "JOIN kieu_dang kd ON sp.id_kieu_dang = kd.id";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("id"));
                sp.setMaSanPham(rs.getString("ma_san_pham"));
                sp.setTenSanPham(rs.getString("ten_san_pham"));
                sp.setTrangThai(rs.getInt("trang_thai"));
                sp.setIdDanhMuc(rs.getInt("id_danh_muc"));
                sp.setIdChatLieu(rs.getInt("id_chat_lieu"));
                sp.setIdKieuDang(rs.getInt("id_kieu_dang"));
                
                sp.setTenDanhMuc(rs.getString("ten_danh_muc"));
                sp.setTenChatLieu(rs.getString("ten_chat_lieu"));
                sp.setTenKieuDang(rs.getString("ten_kieu_dang"));
                list.add(sp);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean insert(SanPham sp) {
        String sql = "INSERT INTO san_pham (id_danh_muc, id_chat_lieu, id_kieu_dang, ma_san_pham, ten_san_pham, trang_thai) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sp.getIdDanhMuc());
            ps.setInt(2, sp.getIdChatLieu());
            ps.setInt(3, sp.getIdKieuDang());
            ps.setString(4, sp.getMaSanPham());
            ps.setString(5, sp.getTenSanPham());
            ps.setInt(6, sp.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}