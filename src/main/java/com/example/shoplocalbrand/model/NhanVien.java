package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {
    private Integer id;
    private String maNv;
    private String tenNv;
    private String sdt;
    private String gioiTinh;
    private String diaChi;
    private String tenDangNhap;
    private Integer idChucVu;
    private Integer trangThai;
}
