/*
@PathVariable dùng để lấy giá trị nằm trực tiếp trên đường dẫn URL,
thường dùng cho định danh của một tài nguyên cụ thể. Ví dụ: /api/v1/movies/M001
Trong trường hợp này M001 là ID của phim nên sử dụng:@PathVariable

@RequestParam dùng để lấy tham số phía sau dấu ? trên URL, thường dùng cho tìm kiếm,
lọc hoặc phân trang. Ví dụ: /api/v1/movies?genre=Sci-Fi
Trong trường hợp này genre là điều kiện lọc nên sử dụng:@RequestParam

Không nên hoán đổi hai loại này cho nhau vì:
@PathVariable đại diện cho tài nguyên cụ thể.
@RequestParam đại diện cho điều kiện truy vấn hoặc lọc dữ liệu.
 */