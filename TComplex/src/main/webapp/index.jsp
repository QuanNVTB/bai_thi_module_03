<%@ page import="java.sql.*, java.text.NumberFormat, java.util.Locale, java.math.BigDecimal" %>
<%@ page import="vn.codegym.tcomplex.util.DbConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách Mặt Bằng</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f7f7f7; }
        h2 { text-align: center; margin: 20px 0; color: #333; }
        .container { width: 95%; margin: auto; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; background: #fff; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: center; }
        th { background-color: #f2f2f2; color: #333; }
        tr:nth-child(even) { background: #fafafa; }
        .actions button { padding: 6px 12px; border: none; border-radius: 4px; cursor: pointer; }
        .btn-add { display: inline-block; padding: 10px 18px; margin: 10px 0; background: #28a745; color: #fff; text-decoration: none; border-radius: 5px; }
        .btn-edit { background: #ffc107; color: #fff; }
        .btn-delete { background: #dc3545; color: #fff; }
        .btn-edit:hover { background: #e0a800; }
        .btn-delete:hover { background: #c82333; }
        .btn-add:hover { background: #218838; }
        .search-form { background: #fff; padding: 15px; border-radius: 5px; margin-bottom: 15px; }
        .search-form label { margin-right: 10px; font-weight: bold; }
        .search-form input, .search-form select { margin-right: 15px; padding: 5px; }
        .btn-search { background: #007bff; color: #fff; padding: 6px 14px; border: none; border-radius: 4px; }
        .btn-search:hover { background: #0056b3; }
    </style>
</head>
<body>
<div class="container">
    <h2>Danh sách Mặt Bằng</h2>
    <a href="matbang?action=create" class="btn-add">+ Thêm Mặt Bằng</a>

    <!-- 🔍 Form tìm kiếm -->
    <form method="get" class="search-form">
        <input type="hidden" name="action" value="search">

        <label for="loaiMatBang">Loại MB:</label>
        <select name="loaiMatBang" id="loaiMatBang">
            <option value="">-- Tất cả --</option>
            <option value="Văn phòng chia sẻ">Văn phòng chia sẻ</option>
            <option value="Văn phòng trọn gói">Văn phòng trọn gói</option>
        </select>

        <label for="giaTien">Giá tiền (VNĐ):</label>
        <input type="number" name="giaTien" id="giaTien" min="0">

        <label for="tang">Tầng:</label>
        <input type="number" name="tang" id="tang" min="1" max="15">

        <button type="submit" class="btn-search">Tìm kiếm</button>
    </form>

    <%
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // Định dạng số VN
        Locale viVN = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getInstance(viVN);
        currencyFormat.setMaximumFractionDigits(0);
        NumberFormat areaFormat = NumberFormat.getNumberInstance(viVN);
        areaFormat.setMaximumFractionDigits(2);

        try {
            conn = DbConnection.getConnection();

            // Lấy giá trị từ form
            String loaiMatBang = request.getParameter("loaiMatBang");
            String giaTienStr  = request.getParameter("giaTien");
            String tangStr     = request.getParameter("tang");

            StringBuilder sql = new StringBuilder(
                    "SELECT MaMatBang, TrangThai, Tang, LoaiMatBang, GiaTien, DienTich, " +
                            "DATE_FORMAT(NgayBatDau, '%d/%m/%Y') as NgayBatDau, " +
                            "DATE_FORMAT(NgayKetThuc, '%d/%m/%Y') as NgayKetThuc " +
                            "FROM MatBang WHERE GiaTien > 1000000 AND Tang BETWEEN 1 AND 15"
            );

            // Điều kiện động
            if (loaiMatBang != null && !loaiMatBang.isEmpty()) {
                sql.append(" AND LoaiMatBang = ?");
            }
            if (giaTienStr != null && !giaTienStr.isEmpty()) {
                sql.append(" AND GiaTien = ?");
            }
            if (tangStr != null && !tangStr.isEmpty()) {
                sql.append(" AND Tang = ?");
            }

            pstmt = conn.prepareStatement(sql.toString());

            // Set tham số PreparedStatement
            int index = 1;
            if (loaiMatBang != null && !loaiMatBang.isEmpty()) {
                pstmt.setString(index++, loaiMatBang);
            }
            if (giaTienStr != null && !giaTienStr.isEmpty()) {
                pstmt.setBigDecimal(index++, new BigDecimal(giaTienStr));
            }
            if (tangStr != null && !tangStr.isEmpty()) {
                pstmt.setInt(index++, Integer.parseInt(tangStr));
            }

            rs = pstmt.executeQuery();
    %>

    <table>
        <tr>
            <th>Mã mặt bằng</th>
            <th>Trạng thái</th>
            <th>Tầng</th>
            <th>Loại mặt bằng</th>
            <th>Giá tiền (VNĐ)</th>
            <th>Diện tích (m²)</th>
            <th>Ngày bắt đầu</th>
            <th>Ngày kết thúc</th>
            <th>Hành động</th>
        </tr>
        <%
            while (rs.next()) {
                BigDecimal gia = rs.getBigDecimal("GiaTien");
                BigDecimal dt = rs.getBigDecimal("DienTich");

                String giaStr = (gia != null) ? currencyFormat.format(gia) : "";
                String dtStr  = (dt  != null) ? areaFormat.format(dt)      : "";
        %>
        <tr>
            <td><%= rs.getString("MaMatBang") %></td>
            <td><%= rs.getString("TrangThai") %></td>
            <td><%= rs.getInt("Tang") %></td>
            <td><%= rs.getString("LoaiMatBang") %></td>
            <td><%= giaStr %></td>
            <td><%= dtStr %></td>
            <td><%= rs.getString("NgayBatDau") %></td>
            <td><%= rs.getString("NgayKetThuc") %></td>
            <td class="actions">
                <form action="matbang" method="get" style="display:inline;">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="maMatBang" value="<%= rs.getString("MaMatBang") %>">
                    <button type="submit" class="btn-edit">Sửa</button>
                </form>
                <form action="matbang" method="get" style="display:inline;"
                      onsubmit="return confirm('Bạn có chắc muốn xóa mặt bằng này?');">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="maMatBang" value="<%= rs.getString("MaMatBang") %>">
                    <button type="submit" class="btn-delete">Xóa</button>
                </form>
            </td>
        </tr>
        <%
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) try { rs.close(); } catch (Exception ignore) {}
                if (pstmt != null) try { pstmt.close(); } catch (Exception ignore) {}
                if (conn != null) try { conn.close(); } catch (Exception ignore) {}
            }
        %>
    </table>
</div>
</body>
</html>
