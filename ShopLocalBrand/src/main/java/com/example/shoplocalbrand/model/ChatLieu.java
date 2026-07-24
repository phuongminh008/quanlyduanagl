package com.example.shoplocalbrand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatLieu {
    private Integer id;
    private String maChatLieu;
    private String tenChatLieu;
    private Integer trangThai;
}
