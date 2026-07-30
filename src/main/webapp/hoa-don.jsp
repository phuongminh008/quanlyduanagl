<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Quản lý Hóa Đơn - S-Fashion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        body {
            overflow-x: hidden;
        }

        .sidebar {
            width: 250px;
            background-color: #212529;
            min-height: 100vh;
            position: fixed;
        }

        .sidebar-brand {
            background-color: #0d6efd;
            color: white;
            padding: 18px;
            text-align: center;
            font-weight: bold;
            margin: 0;
        }

        .sidebar a {
            color: #d3d3d3;
            text-decoration: none;
            padding: 15px 20px;
            display: block;
            border-bottom: 1px solid #343a40;
        }

        .sidebar a:hover, .sidebar a.active {
            background-color: #0d6efd;
            color: white;
            border-left: 4px solid white;
        }

        .content {
            margin-left: 250px;
            padding: 30px;
        }
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
    <a href="${pageContext.request.contextPath}/hoa-don" class="active"><i class="bi bi-receipt me-2"></i> Quản lý Hóa đơn</a>
    <a href="${pageContext.request.contextPath}/ban-hang"><i class="bi bi-cart-check me-2"></i> Quản lý Bán hàng</a>
    <a href="${pageContext.request.contextPath}/khuyen-mai"><i class="bi bi-gift me-2"></i> Quản lý Khuyến mãi</a>
    <a href="${pageContext.request.contextPath}/doanh-thu"><i class="bi bi-graph-up-arrow me-2"></i> Quản lý Doanh thu</a>
</div>

<div class="content">
    <h2 class="mb-4 fw-bold">Quản lý Hóa Đơn</h2>

    <div class="card shadow-sm border-0">
        <div class="card-body p-4">
            <table class="table table-hover table-bordered text-center align-middle">
                <thead class="table-dark">
                <tr>
                    <th>Mã Hóa Đơn</th>
                    <th>Ngày Tạo</th>
                    <th>Tổng Tiền</th>
                    <th>Trạng Thái</th>
                    <th>Hành Động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="hd" items="${listHD}">
                    <tr>
                        <td><span class="badge bg-secondary fs-6">${hd.maHoaDon}</span></td>
                        <td>${hd.ngayTao}</td>
                        <td class="text-danger fw-bold">${hd.tongTien} VNĐ</td>
                        <td>
                            <c:choose>
                                <c:when test="${hd.trangThai == 1}"><span
                                        class="badge bg-success">Đã thanh toán</span></c:when>
                                <c:when test="${hd.trangThai == 0}"><span class="badge bg-warning text-dark">Chờ thanh toán</span></c:when>
                                <c:otherwise><span class="badge bg-danger">Đã hủy</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            
                            <a href="${pageContext.request.contextPath}/hoa-don-chi-tiet?idHoaDon=${hd.id}"
                               class="btn btn-sm btn-info text-white fw-bold">Chi tiết</a>

                            
                            <c:if test="${hd.trangThai == 0}">
                                <a href="${pageContext.request.contextPath}/hoa-don?action=updateStatus&id=${hd.id}&status=1"
                                   class="btn btn-sm btn-success fw-bold"
                                   onclick="return confirm('Xác nhận đã thanh toán?');">Thu tiền</a>
                                <a href="${pageContext.request.contextPath}/hoa-don?action=updateStatus&id=${hd.id}&status=2"
                                   class="btn btn-sm btn-danger fw-bold" onclick="return confirm('Hủy hóa đơn này?');">Hủy</a>
                            </c:if>
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