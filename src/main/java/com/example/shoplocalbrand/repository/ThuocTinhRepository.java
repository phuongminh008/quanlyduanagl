package com.example.shoplocalbrand.repository;

import com.example.shoplocalbrand.model.ChatLieu;
import com.example.shoplocalbrand.model.KieuDang;
import com.example.shoplocalbrand.model.MauSac;
import com.example.shoplocalbrand.model.KichThuoc;
import com.example.shoplocalbrand.util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThuocTinhRepository {

    public List<ChatLieu> getAllChatLieu() {
        List<ChatLieu> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM chat_lieu");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new ChatLieu(rs.getInt("id"), rs.getString("ma_chat_lieu"), rs.getString("ten_chat_lieu"), rs.getInt("trang_thai")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<KieuDang> getAllKieuDang() {
        List<KieuDang> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM kieu_dang");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new KieuDang(rs.getInt("id"), rs.getString("ma_kieu_dang"), rs.getString("ten_kieu_dang"), rs.getInt("trang_thai")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    public List<MauSac> getAllMauSac() {
        List<MauSac> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM mau_sac");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(new MauSac(rs.getInt("id"), rs.getString("ma_mau_sac"), rs.getString("ten_mau"), rs.getInt("trang_thai")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<KichThuoc> getAllKichThuoc() {
        List<KichThuoc> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM kich_thuoc");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(new KichThuoc(rs.getInt("id"), rs.getString("ma_kich_thuoc"), rs.getString("ten_kich_thuoc"), rs.getInt("trang_thai")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}