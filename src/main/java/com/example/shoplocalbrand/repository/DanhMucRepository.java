package com.example.shoplocalbrand.repository;

import com.example.shoplocalbrand.model.DanhMuc;
import com.example.shoplocalbrand.util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DanhMucRepository {

    public List<DanhMuc> getAll() {
        List<DanhMuc> list = new ArrayList<>();
        
        String sql = "SELECT id, ma_danh_muc, ten_danh_muc, trang_thai FROM danh_muc";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DanhMuc dm = new DanhMuc(
                        rs.getInt("id"),
                        rs.getString("ma_danh_muc"),
                        rs.getString("ten_danh_muc"),
                        rs.getInt("trang_thai")
                );
                list.add(dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public DanhMuc findById(int id) {
        String sql = "SELECT id, ma_danh_muc, ten_danh_muc, trang_thai FROM danh_muc WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new DanhMuc(rs.getInt("id"), rs.getString("ma_danh_muc"), rs.getString("ten_danh_muc"), rs.getInt("trang_thai"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    
    public boolean update(DanhMuc dm) {
        String sql = "UPDATE danh_muc SET ma_danh_muc = ?, ten_danh_muc = ?, trang_thai = ? WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dm.getMaDanhMuc());
            ps.setString(2, dm.getTenDanhMuc());
            ps.setInt(3, dm.getTrangThai());
            ps.setInt(4, dm.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    
    public boolean delete(int id) {
        String sql = "DELETE FROM danh_muc WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    
    public boolean insert(DanhMuc dm) {
        String sql = "INSERT INTO danh_muc (ma_danh_muc, ten_danh_muc, trang_thai) VALUES (?, ?, ?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dm.getMaDanhMuc());
            ps.setString(2, dm.getTenDanhMuc());
            ps.setInt(3, dm.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}