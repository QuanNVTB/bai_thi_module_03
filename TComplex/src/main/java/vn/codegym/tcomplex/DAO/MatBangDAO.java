package vn.codegym.tcomplex.DAO;

import vn.codegym.tcomplex.model.MatBang;
import vn.codegym.tcomplex.util.DbConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatBangDAO {

    private static final String SQL_SELECT_ALL = "SELECT * FROM MatBang ORDER BY MaMatBang";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM MatBang WHERE MaMatBang = ?";
    private static final String SQL_INSERT = "INSERT INTO MatBang (MaMatBang, TrangThai, Tang, LoaiMatBang, GiaTien, DienTich, NgayBatDau, NgayKetThuc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE MatBang SET TrangThai = ?, Tang = ?, LoaiMatBang = ?, GiaTien = ?, DienTich = ?, NgayBatDau = ?, NgayKetThuc = ? WHERE MaMatBang = ?";
    private static final String SQL_DELETE = "DELETE FROM MatBang WHERE MaMatBang = ?";

    private DbConnection dbConnection = new DbConnection();

    public List<MatBang> findAll() {
        List<MatBang> customers = new ArrayList<>();
        try {
            Connection conn = dbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL);

            while (rs.next()) {
                String maMatBang = rs.getString("MaMatBang");
                String trangThai = rs.getString("TrangThai");
                int tang = rs.getInt("Tang");
                String loaiMatBang = rs.getString("LoaiMatBang");
                BigDecimal giaTien = rs.getBigDecimal("GiaTien");
                BigDecimal dienTich = rs.getBigDecimal("DienTich");
                LocalDate ngayBatDau = rs.getDate("NgayBatDau").toLocalDate();
                LocalDate ngayKetThuc = rs.getDate("NgayKetThuc").toLocalDate();
                customers.add(new MatBang(maMatBang, trangThai, tang, loaiMatBang, giaTien, dienTich, ngayBatDau, ngayKetThuc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public boolean save(MatBang matBang) {
        int rowAffected = 0;
        String sql = "INSERT INTO MatBang " +
                "(MaMatBang, TrangThai, Tang, LoaiMatBang, GiaTien, DienTich, NgayBatDau, NgayKetThuc) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setString(1, matBang.getMaMatBang());
            psmt.setString(2, matBang.getTrangThai());
            psmt.setInt(3, matBang.getTang());
            psmt.setString(4, matBang.getLoaiMatBang());
            psmt.setBigDecimal(5, matBang.getGiaTien());
            psmt.setBigDecimal(6, matBang.getDienTich());

            if (matBang.getNgayBatDau() != null) {
                psmt.setDate(7, Date.valueOf(matBang.getNgayBatDau()));
            } else {
                psmt.setNull(7, Types.DATE);
            }
            if (matBang.getNgayKetThuc() != null) {
                psmt.setDate(8, Date.valueOf(matBang.getNgayKetThuc()));
            } else {
                psmt.setNull(8, Types.DATE);
            }

            rowAffected = psmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException dup) {
            System.err.println("Lỗi trùng khóa chính (MaMatBang đã tồn tại): " + dup.getMessage());
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi INSERT MatBang: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowAffected > 0;
    }


    public MatBang findById(String maMatBang) {
        String sql = "SELECT * FROM MatBang WHERE MaMatBang = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maMatBang);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new MatBang(
                        rs.getString("MaMatBang"),
                        rs.getString("TrangThai"),
                        rs.getInt("Tang"),
                        rs.getString("LoaiMatBang"),
                        rs.getBigDecimal("GiaTien"),
                        rs.getBigDecimal("DienTich"),
                        rs.getDate("NgayBatDau").toLocalDate(),
                        rs.getDate("NgayKetThuc").toLocalDate()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(MatBang matBang) {
        Connection conn = dbConnection.getConnection();
        int rowAffected = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, matBang.getTrangThai());
            pstmt.setInt(2, matBang.getTang());
            pstmt.setString(3, matBang.getLoaiMatBang());
            pstmt.setBigDecimal(4, matBang.getGiaTien());
            pstmt.setBigDecimal(5, matBang.getDienTich());
            pstmt.setDate(6, Date.valueOf(matBang.getNgayBatDau()));
            pstmt.setDate(7, Date.valueOf(matBang.getNgayKetThuc()));
            pstmt.setString(8, matBang.getMaMatBang());
            rowAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowAffected > 0;
    }

    public boolean delete(String maMatBang) {
        MatBang matBang = findById(maMatBang);

        if (matBang == null) {
            return false;
        } else {
            Connection conn = dbConnection.getConnection();
            int rowAffected = 0;
            try {
                PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE);
                pstmt.setString(1, maMatBang);
                rowAffected = pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rowAffected > 0;
        }
    }

}
