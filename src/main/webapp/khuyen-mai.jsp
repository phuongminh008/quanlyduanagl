<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Quản lý Khuyến Mãi - S-Fashion</title>
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
<div class="sidebar">
    <h2 class="sidebar-brand">S-FASHION</h2>
    <a href="${pageContext.request.contextPath}/"><i class="bi bi-house-door me-2"></i> Tổng quan</a>
    <a href="${pageContext.request.contextPath}/danh-muc"><i class="bi bi-tags me-2"></i> Quản lý Danh mục</a>
    <a href="${pageContext.request.contextPath}/san-pham"><i class="bi bi-box-seam me-2"></i> Quản lý Sản phẩm</a>
    <a href="${pageContext.request.contextPath}/nhan-vien"><i class="bi bi-person-badge me-2"></i> Quản lý Nhân viên</a>
    <a href="${pageContext.request.contextPath}/khach-hang"><i class="bi bi-people me-2"></i> Quản lý Khách hàng</a>
    <a href="${pageContext.request.contextPath}/hoa-don"><i class="bi bi-receipt me-2"></i> Quản lý Hóa đơn</a>
    <a href="${pageContext.request.contextPath}/ban-hang"><i class="bi bi-cart-check me-2"></i> Quản lý Bán hàng</a>
    <a href="${pageContext.request.contextPath}/khuyen-mai" class="active"><i class="bi bi-gift me-2"></i> Quản lý Khuyến mãi</a>
    <a href="${pageContext.request.contextPath}/doanh-thu"><i class="bi bi-graph-up-arrow me-2"></i> Quản lý Doanh thu</a>
</div>

<div class="content">
    <h2 class="mb-4 fw-bold">Quản lý Đợt Giảm Giá</h2>

    <div class="row">
        <div class="col-md-4">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white fw-bold fs-5">Tạo Đợt Giảm Giá</div>
                <div class="card-body p-4">
                    <form action="${pageContext.request.contextPath}/khuyen-mai" method="POST">
                        <div class="mb-3">
                            <label class="fw-bold">Mã Đợt Giảm:</label>
                            <input type="text" class="form-control" name="maDotGiam" required>
                        </div>
                        <div class="mb-3">
                            <label class="fw-bold">Tên Chương Trình:</label>
                            <input type="text" class="form-control" name="tenDotGiam" required>
                        </div>
                        <div class="mb-3">
                            <label class="fw-bold">Phần trăm giảm (%):</label>
                            <input type="number" class="form-control" name="phanTramGiam" min="1" max="100" required>
                        </div>
                        <div class="row mb-3">
                            <div class="col-6">
                                <label class="fw-bold">Ngày Bắt Đầu:</label>
                                <input type="date" class="form-control" name="ngayBatDau" required>
                            </div>
                            <div class="col-6">
                                <label class="fw-bold">Ngày Kết Thúc:</label>
                                <input type="date" class="form-control" name="ngayKetThuc" required>
                            </div>
                        </div>
                        <div class="mb-4">
                            <label class="fw-bold">Trạng Thái:</label><br>
                            <input type="radio" name="trangThai" value="1" checked> Hoạt động
                            <input type="radio" name="trangThai" value="0" class="ms-2"> Ngừng
                        </div>
                        <button type="submit" class="btn btn-primary w-100 fw-bold fs-5">➕ Tạo Khuyến Mãi</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <table class="table table-bordered table-striped text-center align-middle">
                <thead class="table-dark">
                <tr>
                    <th>Mã</th>
                    <th>Tên Chương Trình</th>
                    <th>Giảm giá</th>
                    <th>Thời gian bắt đầu</th>
                    <th>Thời gian kết thúc</th>
                    <th>Trạng Thái</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="dgg" items="${listDGG}">
                    <tr>
                        <td><span class="badge bg-secondary">${dgg.maDotGiam}</span></td>
                        <td class="fw-bold">${dgg.tenDotGiam}</td>
                        <td><span class="badge bg-danger fs-6">${dgg.phanTramGiam}%</span></td>
                        <td>${dgg.ngayBatDau}</td>
                        <td>${dgg.ngayKetThuc}</td>
                        <td>
                            <c:choose>
                                <c:when test="${dgg.trangThai == 1}"><span class="badge bg-success">Hoạt động</span></c:when>
                                <c:otherwise><span class="badge bg-danger">Đã kết thúc</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${dgg.trangThai == 1}">
                                    <a href="${pageContext.request.contextPath}/khuyen-mai?action=change&id=${dgg.id}&tt=1" class="btn btn-sm btn-danger fw-bold" onclick="return confirm('Kết thúc đợt giảm giá này?');">Kết thúc</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/khuyen-mai?action=change&id=${dgg.id}&tt=0" class="btn btn-sm btn-success fw-bold">Kích hoạt</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>