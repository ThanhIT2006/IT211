/*
Nguyên nhân gốc rễ nằm ở chỗ phương thức getHotProducts() đang trả về một chuỗi (String) thay vì dữ liệu JSON hợp lệ:
- Trong đoạn code, code tạo danh sách List<Product> và thêm các đối tượng Product vào
- Tuy nhiên, thay vì để Spring Boot tự động serialize danh sách này thành JSON
(nhờ cơ chế Jackson tích hợp sẵn), code lại gọi products.toString()
- Kết quả của toString() trên một List<Product> chỉ là một chuỗi biểu diễn mặc định của các đối tượng Java,
ví dụ: [Product@123, Product@456]
hoặc nếu lớp Product không override toString(), nó sẽ hiển thị địa chỉ bộ nhớ hoặc chuỗi không có cấu trúc JSON
- Các ứng dụng client khi nhận phản hồi này sẽ cố gắng parse thành JSON.
Nhưng vì dữ liệu không phải JSON hợp lệ, quá trình parse thất bại, dẫn đến việc không thể lấy được danh sách sản phẩm
- Web Service không trả về JSON mà chỉ trả về chuỗi toString() của danh sách đối tượng.
Đây là lý do khiến client không thể phân tích cú pháp dữ liệu
 */