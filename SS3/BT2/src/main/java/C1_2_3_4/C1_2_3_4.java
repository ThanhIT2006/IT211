/*
1. Các thành phần trong HTTP Request
    Request Line
        POST /api/sanpham HTTP/1.1

        POST: Phương thức HTTP dùng để gửi dữ liệu lên server để tạo tài nguyên mới.
        /api/sanpham: URL endpoint của API.
        HTTP/1.1: Phiên bản giao thức HTTP được sử dụng.
    Headers
        Host: example.com
        Content-Type: application/json
        Authorization: Bearer abc123
        Content-Length: 48
    Ý nghĩa từng header
        Host: example.com
            Xác định tên miền của server nhận request.
        Content-Type: application/json
            Cho biết dữ liệu gửi trong body có định dạng JSON.
        Authorization: Bearer abc123
            Chứa token xác thực để chứng minh người dùng có quyền truy cập API.
        Content-Length: 48
            Kích thước dữ liệu body (đơn vị byte).
    Body
        {"ten":"Laptop","gia":15000000,"tonkho":10}
    Dữ liệu sản phẩm được gửi lên server:
        ten: tên sản phẩm
        gia: giá sản phẩm
        tonkho: số lượng tồn kho
 */
/*
2. Các thành phần trong HTTP Response
    Status Line
        HTTP/1.1 201 Created

        HTTP/1.1: phiên bản HTTP.
        201 Created: yêu cầu thành công và tài nguyên mới đã được tạo.
    Response Headers
        Date: Mon, 10 Apr 2025 07:30:00 GMT
        Content-Type: application/json
        Location: /api/sanpham/101
    Ý nghĩa
        Date: Thời gian server gửi response.
        Content-Type: application/json: Dữ liệu trả về ở định dạng JSON.
        Location: /api/sanpham/101: URL của tài nguyên mới được tạo.
    Response Body
        {"id":101,"ten":"Laptop","gia":15000000,"tonkho":10}
        Server trả về thông tin sản phẩm vừa được tạo kèm id = 101.

    Mã trạng thái 201 Created thuộc nhóm nào?
        Thuộc nhóm 2xx (Successful Responses).
    Ý nghĩa:
        Request đã được xử lý thành công.
        Server đã tạo tài nguyên mới.
 */
/*
3. Nếu GET /api/sanpham/999 nhưng sản phẩm không tồn tại
    Mã trạng thái phù hợp: 404 Not Found
    Giải thích
        Client yêu cầu một tài nguyên không tồn tại trên server.
        Server không tìm thấy sản phẩm có ID 999.
4. Khi server gặp lỗi xử lý không xác định
    Mã trạng thái thường dùng: 500 Internal Server Error
    Giải thích
        Thuộc nhóm 5xx (Server Error).
        Cho biết server gặp lỗi nội bộ và không thể xử lý request thành công.
 */
