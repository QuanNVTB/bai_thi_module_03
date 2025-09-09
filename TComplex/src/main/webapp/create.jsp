<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm Mặt Bằng</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f7f7f7; }
        .form-container { width: 50%; margin: 30px auto; background: #fff; padding: 20px; border-radius: 6px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        h2 { text-align: center; margin-bottom: 20px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ccc; border-radius: 4px; }
        .btn { margin-top: 15px; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        .btn-submit { background: #28a745; color: #fff; }
        .btn-cancel { background: #6c757d; color: #fff; margin-left: 10px; }
        .btn-submit:hover { background: #218838; }
        .btn-cancel:hover { background: #5a6268; }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Thêm Mặt Bằng</h2>
    <form action="matbang" method="post">
        <input type="hidden" name="action" value="create">

        <label for="maMatBang">Mã mặt bằng</label>
        <input type="text" id="maMatBang" name="maMatBang" placeholder="VD: A1B2-12-34" required>

        <label for="trangThai">Trạng thái</label>
        <select id="trangThai" name="trangThai" required>
            <option value="Trống">Trống</option>
            <option value="Hạ tầng">Hạ tầng</option>
            <option value="Đầy đủ">Đầy đủ</option>
        </select>

        <label for="tang">Tầng</label>
        <input type="number" id="tang" name="tang" min="1" max="15" required>

        <label for="loaiMatBang">Loại mặt bằng</label>
        <select id="loaiMatBang" name="loaiMatBang" required>
            <option value="Văn phòng chia sẻ">Văn phòng chia sẻ</option>
            <option value="Văn phòng trọn gói">Văn phòng trọn gói</option>
        </select>

        <label for="giaTien">Giá tiền (VNĐ)</label>
        <input type="number" id="giaTien" name="giaTien" min="1000000" required>

        <label for="dienTich">Diện tích (m²)</label>
        <input type="number" id="dienTich" name="dienTich" min="20" step="0.1" required>

        <label for="ngayBatDau">Ngày bắt đầu</label>
        <input type="date" id="ngayBatDau" name="ngayBatDau" required>

        <label for="ngayKetThuc">Ngày kết thúc</label>
        <input type="date" id="ngayKetThuc" name="ngayKetThuc" required>

        <button type="submit" class="btn btn-submit">Lưu</button>
        <a href="matbang" class="btn btn-cancel">Hủy</a>
    </form>
</div>
</body>
</html>