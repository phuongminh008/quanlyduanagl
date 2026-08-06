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

    // Thuộc tính phụ để hiển thị lên bảng giao diện S-Fashion
    private String maSanPham;
    private String tenSanPham;
    private String tenMau;
    private String tenSize;
}