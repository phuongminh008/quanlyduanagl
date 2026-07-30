<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Quản lý Khách Hàng - S-Fashion</title>
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
    <a href="${pageContext.request.contextPath}/khach-hang" class="active"><i class="bi bi-people me-2"></i> Quản lý Khách hàng</a>
    <a href="${pageContext.request.contextPath}/hoa-don"><i class="bi bi-receipt me-2"></i> Quản lý Hóa đơn</a>
    <a href="${pageContext.request.contextPath}/ban-hang"><i class="bi bi-cart-check me-2"></i> Quản lý Bán hàng</a>
    <a href="${pageContext.request.contextPath}/khuyen-mai"><i class="bi bi-gift me-2"></i> Quản lý Khuyến mãi</a>
    <a href="${pageContext.request.contextPath}/doanh-thu"><i class="bi bi-graph-up-arrow me-2"></i> Quản lý Doanh thu</a>
</div>


<div class="content">
    <h2 class="mb-4 fw-bold">Quản lý Khách Hàng</h2>

    <div class="row">
        
        <div class="col-md-4">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white fw-bold fs-5">
                    ${khEdit == null ? 'Thêm Khách Hàng' : 'Sửa Khách Hàng'}
                </div>
                <div class="card-body p-4">
                    <form action="${pageContext.request.contextPath}/khach-hang" method="POST">
                        <input type="hidden" name="id" value="${khEdit.id}">

                        <div class="mb-3">
                            <label class="form-label fw-bold">Mã KH:</label>
                            <input type="text" class="form-control" name="maKh" value="${khEdit.maKh}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label fw-bold">Tên Khách Hàng:</label>
                            <input type="text" class="form-control" name="tenKh" value="${khEdit.tenKh}" required>
                        </div>

                        <div class="row mb-3">
                            <div class="col-6">
                                <label class="form-label fw-bold">Số Điện Thoại:</label>
                                <input type="text" class="form-control" name="sdt" value="${khEdit.sdt}" required>
                            </div>
                            <div class="col-6">
                                <label class="form-label fw-bold">Giới tính:</label>
                                <select class="form-select" name="gioiTinh">
                                    <option value="Nam" ${khEdit.gioiTinh == 'Nam' ? 'selected' : ''}>Nam</option>
                                    <option value="Nữ" ${khEdit.gioiTinh == 'Nữ' ? 'selected' : ''}>Nữ</option>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">Email:</label>
                            <input type="email" class="form-control" name="email" value="${khEdit.email}">
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-bold">Trạng Thái:</label><br>
                            <div class="form-check form-check-inline mt-2">
                                <input class="form-check-input" type="radio" name="trangThai" id="tt1" value="1" ${khEdit == null || khEdit.trangThai == 1 ? 'checked' : ''}>
                                <label class="form-check-label text-success fw-bold" for="tt1">Hoạt động</label>
                            </div>
                            <div class="form-check form-check-inline mt-2">
                                <input class="form-check-input" type="radio" name="trangThai" id="tt0" value="0" ${khEdit != null && khEdit.trangThai == 0 ? 'checked' : ''}>
                                <label class="form-check-label text-danger fw-bold" for="tt0">Ngừng hoạt động</label>
                            </div>
                        </div>

                        <button type="submit" class="btn ${khEdit == null ? 'btn-primary' : 'btn-warning'} w-100 fw-bold fs-5">
                            ${khEdit == null ? '➕ Lưu Khách Hàng' : '✏️ Cập Nhật'}
                        </button>
                    </form>
                </div>
            </div>
        </div>

        
        <div class="col-md-8">
            <table class="table table-bordered table-striped text-center align-middle">
                <thead class="table-dark">
                <tr>
                    <th>Mã KH</th>
                    <th>Tên Khách Hàng</th>
                    <th>Giới Tính</th>
                    <th>SĐT</th>
                    <th>Email</th>
                    <th>Trạng Thái</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="kh" items="${listKhachHang}">
                    <tr>
                        <td><span class="badge bg-secondary fs-6">${kh.maKh}</span></td>
                        <td class="fw-bold">${kh.tenKh}</td>
                        <td>${kh.gioiTinh}</td>
                        <td>${kh.sdt}</td>
                        <td>${kh.email}</td>
                        <td>
                            <c:choose>
                                <c:when test="${kh.trangThai == 1}"><span class="badge bg-success">Hoạt động</span></c:when>
                                <c:otherwise><span class="badge bg-danger">Khóa</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/khach-hang?action=edit&id=${kh.id}" class="btn btn-sm btn-warning fw-bold text-dark">Sửa</a>
                            <a href="${pageContext.request.contextPath}/khach-hang?action=delete&id=${kh.id}" class="btn btn-sm btn-danger fw-bold" onclick="return confirm('Xóa khách hàng này?');">Xóa</a>
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