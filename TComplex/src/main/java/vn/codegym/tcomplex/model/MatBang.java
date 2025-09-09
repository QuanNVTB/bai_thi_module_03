package vn.codegym.tcomplex.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MatBang {
    private String maMatBang;
    private String trangThai;
    private int tang;
    private String loaiMatBang;
    private BigDecimal giaTien;
    private BigDecimal dienTich;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;

    public  MatBang() {
    }

    public MatBang(String maMatBang, String trangThai, int tang,
                   String loaiMatBang, BigDecimal giaTien, BigDecimal dienTich,
                   LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        this.maMatBang = maMatBang;
        this.trangThai = trangThai;
        this.tang = tang;
        this.loaiMatBang = loaiMatBang;
        this.giaTien = giaTien;
        this.dienTich = dienTich;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMaMatBang() {
        return maMatBang;
    }

    public void setMaMatBang(String maMatBang) {
        this.maMatBang = maMatBang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    public String getLoaiMatBang() {
        return loaiMatBang;
    }

    public void setLoaiMatBang(String loaiMatBang) {
        this.loaiMatBang = loaiMatBang;
    }

    public BigDecimal getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }

    public BigDecimal getDienTich() {
        return dienTich;
    }

    public void setDienTich(BigDecimal dienTich) {
        this.dienTich = dienTich;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
