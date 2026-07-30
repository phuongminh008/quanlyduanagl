package com.example.shoplocalbrand.repository;

import com.example.shoplocalbrand.model.KhachHang;
import com.example.shoplocalbrand.util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhachHangRepository {

    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new KhachHang(rs.getInt("id"), rs.getString("ma_kh"),
                        rs.getString("ten_kh"), rs.getString("sdt"),
                        rs.getString("email"), rs.getString("gioi_tinh"),
                        rs.getInt("trang_thai")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public KhachHang findById(int id) {
        String sql = "SELECT * FROM khach_hang WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return new KhachHang(rs.getInt("id"), rs.getString("ma_kh"),
                    rs.getString("ten_kh"), rs.getString("sdt"),
                    rs.getString("email"), rs.getString("gioi_tinh"), rs.getInt("trang_thai"));
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean insert(KhachHang kh) {
        String sql = "INSERT INTO khach_hang (ma_kh, ten_kh, sdt, email, gioi_tinh, trang_thai) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getMaKh());
            ps.setString(2, kh.getTenKh());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getEmail());
            ps.setString(5, kh.getGioiTinh());
            ps.setInt(6, kh.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean update(KhachHang kh) {
        String sql = "UPDATE khach_hang SET ma_kh=?, ten_kh=?, sdt=?, email=?, gioi_tinh=?, trang_thai=? WHERE id=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getMaKh());
            ps.setString(2, kh.getTenKh());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getEmail());
            ps.setString(5, kh.getGioiTinh());
            ps.setInt(6, kh.getTrangThai());
            ps.setInt(7, kh.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM khach_hang WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

}