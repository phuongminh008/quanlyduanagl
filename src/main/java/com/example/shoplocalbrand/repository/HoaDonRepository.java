package com.example.shoplocalbrand.repository;

import com.example.shoplocalbrand.model.HoaDon;
import com.example.shoplocalbrand.util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonRepository {

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        
        String sql = "SELECT * FROM hoa_don ORDER BY thoi_gian_mua DESC";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("id"));
                hd.setMaHoaDon(rs.getString("ma_hoa_don"));
                hd.setIdNhanVien(rs.getInt("id_nhan_vien"));

                
                hd.setIdKhachHang(rs.getInt("id_khach"));
                hd.setNgayTao(rs.getDate("thoi_gian_mua"));

                hd.setTongTien(rs.getDouble("tong_tien"));
                hd.setTrangThai(rs.getInt("trang_thai"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateTrangThai(int id, int trangThaiMoi) {
        String sql = "UPDATE hoa_don SET trang_thai = ? WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, trangThaiMoi);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean taoHoaDonCho() {
        
        
        String sql = "INSERT INTO hoa_don (ma_hoa_don, thoi_gian_mua, trang_thai, tong_tien, id_nhan_vien, id_hinh_thuc) VALUES (?, GETDATE(), 0, 0, 1, 1)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            String maHDo = "HD" + (System.currentTimeMillis() % 100000);
            ps.setString(1, maHDo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public List<HoaDon> getHoaDonCho() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM hoa_don WHERE trang_thai = 0 ORDER BY thoi_gian_mua DESC";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("id"));
                hd.setMaHoaDon(rs.getString("ma_hoa_don"));
                hd.setNgayTao(rs.getDate("thoi_gian_mua"));
                hd.setTrangThai(rs.getInt("trang_thai"));
                list.add(hd);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void capNhatTongTien(int idHoaDon) {
        String sql = "UPDATE hoa_don SET tong_tien = (SELECT COALESCE(SUM(so_luong * don_gia), 0) FROM hoa_don_chi_tiet WHERE id_hoa_don = ?) WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            ps.setInt(2, idHoaDon);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void thanhToan(int idHoaDon) {
        String sql = "UPDATE hoa_don SET trang_thai = 1 WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    
    public HoaDon getById(int id) {
        String sql = "SELECT * FROM hoa_don WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("id"));
                hd.setMaHoaDon(rs.getString("ma_hoa_don"));
                hd.setTongTien(rs.getDouble("tong_tien"));
                return hd;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}