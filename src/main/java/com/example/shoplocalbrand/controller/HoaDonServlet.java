package com.example.shoplocalbrand.controller;

// Khai báo các thư viện cần thiết cho Servlet và Repository
import com.example.shoplocalbrand.repository.HoaDonRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Cấu hình Servlet với URL pattern là "/hoa-don"
@WebServlet(name = "HoaDonServlet", value = "/hoa-don")
public class HoaDonServlet extends HttpServlet {
    // Khởi tạo đối tượng HoaDonRepository để thao tác với CSDL liên quan đến hóa đơn
    private HoaDonRepository hdRepo = new HoaDonRepository();

    // Xử lý các yêu cầu HTTP GET gửi đến URL "/hoa-don"
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy tham số "action" từ URL (ví dụ: /hoa-don?action=updateStatus)
            String action = request.getParameter("action");

            // Kiểm tra xem có hành động cập nhật trạng thái hóa đơn hay không
            if ("updateStatus".equals(action)) {
                // Đọc tham số "id" của hóa đơn và ép kiểu từ String sang int
                int id = Integer.parseInt(request.getParameter("id"));
                // Đọc tham số "status" cần cập nhật và ép kiểu sang int
                int status = Integer.parseInt(request.getParameter("status"));

                // Gọi Repository để thực hiện cập nhật trạng thái vào CSDL
                hdRepo.updateTrangThai(id, status);

                // Cập nhật xong, chuyển hướng người dùng quay lại trang danh sách hóa đơn để tải lại dữ liệu mới
                response.sendRedirect(request.getContextPath() + "/hoa-don");
                return; // Kết thúc phương thức doGet, không chạy tiếp dòng bên dưới
            }

            // Nếu không phải action updateStatus (mặc định là xem danh sách):
            // Lấy danh sách toàn bộ hóa đơn và truyền sang View với tên biến là "listHD"
            request.setAttribute("listHD", hdRepo.getAll());

            // Điều hướng yêu cầu đến trang JSP "/hoa-don.jsp" để hiển thị dữ liệuu
            request.getRequestDispatcher("/hoa-don.jsp").forward(request, response);
        } catch (Exception e) {
            // Nếu xảy ra lỗi: Thiết lập UTF-8 để hiển thị tiếng Việt không bị lỗi font
            response.setContentType("text/html;charset=UTF-8");
            // In chi tiết lỗi (stack trace) ra trực tiếp trình duyệt để kiểm tra
            e.printStackTrace(response.getWriter());
        }
    }
}