<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Quản lý Danh Mục - S-Fashion</title>
  <!-- Nhúng thư viện Bootstrap 5 và Icon -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

  <!-- CSS Tùy chỉnh để giống hệt thiết kế trong ảnh -->
  <style>
    body { background-color: #ffffff; overflow-x: hidden; }

    /* Cột Sidebar bên trái */
    .sidebar {
      width: 250px;
      background-color: #212529;
      min-height: 100vh;
      position: fixed;
      top: 0; left: 0;
    }
    .sidebar-brand {
      background-color: #0d6efd;
      color: white;
      padding: 18px;
      text-align: center;
      font-weight: bold;
      font-size: 24px;
      margin: 0;
    }
    .sidebar a {
      color: #d3d3d3;
      text-decoration: none;
      padding: 15px 20px;
      display: block;
      border-bottom: 1px solid #343a40;
      font-size: 15px;
    }
    .sidebar a:hover, .sidebar a.active {
      background-color: #0d6efd;
      color: white;
      border-left: 4px solid white;
    }

    /* Phần Nội dung bên phải */
    .content {
      margin-left: 250px;
      padding: 30px;
      width: calc(100% - 250px);
    }

    /* Tùy chỉnh Tiêu đề bảng đen như ảnh */
    .table thead th {
      background-color: #343a40 !important;
      color: white !important;
      border-bottom: none;
    }
    .table td { vertical-align: middle; }
  </style>
</head>
<body>

<!-- SIDEBAR CHUẨN ĐẦY ĐỦ CÁC CHỨC NĂNG -->
<div class="sidebar">
    <h2 class="sidebar-brand">S-FASHION</h2>
    <a href="${pageContext.request.contextPath}/"><i class="bi bi-house-door me-2"></i> Tổng quan</a>
    <a href="${pageContext.request.contextPath}/danh-muc" class="active"><i class="bi bi-tags me-2"></i> Quản lý Danh mục</a>
    <a href="${pageContext.request.contextPath}/san-pham"><i class="bi bi-box-seam me-2"></i> Quản lý Sản phẩm</a>
    <a href="${pageContext.request.contextPath}/nhan-vien"><i class="bi bi-person-badge me-2"></i> Quản lý Nhân viên</a>
    <a href="${pageContext.request.contextPath}/khach-hang"><i class="bi bi-people me-2"></i> Quản lý Khách hàng</a>
    <a href="${pageContext.request.contextPath}/hoa-don"><i class="bi bi-receipt me-2"></i> Quản lý Hóa đơn</a>
    <a href="${pageContext.request.contextPath}/ban-hang"><i class="bi bi-cart-check me-2"></i> Quản lý Bán hàng</a>
    <a href="${pageContext.request.contextPath}/khuyen-mai"><i class="bi bi-gift me-2"></i> Quản lý Khuyến mãi</a>
    <a href="${pageContext.request.contextPath}/doanh-thu"><i class="bi bi-graph-up-arrow me-2"></i> Quản lý Doanh thu</a>
</div>

<!-- MAIN CONTENT -->
<div class="content">
  <h2 class="mb-4 fw-bold">Quản lý Danh Mục</h2>

  <div class="row">
    <!-- CỘT TRÁI: FORM THÊM/SỬA -->
    <div class="col-md-5">
      <div class="card shadow-sm border-0">
        <div class="card-header bg-primary text-white fw-bold fs-5">
          ${dmEdit == null ? 'Thêm Danh Mục Mới' : 'Sửa Danh Mục'}
        </div>
        <div class="card-body p-4">
          <form action="${pageContext.request.contextPath}/danh-muc" method="POST">
            <input type="hidden" name="id" value="${dmEdit.id}">

            <div class="mb-3">
              <label class="form-label fw-bold">Mã Danh Mục:</label>
              <input type="text" class="form-control" name="maDanhMuc" value="${dmEdit.maDanhMuc}" required>
            </div>

            <div class="mb-3">
              <label class="form-label fw-bold">Tên Danh Mục:</label>
              <input type="text" class="form-control" name="tenDanhMuc" value="${dmEdit.tenDanhMuc}" required>
            </div>

            <div class="mb-4">
              <label class="form-label fw-bold">Trạng Thái:</label><br>
              <div class="form-check mt-2">
                <input class="form-check-input" type="radio" name="trangThai" id="tt1" value="1" ${dmEdit == null || dmEdit.trangThai == 1 ? 'checked' : ''}>
                <label class="form-check-label text-success fw-bold" for="tt1">Hoạt động</label>
              </div>
              <div class="form-check mt-2">
                <input class="form-check-input" type="radio" name="trangThai" id="tt0" value="0" ${dmEdit != null && dmEdit.trangThai == 0 ? 'checked' : ''}>
                <label class="form-check-label text-danger fw-bold" for="tt0">Ngừng hoạt động</label>
              </div>
            </div>

            <button type="submit" class="btn ${dmEdit == null ? 'btn-primary' : 'btn-warning'} w-100 fw-bold fs-5">
              ${dmEdit == null ? '➕ Lưu Danh Mục' : '✏️ Cập Nhật'}
            </button>
          </form>
        </div>
      </div>
    </div>

    <!-- CỘT PHẢI: BẢNG DỮ LIỆU -->
    <div class="col-md-7">
      <table class="table table-bordered table-striped text-center">
        <thead>
        <tr>
          <th>ID</th>
          <th>Mã DM</th>
          <th>Tên Danh Mục</th>
          <th>Trạng Thái</th>
          <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <!-- Dữ liệu được lấy trực tiếp từ bảng danh_muc[cite: 2] -->
        <c:forEach var="dm" items="${listDanhMuc}">
          <tr>
            <td>${dm.id}</td>
            <td><span class="badge bg-secondary fs-6">${dm.maDanhMuc}</span></td>
            <td class="fw-bold">${dm.tenDanhMuc}</td>
            <td>
              <c:choose>
                <c:when test="${dm.trangThai == 1}">
                  <span class="badge bg-success">Hoạt động</span>
                </c:when>
                <c:otherwise>
                  <span class="badge bg-danger">Ngừng hoạt động</span>
                </c:otherwise>
              </c:choose>
            </td>
            <td>
              <a href="${pageContext.request.contextPath}/danh-muc?action=edit&id=${dm.id}" class="btn btn-sm btn-warning fw-bold text-dark">Sửa</a>
              <a href="${pageContext.request.contextPath}/danh-muc?action=delete&id=${dm.id}" class="btn btn-sm btn-danger fw-bold" onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<!-- Script Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>