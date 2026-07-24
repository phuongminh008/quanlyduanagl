package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KichThuoc {
    private Integer id;
    private String maKichThuoc;
    private String tenKichThuoc;
    private Integer trangThai;
}
