package vn.codegym.tcomplex.controller;

import vn.codegym.tcomplex.model.MatBang;
import vn.codegym.tcomplex.service.MatBangService;
import vn.codegym.tcomplex.service.MatBangServicelpm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "MatBangController", urlPatterns = {"/matbang"})
public class MatBangController extends HttpServlet {


        MatBangService matBangService;

        @Override
        public void init() throws ServletException {
            matBangService  = new MatBangServicelpm();
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");
            if (action == null) {
                action = "";
            }

            RequestDispatcher dispatcher = null;
            switch (action) {
                case "create":
                    req.setAttribute("message", "");
                    dispatcher = req.getRequestDispatcher("/create.jsp");
                    break;
                case "edit":
                String maMatBang = req.getParameter("maMatBang");
                MatBang matBang = matBangService.findById(maMatBang);
                if (matBang == null) {
                    req.setAttribute("message", "Không tìm thấy mặt bằng có mã " + maMatBang);
                    dispatcher = req.getRequestDispatcher("/index.jsp");
                } else {
                    req.setAttribute("matBang", matBang);
                    dispatcher = req.getRequestDispatcher("/edit.jsp");
                }
                break;
                case "delete":
                    String maDelete = req.getParameter("maMatBang");
                    if (matBangService.delete(maDelete)) {
                        resp.sendRedirect("/matbang");
                        return;
                    } else {
                        req.setAttribute("message", "Mã mặt bằng không tồn tại!");
                        dispatcher = req.getRequestDispatcher("/index.jsp");
                    }
                    break;

                default:
                    List<MatBang> list = matBangService.findAll();
                    req.setAttribute("matBangs", list);
                    req.setAttribute("title", "Danh sách mặt bằng");
                    dispatcher = req.getRequestDispatcher("/index.jsp");
                    break;
            }
            dispatcher.forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");
            if (action == null) {
                action = "";
            }
            if (action.equals("create")) {
                MatBang mb = extractMatBangFromRequest(req);
                if (matBangService.save(mb)) {
                    req.setAttribute("message", "Thêm mới mặt bằng thành công");
                    req.setAttribute("success", true);
                } else {
                    req.setAttribute("message", "Thêm mới mặt bằng thất bại");
                    req.setAttribute("success", false);
                }
                req.getRequestDispatcher("/create.jsp").forward(req, resp);

            } else if (action.equals("edit")) {
                MatBang mb = extractMatBangFromRequest(req);
                if (matBangService.update(mb)) {
                    req.setAttribute("message", "Cập nhật mặt bằng thành công");
                    req.setAttribute("success", true);
                } else {
                    req.setAttribute("message", "Cập nhật mặt bằng thất bại");
                    req.setAttribute("success", false);
                }
                req.getRequestDispatcher("/edit.jsp").forward(req, resp);
            }
        }

    private MatBang extractMatBangFromRequest(HttpServletRequest req) {
        String maMatBang = req.getParameter("maMatBang");
        String trangThai = req.getParameter("trangThai");
        int tang = Integer.parseInt(req.getParameter("tang"));
        String loaiMatBang = req.getParameter("loaiMatBang");
        BigDecimal giaTien = new BigDecimal(req.getParameter("giaTien"));
        BigDecimal dienTich = new BigDecimal(req.getParameter("dienTich"));
        LocalDate ngayBatDau = LocalDate.parse(req.getParameter("ngayBatDau")); // format yyyy-MM-dd
        LocalDate ngayKetThuc = LocalDate.parse(req.getParameter("ngayKetThuc"));

        return new MatBang(maMatBang, trangThai, tang, loaiMatBang, giaTien, dienTich, ngayBatDau, ngayKetThuc);
    }
}

