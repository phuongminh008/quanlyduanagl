package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KieuDang {
    private Integer id;
    private String maKieuDang;
    private String tenKieuDang;
    private Integer trangThai;
}
