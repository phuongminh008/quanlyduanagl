package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon {
    private Integer id;
    private Integer idNhanVien;
    private Integer idKhachHang;
    private String maHoaDon;
    private Date ngayTao;
    private Double tongTien;
    private Integer trangThai; // Ví dụ: 0: Chờ thanh toán, 1: Đã thanh toán, 2: Đã hủy

    // Tên hiển thị thêm (nếu cần join bảng)
    private String tenNhanVien;
    private String tenKhachHang;
}