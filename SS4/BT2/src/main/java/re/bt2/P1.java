/*
So sánh POST và PUT
|------------------------------------------------------------------------------------|
|Tiêu chí           | POST              	    | PUT                                |
|------------------------------------------------------------------------------------|
|Mục đích       	| Tạo tài nguyên mới	    | Cập nhật hoặc thay thế tài nguyên  |
|Idempotent         | Không idempotent	        | Có idempotent                      |
|Khi gọi nhiều lần  | Tạo nhiều dữ liệu mới	    | Kết quả vẫn như một lần gọi        |
|Khi nào dùng       | Khi server tự sinh ID	    | Khi client đã biết ID tài nguyên   |
|------------------------------------------------------------------------------------|

Đồng nghiệp B đúng vì API này dùng để tạo đơn hàng mới và hệ thống tự sinh orderId.
Trong RESTful API, trường hợp tạo mới tài nguyên nên sử dụng: POST /api/v1/orders
POST phù hợp vì mỗi lần gửi request sẽ tạo ra một đơn hàng mới khác nhau.
PUT thường dùng khi đã biết ID của tài nguyên và muốn cập nhật hoặc thay thế tài nguyên đó, ví dụ: PUT /api/v1/orders/ORD001
 */