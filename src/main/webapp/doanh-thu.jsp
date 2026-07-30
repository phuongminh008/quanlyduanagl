<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Quản lý Doanh Thu - S-Fashion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        body { overflow-x: hidden; background-color: #f8f9fa; }
        .sidebar { width: 250px; background-color: #212529; min-height: 100vh; position: fixed; }
        .sidebar-brand { background-color: #0d6efd; color: white; padding: 18px; text-align: center; font-weight: bold; margin: 0; }
        .sidebar a { color: #d3d3d3; text-decoration: none; padding: 15px 20px; display: block; border-bottom: 1px solid #343a40; }
        .sidebar a:hover, .sidebar a.active { background-color: #0d6efd; color: white; border-left: 4px solid white; }
        .content { margin-left: 250px; padding: 30px; }
    </style>
</head>
<body>
<div class="sidebar">
    <h2 class="sidebar-brand">S-FASHION</h2>
    <a href="${pageContext.request.contextPath}/"><i class="bi bi-house-door me-2"></i> Tổng quan</a>
    <a href="${pageContext.request.contextPath}/san-pham"><i class="bi bi-box-seam me-2"></i> Quản lý Sản phẩm</a>
    <a href="${pageContext.request.contextPath}/hoa-don"><i class="bi bi-receipt me-2"></i> Quản lý Hóa đơn</a>
    <a href="${pageContext.request.contextPath}/khuyen-mai"><i class="bi bi-gift me-2"></i> Quản lý Khuyến mãi</a>
    <a href="${pageContext.request.contextPath}/doanh-thu" class="active"><i class="bi bi-graph-up me-2"></i> Quản lý Doanh thu</a>
</div>

<div class="content">
    <h2 class="mb-4 fw-bold">Báo Cáo Doanh Thu</h2>

    
    <div class="row mb-4">
        <div class="col-md-4">
            <div class="card shadow-sm text-white bg-primary">
                <div class="card-body">
                    <h5 class="card-title"><i class="bi bi-cash-stack"></i> Tổng doanh thu</h5>
                    <h3 class="fw-bold mt-2">${tongDoanhThuToanThoiGian} VNĐ</h3>
                </div>
            </div>
        </div>
    </div>

    
    <div class="card shadow-sm border-0">
        <div class="card-body p-4">
            <h5 class="fw-bold text-primary mb-3">Doanh thu theo ngày</h5>
            <table class="table table-bordered table-striped text-center align-middle">
                <thead class="table-dark">
                <tr>
                    <th>Ngày</th>
                    <th>Số Hóa Đơn (Đã bán)</th>
                    <th>Doanh Thu Trong Ngày</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tk" items="${listThongKe}">
                    <tr>
                        <td class="fw-bold">${tk.ngay}</td>
                        <td><span class="badge bg-success fs-6">${tk.soDonHang} đơn</span></td>
                        <td class="text-danger fw-bold">${tk.tongDoanhThu} VNĐ</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>