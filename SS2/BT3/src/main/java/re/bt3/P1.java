/*
1. Vai trò của HTTP Status Codes trong API vững chắc
- HTTP Status Codes là ngôn ngữ chung giữa server và client để mô tả kết quả của một request
- Chúng giúp client phân biệt rõ ràng giữa các tình huống: thành công (200 OK), tạo mới (201 Created),
không tìm thấy (404 Not Found), lỗi server (500 Internal Server Error)…
- Nếu API chỉ trả về null hoặc {} mà không kèm status code, client không thể biết đó là:
    Một bản ghi rỗng hợp lệ
    Một lỗi do không tìm thấy dữ liệu
- Điều này làm client khó xử lý logic, dễ gây ra bug hoặc trải nghiệm người dùng kém.
Ví dụ:
- Trả về null → client có thể nghĩ dữ liệu hợp lệ nhưng rỗng
- Trả về ResponseEntity với HttpStatus.NOT_FOUND → client biết chắc là lỗi 404, có thể hiển thị thông báo “Mặt hàng không tồn tại”

2. Tại sao trả về null thay vì ResponseEntity với HttpStatus.NOT_FOUND gây khó khăn cho client
- null không mang thông tin ngữ nghĩa về lỗi
- Client phải đoán hoặc viết thêm logic kiểm tra dữ liệu rỗng
- Điều này làm tăng độ phức tạp và dễ dẫn đến xử lý sai (ví dụ: hiển thị danh sách trống thay vì báo lỗi)
- Ngược lại, ResponseEntity với mã lỗi chuẩn giúp client dễ dàng phân nhánh xử lý: hiển thị thông báo lỗi, retry, hoặc log lại

3. Tại sao Jackson Dataformat XML cần thiết cho Content Negotiation với XML
- Spring Boot mặc định hỗ trợ JSON nhờ Jackson
- Để hỗ trợ Content Negotiation (client chọn định dạng qua Accept: application/json hoặc Accept: application/xml), hệ thống cần có khả năng Tuần tự hóa/Giải tuần tự hóa cả JSON và XML
- Jackson Dataformat XML cung cấp bộ chuyển đổi để Spring Boot có thể tự động biến đối tượng Java thành XML
- Nhờ đó, cùng một API có thể trả về:
    JSON cho hệ thống mới (ứng dụng web/mobile)
    XML cho hệ thống cũ (legacy systems)
 */