package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTiet {
    private Integer id;
    private Integer idHoaDon;
    private Integer idSpct;
    private Integer soLuong;
    private Double donGia;

    
    private String maSanPham;
    private String tenSanPham;
    private String tenMau;
    private String tenSize;
}