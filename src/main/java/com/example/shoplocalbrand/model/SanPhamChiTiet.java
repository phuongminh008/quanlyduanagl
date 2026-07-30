package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamChiTiet {
    private Integer id;
    private Integer idSanPham;
    private Integer idMauSac;
    private Integer idKichThuoc;
    private String maSpChiTiet;
    private Integer soLuong;
    private Double donGia;
    private Integer trangThai;

    
    private String tenMauSac;
    private String tenKichThuoc;
    private String tenSanPham;
}