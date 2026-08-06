package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang {
    private Integer id;
    private String maKh;
    private String tenKh;
    private String sdt;
    private String email;
    private String gioiTinh;
    private Integer trangThai;
}