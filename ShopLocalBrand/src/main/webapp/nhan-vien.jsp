<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<!-- Giữ nguyên phần <head> và CSS Sidebar như trang danh-muc.jsp -->
<head>
    <title>Quản lý Nhân Viên - S-Fashion</title>
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
    <a href="${pageContext.request.contextPath}/san-pham"><i class="bi bi-box-seam me-2"></i> Quản lý Sản phẩm</a>
    <a href="${pageContext.request.contextPath}/nhan-vien" class="active"><i class="bi bi-person-badge me-2"></i> Quản lý Nhân viên</a>
    <a href="${pageContext.request.contextPath}/khach-hang"><i class="bi bi-people me-2"></i> Quản lý Khách hàng</a>
    <a href="${pageContext.request.contextPath}/hoa-don"><i class="bi bi-receipt me-2"></i> Quản lý Hóa đơn</a>
    <a href="${pageContext.request.contextPath}/ban-hang"><i class="bi bi-cart-check me-2"></i> Quản lý Bán hàng</a>
    <a href="${pageContext.request.contextPath}/khuyen-mai"><i class="bi bi-gift me-2"></i> Quản lý Khuyến mãi</a>
    <a href="${pageContext.request.contextPath}/doanh-thu"><i class="bi bi-graph-up-arrow me-2"></i> Quản lý Doanh thu</a>
</div>

<!-- NỘI DUNG -->
<div class="content">
    <!-- MAIN CONTENT -->
    <div class="content">
        <h2 class="mb-4 fw-bold">Quản lý Nhân Viên</h2>

        <div class="row">
            <!-- CỘT TRÁI: FORM THÊM/SỬA -->
            <div class="col-md-4">
                <div class="card shadow-sm border-0">
                    <div class="card-header bg-primary text-white fw-bold fs-5">
                        ${nvEdit == null ? 'Thêm Nhân Viên Mới' : 'Sửa Nhân Viên'}
                    </div>
                    <div class="card-body p-4">
                        <form action="${pageContext.request.contextPath}/nhan-vien" method="POST">
                            <input type="hidden" name="id" value="${nvEdit.id}">

                            <div class="mb-3">
                                <label class="form-label fw-bold">Mã NV:</label>
                                <input type="text" class="form-control" name="maNv" value="${nvEdit.maNv}" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label fw-bold">Họ và Tên:</label>
                                <input type="text" class="form-control" name="tenNv" value="${nvEdit.tenNv}" required>
                            </div>

                            <div class="row mb-3">
                                <div class="col-6">
                                    <label class="form-label fw-bold">Giới tính:</label>
                                    <select class="form-select" name="gioiTinh">
                                        <option value="Nam" ${nvEdit.gioiTinh == 'Nam' ? 'selected' : ''}>Nam</option>
                                        <option value="Nữ" ${nvEdit.gioiTinh == 'Nữ' ? 'selected' : ''}>Nữ</option>
                                    </select>
                                </div>
                                <div class="col-6">
                                    <label class="form-label fw-bold">SĐT:</label>
                                    <input type="text" class="form-control" name="sdt" value="${nvEdit.sdt}">
                                </div>
                            </div>

                            <c:if test="${nvEdit == null}">
                                <div class="mb-3">
                                    <label class="form-label fw-bold">Tên đăng nhập:</label>
                                    <input type="text" class="form-control" name="tenDangNhap" required>
                                    <small class="text-muted">Mật khẩu mặc định: 123456</small>
                                </div>
                            </c:if>

                            <div class="mb-3">
                                <label class="form-label fw-bold">Chức vụ:</label>
                                <select class="form-select" name="idChucVu">
                                    <option value="1" ${nvEdit.idChucVu == 1 ? 'selected' : ''}>Quản lý</option>
                                    <option value="2" ${nvEdit.idChucVu == 2 ? 'selected' : ''}>Nhân viên bán hàng</option>
                                </select>
                            </div>

                            <div class="mb-4">
                                <label class="form-label fw-bold">Trạng Thái:</label><br>
                                <div class="form-check form-check-inline mt-2">
                                    <input class="form-check-input" type="radio" name="trangThai" id="tt1" value="1" ${nvEdit == null || nvEdit.trangThai == 1 ? 'checked' : ''}>
                                    <label class="form-check-label text-success fw-bold" for="tt1">Đang làm việc</label>
                                </div>
                                <div class="form-check form-check-inline mt-2">
                                    <input class="form-check-input" type="radio" name="trangThai" id="tt0" value="0" ${nvEdit != null && nvEdit.trangThai == 0 ? 'checked' : ''}>
                                    <label class="form-check-label text-danger fw-bold" for="tt0">Đã nghỉ</label>
                                </div>
                            </div>

                            <button type="submit" class="btn ${nvEdit == null ? 'btn-primary' : 'btn-warning'} w-100 fw-bold fs-5">
                                ${nvEdit == null ? '➕ Lưu Nhân Viên' : '✏️ Cập Nhật'}
                            </button>
                        </form>
                    </div>
                </div>
            </div>

            <!-- CỘT PHẢI: BẢNG DỮ LIỆU -->
            <div class="col-md-8">
                <table class="table table-bordered table-striped text-center align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>Mã NV</th>
                        <th>Tên Nhân Viên</th>
                        <th>Giới Tính</th>
                        <th>SĐT</th>
                        <th>Chức Vụ</th>
                        <th>Trạng Thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="nv" items="${listNhanVien}">
                        <tr>
                            <td><span class="badge bg-secondary fs-6">${nv.maNv}</span></td>
                            <td class="fw-bold">${nv.tenNv}</td>
                            <td>${nv.gioiTinh}</td>
                            <td>${nv.sdt}</td>
                            <td><span class="text-primary fw-bold">${nv.diaChi}</span></td>
                            <td>
                                <c:choose>
                                    <c:when test="${nv.trangThai == 1}"><span class="badge bg-success">Làm việc</span></c:when>
                                    <c:otherwise><span class="badge bg-danger">Đã nghỉ</span></c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/nhan-vien?action=edit&id=${nv.id}" class="btn btn-sm btn-warning fw-bold text-dark">Sửa</a>
                                <a href="${pageContext.request.contextPath}/nhan-vien?action=delete&id=${nv.id}" class="btn btn-sm btn-danger fw-bold" onclick="return confirm('Xóa nhân viên này?');">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>