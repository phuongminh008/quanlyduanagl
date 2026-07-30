<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>S-Fashion Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        .sidebar { min-height: 100vh; background-color: #212529; }
        .sidebar a { color: white; text-decoration: none; padding: 15px; display: block; border-bottom: 1px solid #343a40; }
        .sidebar a:hover { background-color: #343a40; }
        .content { background-color: #f8f9fa; }
    </style>
</head>
<body>
<div class="d-flex">
    
    <div class="sidebar col-md-2 p-0">
        <h4 class="text-white text-center py-3 m-0 bg-primary fw-bold">S-FASHION</h4>
        <a href="${pageContext.request.contextPath}/" class="active"><i class="bi bi-house-door me-2"></i> Tổng quan</a>
        <a href="${pageContext.request.contextPath}/danh-muc"><i class="bi bi-tags me-2"></i> Quản lý Danh mục</a>
        <a href="${pageContext.request.contextPath}/san-pham"><i class="bi bi-box-seam me-2"></i> Quản lý Sản phẩm</a>
        <a href="${pageContext.request.contextPath}/nhan-vien"><i class="bi bi-person-badge me-2"></i> Quản lý Nhân viên</a>
        <a href="${pageContext.request.contextPath}/khach-hang"><i class="bi bi-people me-2"></i> Quản lý Khách hàng</a>
        <a href="${pageContext.request.contextPath}/hoa-don"><i class="bi bi-receipt me-2"></i> Quản lý Hóa đơn</a>
        <a href="${pageContext.request.contextPath}/ban-hang"><i class="bi bi-cart-check me-2"></i> Quản lý Bán hàng</a>
        <a href="${pageContext.request.contextPath}/khuyen-mai"><i class="bi bi-gift me-2"></i> Quản lý Khuyến mãi</a>
        <a href="${pageContext.request.contextPath}/doanh-thu"><i class="bi bi-graph-up-arrow me-2"></i> Quản lý Doanh thu</a>
    </div>

    
    <div class="content col-md-10 p-4">
        <h2 class="mb-4">Chào mừng đến với hệ thống quản trị!</h2>

        <div class="row">
            
            <div class="col-md-3">
                <div class="card text-white bg-primary mb-3 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Tổng Doanh Thu</h5>
                        <h3 class="fw-bold">15,000,000 VNĐ</h3>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-success mb-3 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Đơn Hàng Mới</h5>
                        <h3 class="fw-bold">24 Đơn</h3>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-warning mb-3 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Sản Phẩm Sắp Hết</h5>
                        <h3 class="fw-bold">5 SP</h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>