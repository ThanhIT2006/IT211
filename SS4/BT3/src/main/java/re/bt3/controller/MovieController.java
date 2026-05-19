package re.bt3.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @GetMapping("/{movieId}")
    public String getMovieById(@PathVariable String movieId) {
        return "Thông tin chi tiết phim có ID = " + movieId;
    }

    @GetMapping
    public List<String> getMoviesByGenre(@RequestParam String genre) {

        if (genre.equalsIgnoreCase("Sci-Fi")) {
            return Arrays.asList(
                    "Interstellar",
                    "Inception",
                    "The Matrix"
            );
        }

        return Arrays.asList("Không tìm thấy phim thuộc thể loại: " + genre);
    }
}