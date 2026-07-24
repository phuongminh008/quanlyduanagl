<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Quản lý Sản Phẩm - S-Fashion</title>
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
<!-- SIDEBAR CHUẨN ĐẦY ĐỦ CÁC CHỨC NĂNG -->
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

<!-- MAIN CONTENT -->
<div class="content">
    <h2 class="mb-4 fw-bold">Quản lý Sản Phẩm</h2>

    <div class="row">
        <!-- FORM THÊM MỚI -->
        <div class="col-md-4">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white fw-bold fs-5">Thêm Sản Phẩm Mới</div>
                <div class="card-body p-4">
                    <form action="${pageContext.request.contextPath}/san-pham" method="POST">
                        <input type="hidden" name="id" value="${spEdit.id}">

                        <div class="mb-3">
                            <label class="form-label fw-bold">Mã Sản Phẩm:</label>
                            <input type="text" class="form-control" name="maSanPham" value="${spEdit.maSanPham}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label fw-bold">Tên Sản Phẩm:</label>
                            <input type="text" class="form-control" name="tenSanPham" value="${spEdit.tenSanPham}" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">Danh Mục:</label>
                            <select class="form-select" name="idDanhMuc">
                                <c:forEach var="dm" items="${listDanhMuc}">
                                    <option value="${dm.id}" ${spEdit.idDanhMuc == dm.id ? 'selected' : ''}>${dm.tenDanhMuc}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="row mb-3">
                            <div class="col-6">
                                <label class="form-label fw-bold">Chất Liệu:</label>
                                <select class="form-select" name="idChatLieu">
                                    <c:forEach var="cl" items="${listChatLieu}">
                                        <option value="${cl.id}" ${spEdit.idChatLieu == cl.id ? 'selected' : ''}>${cl.tenChatLieu}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-6">
                                <label class="form-label fw-bold">Kiểu Dáng:</label>
                                <select class="form-select" name="idKieuDang">
                                    <c:forEach var="kd" items="${listKieuDang}">
                                        <option value="${kd.id}" ${spEdit.idKieuDang == kd.id ? 'selected' : ''}>${kd.tenKieuDang}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-bold">Trạng Thái:</label><br>
                            <input type="radio" name="trangThai" value="1" ${spEdit == null || spEdit.trangThai == 1 ? 'checked' : ''}> Hoạt động
                            <input type="radio" name="trangThai" value="0" class="ms-2" ${spEdit != null && spEdit.trangThai == 0 ? 'checked' : ''}> Ngừng bán
                        </div>

                        <button type="submit" class="btn ${spEdit == null ? 'btn-primary' : 'btn-warning'} w-100 fw-bold fs-5">
                            ${spEdit == null ? '➕ Lưu Sản Phẩm' : '✏️ Cập Nhật'}
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <!-- BẢNG DỮ LIỆU -->
        <div class="col-md-8">
            <table class="table table-bordered table-striped text-center align-middle">
                <thead class="table-dark">
                <tr>
                    <th>Mã SP</th>
                    <th>Tên Sản Phẩm</th>
                    <th>Danh Mục</th>
                    <th>Chất Liệu</th>
                    <th>Kiểu Dáng</th>
                    <th>Trạng Thái</th>
                    <th>Cấu hình</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sp" items="${listSanPham}">
                    <tr>
                        <td><span class="badge bg-secondary">${sp.maSanPham}</span></td>
                        <td class="fw-bold">${sp.tenSanPham}</td>
                        <td>${sp.tenDanhMuc}</td>
                        <td>${sp.tenChatLieu}</td>
                        <td>${sp.tenKieuDang}</td>
                        <td>
                            <c:choose>
                                <c:when test="${sp.trangThai == 1}"><span class="badge bg-success">Hoạt động</span></c:when>
                                <c:otherwise><span class="badge bg-danger">Ngừng bán</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/san-pham-chi-tiet?idSanPham=${sp.id}" class="btn btn-sm btn-info fw-bold text-white mb-1">Chi tiết SP</a>
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