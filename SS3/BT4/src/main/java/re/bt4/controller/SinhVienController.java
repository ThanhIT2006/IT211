package re.bt4.controller;

import re.bt4.model.SinhVien;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SinhVienController {

    @GetMapping(
            value = "/sinhvien",
            produces = {
                    "application/json",
                    "application/xml"
            }
    )
    public List<SinhVien> getSinhVien() {

        List<SinhVien> list = new ArrayList<>();

        list.add(new SinhVien("SV001", "Nguyen Van A", 8.5));
        list.add(new SinhVien("SV002", "Tran Thi B", 7.8));
        list.add(new SinhVien("SV003", "Le Van C", 9.1));

        return list;
    }
}