CREATE DATABASE data_complex;

USE data_complex;

CREATE TABLE MatBang (
    MaMatBang VARCHAR(20) NOT NULL PRIMARY KEY,  
    TrangThai ENUM('Trống', 'Hạ tầng', 'Đầy đủ'),
    Tang INT CHECK (Tang BETWEEN 1 AND 15),
    LoaiMatBang ENUM('Văn phòng chia sẻ', 'Văn phòng trọn gói'),
    GiaTien DECIMAL(18,0) CHECK (GiaTien > 1000000),  -- > 1,000,000 VND
    DienTich DECIMAL(10,2) CHECK (DienTich >= 20),     -- m², > 0
    NgayBatDau DATE,
    NgayKetThuc DATE,
    CONSTRAINT chk_MaMatBang_Format CHECK (MaMatBang REGEXP '^[A-Z0-9]{4}-[A-Z0-9]{2}-[A-Z0-9]{2}$')
);

INSERT INTO MatBang (MaMatBang, TrangThai, Tang, LoaiMatBang, GiaTien, DienTich, NgayBatDau, NgayKetThuc)
VALUES 
('A1B2-12-34', 'Trống', 12, 'Văn phòng trọn gói', 2000000, 45.50,
 STR_TO_DATE('01/09/2025','%d/%m/%Y'), STR_TO_DATE('30/09/2025','%d/%m/%Y'));
 
INSERT INTO MatBang (MaMatBang, TrangThai, Tang, LoaiMatBang, GiaTien, DienTich, NgayBatDau, NgayKetThuc)
VALUES 
('C5D6-08-11', 'Đầy đủ', 8, 'Văn phòng trọn gói', 3500000, 60.75,
 STR_TO_DATE('15/09/2025','%d/%m/%Y'), STR_TO_DATE('15/01/2026','%d/%m/%Y')),

('D7E8-02-07', 'Trống', 2, 'Văn phòng chia sẻ', 1200000, 25.00,
 STR_TO_DATE('05/09/2025','%d/%m/%Y'), STR_TO_DATE('05/10/2025','%d/%m/%Y')),

('E9F0-14-19', 'Đầy đủ', 14, 'Văn phòng trọn gói', 5000000, 80.00,
 STR_TO_DATE('20/09/2025','%d/%m/%Y'), STR_TO_DATE('20/03/2026','%d/%m/%Y'));

