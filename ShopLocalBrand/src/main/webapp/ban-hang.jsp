<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Bán Hàng Tại Quầy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        body { overflow-x: hidden; background-color: #f8f9fa; }
        .sidebar { width: 250px; background-color: #212529; min-height: 100vh; position: fixed; }
        .sidebar-brand { background-color: #0d6efd; color: white; padding: 18px; text-align: center; font-weight: bold; margin: 0; }
        .sidebar a { color: #d3d3d3; text-decoration: none; padding: 15px 20px; display: block; border-bottom: 1px solid #343a40; }
        .sidebar a:hover, .sidebar a.active { background-color: #0d6efd; color: white; border-left: 4px solid white; }
        .content { margin-left: 250px; padding: 20px; }
        .section-title { font-size: 1.1rem; font-weight: bold; border-bottom: 2px solid #0d6efd; padding-bottom: 5px; margin-bottom: 15px; }
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
    <a href="${pageContext.request.contextPath}/ban-hang" class="active"><i class="bi bi-cart-check me-2"></i> Quản lý Bán hàng</a>
    <a href="${pageContext.request.contextPath}/khuyen-mai"><i class="bi bi-gift me-2"></i> Quản lý Khuyến mãi</a>
    <a href="${pageContext.request.contextPath}/doanh-thu"><i class="bi bi-graph-up-arrow me-2"></i> Quản lý Doanh thu</a>
</div>

<div class="content">
    <div class="row">
        <!-- Cột trái: Hóa đơn chờ và Sản phẩm -->
        <div class="col-md-8">
            <!-- 1. HÓA ĐƠN CHỜ -->
            <div class="card shadow-sm mb-4">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="section-title mb-0">Hóa Đơn Chờ</h5>
                        <!-- Đã sửa thành thẻ a có gắn link action=create -->
                        <a href="${pageContext.request.contextPath}/ban-hang?action=create" class="btn btn-sm btn-primary fw-bold">➕ Tạo Hóa Đơn</a>
                    </div>
                    <table class="table table-bordered text-center align-middle">
                        <thead class="table-light">
                            <tr>
                                <th>Mã HĐ</th>
                                <th>Ngày Tạo</th>
                                <th>Trạng Thái</th>
                                <th>Hành Động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Vòng lặp in ra các hóa đơn chờ -->
                            <c:forEach var="hd" items="${listHoaDonCho}">
                                <tr>
                                    <td class="fw-bold">${hd.maHoaDon}</td>
                                    <td>${hd.ngayTao}</td>
                                    <td><span class="badge bg-warning text-dark">Chờ thanh toán</span></td>
                                    <td>
                                        <a href="#" class="btn btn-sm btn-outline-success fw-bold">Chọn</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- 2. GIỎ HÀNG -->
            <div class="card shadow-sm mb-4">
                <div class="card-body">
                    <h5 class="section-title">Giỏ Hàng</h5>
                    <table class="table table-bordered text-center align-middle">
                        <thead class="table-light">
                        <tr>
                            <th>Tên Sản Phẩm</th>
                            <th>Số Lượng</th>
                            <th>Đơn Giá</th>
                            <th>Tổng</th>
                            <th>Xóa</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="5" class="text-muted">Chưa có sản phẩm nào trong giỏ.</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- 3. DANH SÁCH SẢN PHẨM -->
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="section-title">Danh Sách Sản Phẩm</h5>
                    <table class="table table-bordered text-center align-middle">
                        <thead class="table-light">
                        <tr>
                            <th>Mã SKU</th>
                            <th>Số lượng tồn</th>
                            <th>Giá Bán</th>
                            <th>Hành Động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="spct" items="${listSPCT}">
                            <tr>
                                <td>${spct.maSpChiTiet}</td>
                                <td><span class="badge bg-success">${spct.soLuong}</span></td>
                                <td class="text-danger fw-bold">${spct.donGia}</td>
                                <td>
                                    <button class="btn btn-sm btn-outline-primary fw-bold">Thêm</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Cột phải: Khu vực thanh toán -->
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h5 class="section-title">Thanh Toán</h5>
                    <form>
                        <div class="mb-3">
                            <label class="fw-bold text-muted">Khách Hàng</label>
                            <input type="text" class="form-control" value="Khách lẻ" readonly>
                        </div>
                        <div class="d-flex justify-content-between mb-3">
                            <span class="fw-bold">Tổng tiền hàng:</span>
                            <span class="text-danger fw-bold fs-5">0 VNĐ</span>
                        </div>
                        <div class="mb-3">
                            <label class="fw-bold text-muted">Khách đưa:</label>
                            <input type="number" class="form-control">
                        </div>
                        <div class="d-flex justify-content-between mb-4">
                            <span class="fw-bold">Tiền thừa:</span>
                            <span class="fw-bold">0 VNĐ</span>
                        </div>
                        <button class="btn btn-success w-100 fw-bold fs-5 py-2">THANH TOÁN</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>