<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Chọn Sản Phẩm Khuyến Mãi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        body { background-color: #f8f9fa; }
        .content { padding: 30px; }
    </style>
</head>
<body>
    <div class="container content">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h3 class="fw-bold">Chọn Sản Phẩm Áp Dụng Khuyến Mãi (ID Đợt: ${idDotGiamGia})</h3>
            <a href="${pageContext.request.contextPath}/khuyen-mai" class="btn btn-secondary fw-bold">
                <i class="bi bi-arrow-left"></i> Quay lại Danh sách
            </a>
        </div>

        <div class="card shadow-sm border-0">
            <div class="card-body p-4">
                <table class="table table-bordered table-hover text-center align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>Mã SKU</th>
                            <th>Tên Sản Phẩm</th>
                            <th>Giá Bán Gốc</th>
                            <th>Trạng Thái Khuyến Mãi</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="spct" items="${listSPCT}">
                            <!-- Kiểm tra xem ID sản phẩm đã có trong danh sách giảm giá chưa -->
                            <c:set var="daApDung" value="${listIdDaApDung.contains(spct.id)}" />
                            
                            <tr class="${daApDung ? 'table-success' : ''}">
                                <td class="fw-bold">${spct.maSpChiTiet}</td>
                                <td>${spct.tenSanPham}</td>
                                <td class="text-danger fw-bold">${spct.donGia} VNĐ</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${daApDung}">
                                            <span class="badge bg-success fs-6">Đang áp dụng sale</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-secondary fs-6">Không áp dụng</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${daApDung}">
                                            <a href="${pageContext.request.contextPath}/khuyen-mai-chi-tiet?id=${idDotGiamGia}&action=remove&idSpct=${spct.id}" 
                                               class="btn btn-sm btn-outline-danger fw-bold">Hủy áp dụng</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${pageContext.request.contextPath}/khuyen-mai-chi-tiet?id=${idDotGiamGia}&action=add&idSpct=${spct.id}" 
                                               class="btn btn-sm btn-success fw-bold">➕ Áp dụng ngay</a>
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
