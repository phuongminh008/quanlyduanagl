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
        // Đã sửa ORDER BY theo thoi_gian_mua
        String sql = "SELECT * FROM hoa_don ORDER BY thoi_gian_mua DESC";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("id"));
                hd.setMaHoaDon(rs.getString("ma_hoa_don"));
                hd.setIdNhanVien(rs.getInt("id_nhan_vien"));

                // Đã sửa tên cột cho khớp với SQL của bạn
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
        // Tự động sinh mã HĐ ngẫu nhiên (ví dụ: HD12345) và set trạng thái = 0, tổng tiền = 0
        String sql = "INSERT INTO hoa_don (ma_hoa_don, thoi_gian_mua, trang_thai, tong_tien) VALUES (?, GETDATE(), 0, 0)";
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

    // Thêm hàm này để chỉ lấy danh sách các hóa đơn ĐANG CHỜ (trạng thái = 0)
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
}