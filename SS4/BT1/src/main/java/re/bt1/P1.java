/*
Phương thức getMovies() đang dùng:
return movies.toString();

Khi gọi toString() trên List<Movie>, Java sẽ gọi toString() mặc định của từng object Movie.
Do class Movie không override phương thức toString(), nên kết quả trả về là:
[Movie@3a1b2c3d, Movie@4f2e1a0b]

Đây chỉ là chuỗi văn bản thông thường, không phải JSON hợp lệ nên frontend không thể parse và hiển thị dữ liệu.
Nguyên nhân gốc rễ là trả về movies.toString() thay vì trả về trực tiếp List<Movie> để Spring Boot tự động chuyển đổi object sang JSON.
 */
