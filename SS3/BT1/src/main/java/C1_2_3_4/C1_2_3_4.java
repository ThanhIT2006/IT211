/*
C1: Web Service là một phương pháp cho phép các ứng dụng giao tiếp qua mạng,
sử dụng các giao thức và chuẩn mở để trao đổi dữ liệu và thực hiện các chức năng.

C2:
|------------------------------------------------------------------------|
| Tiêu chí                       | SOAP                  | REST          |
|------------------------------------------------------------------------|
|Giao thức truyền tải            | HTTP, SMTP, TCP, v.v. | HTTP          |
|Định dạng dữ liệu chính         | XML                   | JSON, XML     |
|Trạng thái (stateful/stateless) | Stateful              | Stateless     |
|Tính dễ dàng triển khai         | Phức tạp              | Đơn giản      |
|------------------------------------------------------------------------|

C3:
<sinhvien>
    <maso>SV001</maso>
    <hoten>Nguyễn Văn A</hoten>

    <diem>8.5</diem>
    <diem>7.0</diem>
    <diem>9.0</diem>
</sinhvien>

C4:
Cú pháp ngắn gọn, dễ đọc
Dung lượng nhỏ hơn
Xử lý nhanh hơn
Phù hợp với Web và REST API
Dễ chuyển đổi thành Object
Giảm tải cho server và client
*/