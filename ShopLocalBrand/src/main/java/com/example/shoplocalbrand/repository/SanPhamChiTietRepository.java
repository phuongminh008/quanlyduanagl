package com.example.shoplocalbrand.repository;

import com.example.shoplocalbrand.model.SanPhamChiTiet;
import com.example.shoplocalbrand.util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamChiTietRepository {

    public List<SanPhamChiTiet> getAll() {
        List<SanPhamChiTiet> list = new ArrayList<>();
        String sql = "SELECT spct.*, ms.ten_mau, kt.ten_kich_thuoc " +
                "FROM san_pham_chi_tiet spct " +
                "JOIN mau_sac ms ON spct.id_mau_sac = ms.id " +
                "JOIN kich_thuoc kt ON spct.id_kich_thuoc = kt.id " +
                "WHERE spct.trang_thai = 1";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getInt("id"));
                spct.setIdSanPham(rs.getInt("id_san_pham"));
                spct.setIdMauSac(rs.getInt("id_mau_sac"));
                spct.setIdKichThuoc(rs.getInt("id_kich_thuoc"));
                spct.setMaSpChiTiet(rs.getString("ma_sp_chi_tiet"));
                spct.setSoLuong(rs.getInt("so_luong"));
                spct.setDonGia(rs.getDouble("don_gia"));
                spct.setTrangThai(rs.getInt("trang_thai"));
                spct.setTenMauSac(rs.getString("ten_mau"));
                spct.setTenKichThuoc(rs.getString("ten_kich_thuoc"));
                list.add(spct);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<SanPhamChiTiet> getBySanPhamId(int idSanPham) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        String sql = "SELECT spct.*, ms.ten_mau, kt.ten_kich_thuoc " +
                "FROM san_pham_chi_tiet spct " +
                "JOIN mau_sac ms ON spct.id_mau_sac = ms.id " +
                "JOIN kich_thuoc kt ON spct.id_kich_thuoc = kt.id " +
                "WHERE spct.id_san_pham = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getInt("id"));
                spct.setIdSanPham(rs.getInt("id_san_pham"));
                spct.setIdMauSac(rs.getInt("id_mau_sac"));
                spct.setIdKichThuoc(rs.getInt("id_kich_thuoc"));
                spct.setMaSpChiTiet(rs.getString("ma_sp_chi_tiet"));
                spct.setSoLuong(rs.getInt("so_luong"));
                spct.setDonGia(rs.getDouble("don_gia"));
                spct.setTrangThai(rs.getInt("trang_thai"));
                spct.setTenMauSac(rs.getString("ten_mau"));
                spct.setTenKichThuoc(rs.getString("ten_kich_thuoc"));
                list.add(spct);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean insert(SanPhamChiTiet spct) {
        String sql = "INSERT INTO san_pham_chi_tiet (id_san_pham, id_mau_sac, id_kich_thuoc, ma_sp_chi_tiet, so_luong, don_gia, trang_thai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, spct.getIdSanPham());
            ps.setInt(2, spct.getIdMauSac());
            ps.setInt(3, spct.getIdKichThuoc());
            ps.setString(4, spct.getMaSpChiTiet());
            ps.setInt(5, spct.getSoLuong());
            ps.setDouble(6, spct.getDonGia());
            ps.setInt(7, spct.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    public SanPhamChiTiet findById(int id) {
        String sql = "SELECT * FROM san_pham_chi_tiet WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getInt("id"));
                spct.setIdSanPham(rs.getInt("id_san_pham"));
                spct.setIdMauSac(rs.getInt("id_mau_sac"));
                spct.setIdKichThuoc(rs.getInt("id_kich_thuoc"));
                spct.setMaSpChiTiet(rs.getString("ma_sp_chi_tiet"));
                spct.setSoLuong(rs.getInt("so_luong"));
                spct.setDonGia(rs.getDouble("don_gia"));
                spct.setTrangThai(rs.getInt("trang_thai"));
                return spct;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean update(SanPhamChiTiet spct) {
        String sql = "UPDATE san_pham_chi_tiet SET id_mau_sac=?, id_kich_thuoc=?, ma_sp_chi_tiet=?, so_luong=?, don_gia=?, trang_thai=? WHERE id=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, spct.getIdMauSac());
            ps.setInt(2, spct.getIdKichThuoc());
            ps.setString(3, spct.getMaSpChiTiet());
            ps.setInt(4, spct.getSoLuong());
            ps.setDouble(5, spct.getDonGia());
            ps.setInt(6, spct.getTrangThai());
            ps.setInt(7, spct.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(int id) {
        // Xóa mềm: Chuyển trạng thái thành 0 (Ngừng bán) để tránh lỗi khóa ngoại hóa đơn
        String sql = "UPDATE san_pham_chi_tiet SET trang_thai = 0 WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}