/*
1. Sự khác biệt cơ bản giữa POST và PUT
POST
   	Dùng để tạo mới tài nguyên
   	Không idempotent: gửi cùng một yêu cầu nhiều lần có thể tạo ra nhiều bản ghi khác nhau
   	Ví dụ: gửi POST /api/customers với dữ liệu khách hàng mới → mỗi lần gửi có thể tạo thêm một bản ghi mới
PUT
   	Dùng để cập nhật hoặc thay thế một tài nguyên đã tồn tại (thường xác định qua id)
   	Idempotent: gửi cùng một yêu cầu nhiều lần sẽ cho kết quả giống nhau, không tạo thêm bản ghi mới
   	Ví dụ: gửi PUT /api/customers/123 với dữ liệu cập nhật → dù gửi 10 lần,
   	bản ghi id=123 vẫn chỉ được cập nhật, không nhân bản
2. Tại sao dùng POST cho cả tạo mới và cập nhật dễ gây lỗi trùng lặp dữ liệu
- Trong đoạn code, code dùng POST cho cả hai hành động: tạo mới và cập nhật
- Khi client gửi dữ liệu có id nhưng không tìm thấy trong danh sách,
    logic hiện tại lại tạo mới một bản ghi thay vì báo lỗi hoặc yêu cầu dùng PUT
- Điều này phá vỡ quy tắc nghiệp vụ: cập nhật phải chỉnh sửa bản ghi hiện có, không được tạo thêm
- Vì POST không idempotent, nếu người dùng bấm nút "Cập nhật" nhiều lần (hoặc mạng gửi lại request),
    hệ thống có thể tạo ra nhiều bản ghi trùng lặp.
- Kết quả: dữ liệu khách hàng bị nhân bản, gây mất nhất quán và khó quản lý
 */