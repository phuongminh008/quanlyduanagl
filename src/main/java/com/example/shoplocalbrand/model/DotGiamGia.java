package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DotGiamGia {
    private Integer id;
    private String maDotGiam;
    private String tenDotGiam;
    private Integer phanTramGiam;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Integer trangThai;
}