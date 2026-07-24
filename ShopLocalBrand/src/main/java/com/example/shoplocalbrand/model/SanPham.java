package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPham {
    private Integer id;
    private String maSanPham;
    private String tenSanPham;
    private Integer trangThai;
    private Integer idDanhMuc;
    private Integer idChatLieu;
    private Integer idKieuDang;
    private String tenDanhMuc;
    private String tenChatLieu;
    private String tenKieuDang;
}
