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
    private Integer trangThai; 

    
    private String tenNhanVien;
    private String tenKhachHang;
}