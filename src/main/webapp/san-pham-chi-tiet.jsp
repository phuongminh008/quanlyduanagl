<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<!-- Copy phần <head> và cấu trúc Sidebar y hệt như trang san-pham.jsp vào đây -->
<head>
    <title>Sản Phẩm Chi Tiết</title>
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
    <a href="${pageContext.request.contextPath}/san-pham" class="active"><i class="bi bi-box-seam me-2"></i> Quản lý Sản phẩm</a>
    <a href="${pageContext.request.contextPath}/nhan-vien"><i class="bi bi-person-badge me-2"></i> Quản lý Nhân viên</a>
    <a href="${pageContext.request.contextPath}/khach-hang"><i class="bi bi-people me-2"></i> Quản lý Khách hàng</a>
    <a href="${pageContext.request.contextPath}/hoa-don"><i class="bi bi-receipt me-2"></i> Quản lý Hóa đơn</a>
    <a href="${pageContext.request.contextPath}/ban-hang"><i class="bi bi-cart-check me-2"></i> Quản lý Bán hàng</a>
    <a href="${pageContext.request.contextPath}/khuyen-mai"><i class="bi bi-gift me-2"></i> Quản lý Khuyến mãi</a>
    <a href="${pageContext.request.contextPath}/doanh-thu"><i class="bi bi-graph-up-arrow me-2"></i> Quản lý Doanh thu</a>
</div>

<div class="content">
    <h2 class="mb-4 fw-bold">Cấu hình Chi Tiết SP</h2>

    <div class="row">
        <div class="col-md-4">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-info text-white fw-bold fs-5">Thêm Phiên Bản Mới</div>
                <div class="card-body p-4">
                    <form action="${pageContext.request.contextPath}/san-pham-chi-tiet" method="POST">
                        <input type="hidden" name="id" value="${spctEdit.id}">
                        <input type="hidden" name="idSanPham" value="${idSanPhamGoc}">

                        <div class="mb-3">
                            <label class="fw-bold">Mã Phiên Bản (SKU):</label>
                            <input type="text" class="form-control" name="maSpChiTiet" value="${spctEdit.maSpChiTiet}" required>
                        </div>

                        <div class="row mb-3">
                            <div class="col-6">
                                <label class="fw-bold">Màu Sắc:</label>
                                <select class="form-select" name="idMauSac">
                                    <c:forEach var="ms" items="${listMauSac}">
                                        <option value="${ms.id}" ${spctEdit.idMauSac == ms.id ? 'selected' : ''}>${ms.tenMau}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-6">
                                <label class="fw-bold">Kích Thước:</label>
                                <select class="form-select" name="idKichThuoc">
                                    <c:forEach var="kt" items="${listKichThuoc}">
                                        <option value="${kt.id}" ${spctEdit.idKichThuoc == kt.id ? 'selected' : ''}>${kt.tenKichThuoc}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-6">
                                <label class="fw-bold">Số lượng (Kho):</label>
                                <input type="number" class="form-control" name="soLuong" value="${spctEdit != null ? spctEdit.soLuong : 0}" min="0" required>
                            </div>
                            <div class="col-6">
                                <label class="fw-bold">Đơn giá (VNĐ):</label>
                                <input type="number" class="form-control" name="donGia" value="${spctEdit != null ? spctEdit.donGia : 0}" min="0" required>
                            </div>
                        </div>

                        <div class="mb-4">
                            <label class="fw-bold">Trạng Thái:</label><br>
                            <input type="radio" name="trangThai" value="1" ${spctEdit == null || spctEdit.trangThai == 1 ? 'checked' : ''}> Hoạt động
                            <input type="radio" name="trangThai" value="0" class="ms-2" ${spctEdit != null && spctEdit.trangThai == 0 ? 'checked' : ''}> Ngừng bán
                        </div>

                        <button type="submit" class="btn ${spctEdit == null ? 'btn-info text-white' : 'btn-warning'} w-100 fw-bold fs-5">
                            ${spctEdit == null ? 'Lưu Cấu Hình' : 'Cập Nhật Cấu Hình'}
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <table class="table table-bordered table-striped text-center align-middle">
                <thead class="table-dark">
                <tr>
                    <th>Mã SKU</th>
                    <th>Màu Sắc</th>
                    <th>Kích Thước</th>
                    <th>Số Lượng</th>
                    <th>Đơn Giá</th>
                    <th>Trạng Thái</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="spct" items="${listSPCT}">
                    <tr>
                        <td><span class="badge bg-secondary">${spct.maSpChiTiet}</span></td>
                        <td>${spct.tenMauSac}</td>
                        <td>${spct.tenKichThuoc}</td>
                        <td><span class="badge bg-primary fs-6">${spct.soLuong}</span></td>
                        <td class="text-danger fw-bold">${spct.donGia} VNĐ</td>
                        <td>
                            <c:choose>
                                <c:when test="${spct.trangThai == 1}"><span class="badge bg-success">Hoạt động</span></c:when>
                                <c:otherwise><span class="badge bg-danger">Ngừng bán</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/san-pham-chi-tiet?action=edit&id=${spct.id}" class="btn btn-sm btn-warning fw-bold text-dark mb-1">Sửa</a>
                            <a href="${pageContext.request.contextPath}/san-pham-chi-tiet?action=delete&id=${spct.id}&idSanPham=${idSanPhamGoc}" class="btn btn-sm btn-danger fw-bold mb-1" onclick="return confirm('Ngừng bán phiên bản này?');">Xóa</a>
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