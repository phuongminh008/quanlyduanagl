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

    public List<HoaDon> timKiemHoaDon(String tuKhoa, String tuNgay, String denNgay, String trangThai) {
        List<HoaDon> list = new ArrayList<>();
        // Câu lệnh gốc đã được điều chỉnh JOIN để tìm theo SDT khách hàng
        StringBuilder sql = new StringBuilder("SELECT hd.* FROM hoa_don hd LEFT JOIN khach_hang kh ON hd.id_khach = kh.id WHERE 1=1 ");
        
        // 1. Nếu có nhập từ khóa (Mã HĐ hoặc SĐT khách)
        if (tuKhoa != null && !tuKhoa.trim().isEmpty()) {
            sql.append(" AND (hd.ma_hoa_don LIKE ? OR kh.sdt LIKE ?) ");
        }
        
        // 2. Nếu có chọn Trạng thái (Ví dụ: 1 là Đã thanh toán, 0 là Đã hủy)
        if (trangThai != null && !trangThai.isEmpty()) {
            sql.append(" AND hd.trang_thai = ? ");
        }
        
        // 3. Nếu có chọn Khoảng thời gian
        if (tuNgay != null && !tuNgay.isEmpty() && denNgay != null && !denNgay.isEmpty()) {
            sql.append(" AND hd.thoi_gian_mua BETWEEN ? AND ? ");
        }
        
        sql.append(" ORDER BY hd.id DESC"); // Mới nhất xếp lên đầu

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
             
            int index = 1;
            if (tuKhoa != null && !tuKhoa.trim().isEmpty()) {
                ps.setString(index++, "%" + tuKhoa + "%");
                ps.setString(index++, "%" + tuKhoa + "%");
            }
            if (trangThai != null && !trangThai.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(trangThai));
            }
            if (tuNgay != null && !tuNgay.isEmpty() && denNgay != null && !denNgay.isEmpty()) {
                // Thêm " 00:00:00" và " 23:59:59" để bao trọn trọn vẹn ngày đó
                ps.setString(index++, tuNgay + " 00:00:00");
                ps.setString(index++, denNgay + " 23:59:59");
            }

            ResultSet rs = ps.executeQuery();
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
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // 1. Cập nhật trạng thái Hóa Đơn thành Đã Thanh Toán (ví dụ: 1)
    public boolean chotHoaDon(int idHoaDon) {
        String sql = "UPDATE hoa_don SET trang_thai = 1 WHERE id = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    // 2. Trừ số lượng tồn kho của các sản phẩm đã mua
    public void truTonKho(int idHoaDon) {
        String sql = "UPDATE spct " +
                     "SET so_luong = spct.so_luong - hdct.so_luong " +
                     "FROM san_pham_chi_tiet spct " +
                     "JOIN hoa_don_chi_tiet hdct ON spct.id = hdct.id_san_pham_chi_tiet " +
                     "WHERE hdct.id_hoa_don = ?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}