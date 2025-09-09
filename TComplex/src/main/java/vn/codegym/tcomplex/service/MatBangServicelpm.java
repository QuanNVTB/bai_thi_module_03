package vn.codegym.tcomplex.service;

import vn.codegym.tcomplex.DAO.MatBangDAO;
import vn.codegym.tcomplex.model.MatBang;

import java.util.Collections;
import java.util.List;

public class MatBangServicelpm implements MatBangService{

    MatBangDAO matBangDAO = new MatBangDAO();

    @Override
    public List<MatBang> findAll() {
        return matBangDAO.findAll();
    }

    @Override
    public boolean save(MatBang matBang) {
        return matBangDAO.save(matBang);
    }

    @Override
    public boolean update(MatBang matBang) {
        MatBang m = findById(matBang.getMaMatBang());
        if(m != null) {
            m.setTrangThai(matBang.getTrangThai());
            m.setTang(matBang.getTang());
            m.setLoaiMatBang(matBang.getLoaiMatBang());
            m.setGiaTien(matBang.getGiaTien());
            m.setDienTich(matBang.getDienTich());
            m.setNgayBatDau(matBang.getNgayBatDau());
            m.setNgayKetThuc(matBang.getNgayKetThuc());
            return matBangDAO.update(m);
        }
        return false;
    }

    @Override
    public boolean delete(String maMatBang) {
        return matBangDAO.delete(maMatBang);
    }

    @Override
    public MatBang findById(String maMatBang) {
        MatBang matBang = matBangDAO.findById(maMatBang);
        return matBang;
    }
}