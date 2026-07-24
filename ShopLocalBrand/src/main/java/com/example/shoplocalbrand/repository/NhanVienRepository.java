package com.example.shoplocalbrand.repository;

import com.example.shoplocalbrand.model.NhanVien;
import com.example.shoplocalbrand.util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienRepository {
    public List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        // JOIN bảng nhan_vien với chuc_vu để lấy tên chức vụ
        String sql = "SELECT nv.id, nv.ma_nv, nv.ten_nv, nv.sdt, nv.gioi_tinh, cv.ten_chuc_vu, nv.trang_thai " +
                "FROM nhan_vien nv JOIN chuc_vu cv ON nv.id_chuc_vu = cv.id";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt("id"));
                nv.setMaNv(rs.getString("ma_nv"));
                nv.setTenNv(rs.getString("ten_nv"));
                nv.setSdt(rs.getString("sdt"));
                nv.setGioiTinh(rs.getString("gioi_tinh"));
                // Mượn tạm trường diaChi hoặc tạo thêm biến dto để lưu tên chức vụ hiển thị
                nv.setDiaChi(rs.getString("ten_chuc_vu"));
                nv.setTrangThai(rs.getInt("trang_thai"));
                list.add(nv);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    // 1. Lấy 1 nhân viên theo ID (dùng cho tính năng Sửa)
    public NhanVien findById(int id) {
        String sql = "SELECT * FROM nhan_vien WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt("id"));
                nv.setIdChucVu(rs.getInt("id_chuc_vu"));
                nv.setMaNv(rs.getString("ma_nv"));
                nv.setTenNv(rs.getString("ten_nv"));
                nv.setGioiTinh(rs.getString("gioi_tinh"));
                nv.setSdt(rs.getString("sdt"));
                nv.setTenDangNhap(rs.getString("ten_dang_nhap"));
                nv.setTrangThai(rs.getInt("trang_thai"));
                return nv;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    // 2. Thêm mới
    public boolean insert(NhanVien nv) {
        String sql = "INSERT INTO nhan_vien (id_chuc_vu, ma_nv, ten_nv, gioi_tinh, sdt, ten_dang_nhap, mat_khau, trang_thai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nv.getIdChucVu());
            ps.setString(2, nv.getMaNv());
            ps.setString(3, nv.getTenNv());
            ps.setString(4, nv.getGioiTinh());
            ps.setString(5, nv.getSdt());
            ps.setString(6, nv.getTenDangNhap());
            ps.setString(7, "123456"); // Mặc định mật khẩu là 123456
            ps.setInt(8, nv.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    // 3. Cập nhật
    public boolean update(NhanVien nv) {
        String sql = "UPDATE nhan_vien SET id_chuc_vu=?, ma_nv=?, ten_nv=?, gioi_tinh=?, sdt=?, trang_thai=? WHERE id=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nv.getIdChucVu());
            ps.setString(2, nv.getMaNv());
            ps.setString(3, nv.getTenNv());
            ps.setString(4, nv.getGioiTinh());
            ps.setString(5, nv.getSdt());
            ps.setInt(6, nv.getTrangThai());
            ps.setInt(7, nv.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    // 4. Xóa
    public boolean delete(int id) {
        String sql = "DELETE FROM nhan_vien WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}