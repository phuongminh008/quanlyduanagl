package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MauSac {
    private Integer id;
    private String maMauSac;
    private String tenMau;
    private Integer trangThai;
}
