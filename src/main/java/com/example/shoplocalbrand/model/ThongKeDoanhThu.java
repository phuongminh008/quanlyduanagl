package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThongKeDoanhThu {
    private Date ngay;
    private Integer soDonHang;
    private Double tongDoanhThu;
}