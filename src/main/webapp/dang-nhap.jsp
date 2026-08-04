<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Đăng Nhập - S-Fashion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f2f5; height: 100vh; display: flex; align-items: center; justify-content: center; }
        .login-card { width: 100%; max-width: 400px; padding: 30px; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); background: white; }
    </style>
</head>
<body>
    <div class="login-card">
        <h2 class="text-center text-primary fw-bold mb-4">S-FASHION</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger fw-bold text-center">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/dang-nhap" method="post">
            <div class="mb-3">
                <label class="form-label fw-bold">Tên đăng nhập:</label>
                <input type="text" class="form-control" name="tenDangNhap" required>
            </div>
            <div class="mb-4">
                <label class="form-label fw-bold">Mật khẩu:</label>
                <input type="password" class="form-control" name="matKhau" required>
            </div>
            <button type="submit" class="btn btn-primary w-100 fw-bold py-2">ĐĂNG NHẬP</button>
        </form>
    </div>
</body>
</html>
