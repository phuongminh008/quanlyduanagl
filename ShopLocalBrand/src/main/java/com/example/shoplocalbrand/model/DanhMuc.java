package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DanhMuc {
    private Integer id;
    private String maDanhMuc;
    private String tenDanhMuc;
    private Integer trangThai;
}