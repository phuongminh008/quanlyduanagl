$sidebarTemplate = @"
<div class="sidebar">
    <h2 class="sidebar-brand">S-FASHION</h2>
    <a href="`${pageContext.request.contextPath}/" {INDEX_ACTIVE}><i class="bi bi-house-door me-2"></i> Tổng quan</a>
    <a href="`${pageContext.request.contextPath}/danh-muc" {DANH_MUC_ACTIVE}><i class="bi bi-tags me-2"></i> Quản lý Danh mục</a>
    <a href="`${pageContext.request.contextPath}/san-pham" {SAN_PHAM_ACTIVE}><i class="bi bi-box-seam me-2"></i> Quản lý Sản phẩm</a>
    <a href="`${pageContext.request.contextPath}/nhan-vien" {NHAN_VIEN_ACTIVE}><i class="bi bi-person-badge me-2"></i> Quản lý Nhân viên</a>
    <a href="`${pageContext.request.contextPath}/khach-hang" {KHACH_HANG_ACTIVE}><i class="bi bi-people me-2"></i> Quản lý Khách hàng</a>
    <a href="`${pageContext.request.contextPath}/hoa-don" {HOA_DON_ACTIVE}><i class="bi bi-receipt me-2"></i> Quản lý Hóa đơn</a>
    <a href="`${pageContext.request.contextPath}/ban-hang" {BAN_HANG_ACTIVE}><i class="bi bi-cart-check me-2"></i> Quản lý Bán hàng</a>
    <a href="`${pageContext.request.contextPath}/khuyen-mai" {KHUYEN_MAI_ACTIVE}><i class="bi bi-gift me-2"></i> Quản lý Khuyến mãi</a>
    <a href="`${pageContext.request.contextPath}/doanh-thu" {DOANH_THU_ACTIVE}><i class="bi bi-graph-up-arrow me-2"></i> Quản lý Doanh thu</a>
</div>
"@

$files = Get-ChildItem -Path "C:\Users\Admin\Desktop\shoplocalbrand\ShopLocalBrand\src\main\webapp" -Filter "*.jsp" -Recurse

foreach ($f in $files) {
    if ($f.Name -match "index.jsp") { continue }

    $content = Get-Content -Raw -Path $f.FullName
    $activeKey = ""
    if ($f.Name -match "danh-muc") { $activeKey = "{DANH_MUC_ACTIVE}" }
    elseif ($f.Name -match "san-pham") { $activeKey = "{SAN_PHAM_ACTIVE}" }
    elseif ($f.Name -match "nhan-vien") { $activeKey = "{NHAN_VIEN_ACTIVE}" }
    elseif ($f.Name -match "khach-hang") { $activeKey = "{KHACH_HANG_ACTIVE}" }
    elseif ($f.Name -match "hoa-don") { $activeKey = "{HOA_DON_ACTIVE}" }
    elseif ($f.Name -match "ban-hang") { $activeKey = "{BAN_HANG_ACTIVE}" }
    elseif ($f.Name -match "khuyen-mai") { $activeKey = "{KHUYEN_MAI_ACTIVE}" }
    elseif ($f.Name -match "doanh-thu") { $activeKey = "{DOANH_THU_ACTIVE}" }
    else { $activeKey = "{UNKNOWN}" }
    
    $newSidebar = $sidebarTemplate.Replace($activeKey, 'class="active"')
    $newSidebar = $newSidebar -replace ' \{[A-Z_]+_ACTIVE\}', ''
    $newSidebar = $newSidebar -replace '\{[A-Z_]+_ACTIVE\}', ''
    
    $newContent = $content -replace '(?s)<div class="sidebar">.*?</div>', $newSidebar
    Set-Content -Path $f.FullName -Value $newContent -NoNewline
}
