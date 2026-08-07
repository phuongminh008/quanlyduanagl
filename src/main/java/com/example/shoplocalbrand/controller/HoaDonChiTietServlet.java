// Khai báo package chứa class Controller này
package com.example.shoplocalbrand.controller;

// Import class Repository để tương tác với cơ sở dữ liệu
import com.example.shoplocalbrand.repository.HoaDonChiTietRepository;
// Import các thư viện Java Servlet API
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Cấu hình URL mapping: Servlet này xử lý các yêu cầu tới đường dẫn "/hoa-don-chi-tiet"
@WebServlet(name = "HoaDonChiTietServlet", value = "/hoa-don-chi-tiet")
public class HoaDonChiTietServlet extends HttpServlet {

    // Khởi tạo đối tượng Repository để gọi các hàm truy vấn hóa đơn chi tiết
    private HoaDonChiTietRepository hdctRepo = new HoaDonChiTietRepository();

    // Ghi đè phương thức doGet để xử lý các yêu cầu HTTP GET gửi tới Servlet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy giá trị của tham số "idHoaDon" truyền từ URL hoặc Form
            String idHoaDonStr = request.getParameter("idHoaDon");

            // Kiểm tra validation: Nếu tham số idHoaDon bị thiếu hoặc rỗng
            if (idHoaDonStr == null || idHoaDonStr.isEmpty()) {
                // Chuyển hướng người dùng quay lại trang danh sách hóa đơn (/hoa-don)
                response.sendRedirect(request.getContextPath() + "/hoa-don");
                return; // Dừng thực thi tiếp các dòng lệnh phía dưới
            }

            // Ép kiểu chuỗi idHoaDonStr sang kiểu số nguyên int
            int idHoaDon = Integer.parseInt(idHoaDonStr);

            // Đưa ID hóa đơn vào request attribute để truyền sang trang JSP (nếu cần hiển thị lại)
            request.setAttribute("idHoaDonGoc", idHoaDon);

            // Gọi Repository để lấy danh sách chi tiết hóa đơn theo ID và đưa vào request attribute "listHDCT"
            request.setAttribute("listHDCT", hdctRepo.getByHoaDonId(idHoaDon));

            // Chuyển tiếp (forward) request và response sang trang giao diện "/hoa-don-chi-tiet.jsp"
            request.getRequestDispatcher("/hoa-don-chi-tiet.jsp").forward(request, response);

        } catch (Exception e) {
            // Bắt ngoại lệ nếu có lỗi xảy ra (ví dụ: lỗi ép kiểu số NumberFormatException hoặc lỗi SQL)

            // Thiết lập kiểu dữ liệu trả về là HTML với bảng mã UTF-8 để hiển thị tiếng Việt không bị lỗi font
            response.setContentType("text/html;charset=UTF-8");

            // In chi tiết vết lỗi (Stack Trace) trực tiếp ra màn hình trình duyệt của người dùng
            e.printStackTrace(response.getWriter());
        }
    }
}