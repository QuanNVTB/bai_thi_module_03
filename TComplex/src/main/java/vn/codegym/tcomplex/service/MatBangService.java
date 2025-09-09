package vn.codegym.tcomplex.service;

import vn.codegym.tcomplex.model.MatBang;

import java.util.List;

public interface MatBangService {

        List<MatBang> findAll();
        boolean save(MatBang matBang);

        boolean update(MatBang matBang);

        boolean delete(String maMatBang);

        MatBang findById(String maMatBang);
}
