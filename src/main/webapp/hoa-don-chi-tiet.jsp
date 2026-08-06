<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Hóa Đơn Chi Tiết</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        body { overflow-x: hidden; }
        .sidebar { width: 250px; background-color: #212529; min-height: 100vh; position: fixed; }
        .sidebar-brand { background-color: #0d6efd; color: white; padding: 18px; text-align: center; font-weight: bold; margin: 0; }
        .sidebar a { color: #d3d3d3; text-decoration: none; padding: 15px 20px; display: block; border-bottom: 1px solid #343a40; }
        .sidebar a:hover, .sidebar a.active { background-color: #0d6efd; color: white; border-left: 4px solid white; }
        .content { margin-left: 250px; padding: 30px; }
    </style>
</head>
<body>
<div class="sidebar ">
    <h2 class="sidebar-brand">S-FASHION</h2>
    
    <a href="${pageContext.request.contextPath}/" ><i class="bi bi-house-door me-2"></i> Tổng quan</a>
    <a href="${pageContext.request.contextPath}/danh-muc" ><i class="bi bi-tags me-2"></i> Quản lý Danh mục</a>
    <a href="${pageContext.request.contextPath}/ban-hang" ><i class="bi bi-cart-check me-2"></i> Quản lý Bán hàng</a>
    <a href="${pageContext.request.contextPath}/hoa-don" class="active"><i class="bi bi-receipt me-2"></i> Quản lý Hóa đơn</a>
    <a href="${pageContext.request.contextPath}/khach-hang" ><i class="bi bi-people me-2"></i> Quản lý Khách hàng</a>

    <c:if test="${sessionScope.nhanVienLogin.chucVu == 'Quản lý'}">
        <a href="${pageContext.request.contextPath}/san-pham" ><i class="bi bi-box-seam me-2"></i> Quản lý Sản phẩm</a>
        <a href="${pageContext.request.contextPath}/nhan-vien" ><i class="bi bi-person-badge me-2"></i> Quản lý Nhân viên</a>
        <a href="${pageContext.request.contextPath}/khuyen-mai" ><i class="bi bi-gift me-2"></i> Quản lý Khuyến mãi</a>
        <a href="${pageContext.request.contextPath}/doanh-thu" ><i class="bi bi-graph-up-arrow me-2"></i> Quản lý Doanh thu</a>
    </c:if>
    
    <!-- Khu vực đăng xuất -->
    <div style="position: absolute; bottom: 20px; width: 100%; border-top: 1px solid #343a40; padding-top: 15px;">
        <div class="text-center text-white mb-2 fw-bold">
            Xin chào, ${sessionScope.nhanVienLogin.tenNhanVien}
            <br>
            <small class="fw-normal">(${sessionScope.nhanVienLogin.chucVu})</small>
        </div>
        <a href="${pageContext.request.contextPath}/dang-xuat" class="d-block text-danger fw-bold text-center" style="border: none; text-decoration: none;" onmouseover="this.style.backgroundColor='transparent'; this.style.color='#dc3545'" onmouseout="this.style.backgroundColor='transparent';">
            <i class="bi bi-box-arrow-left me-2"></i> Đăng Xuất
        </a>
    </div>
</div>
<div class="content">
    <h2 class="mb-4 fw-bold">Chi Tiết Hóa Đơn (ID: ${idHoaDonGoc})</h2>

    <div class="card shadow-sm border-0">
        <div class="card-body p-4">
            <table class="table table-bordered table-striped text-center align-middle">
                <thead class="table-dark">
                <tr>
                    <th>Mã SPCT (SKU)</th>
                    <th>Tên Sản Phẩm</th>
                    <th>Phân loại</th>
                    <th>Đơn Giá</th>
                    <th>Số Lượng</th>
                    <th>Thành Tiền</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${listHDCT}">
                    <tr>
                        <td><span class="badge bg-secondary">${item.maSanPham}</span></td>
                        <td class="fw-bold">${item.tenSanPham}</td>
                        <td>${item.tenMau} - Size ${item.tenSize}</td>
                        <td class="text-danger">${item.donGia} VNĐ</td>
                        <td><span class="badge bg-primary fs-6">${item.soLuong}</span></td>
                        <td class="text-danger fw-bold">${item.donGia * item.soLuong} VNĐ</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
