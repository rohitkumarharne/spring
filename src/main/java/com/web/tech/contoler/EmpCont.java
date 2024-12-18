package com.web.tech.contoler;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.web.tech.model.Employee;
import com.web.tech.model.ImageHelper;
import com.web.tech.service.Empsarvice;

import java.util.List;

@RestController
@CrossOrigin("https://react-lyart-sigma-25.vercel.app/")  // Allow requests from React app
public class EmpCont {

    @Autowired
    Empsarvice empser;
    
    @Autowired
    ImageHelper imfs;

    @PostMapping("/empsave")
    public String empSaveRecord(@ModelAttribute Employee employee, @RequestParam("img") MultipartFile img) {
        System.out.println(employee);
        System.out.println(img);

        // Save image using ImageHelper (method should save image to static/imgs)
        imfs.isSaveImage(img);
        
        // Get image name and set it to employee
        String imageName = img.getOriginalFilename();
        employee.setImgname(imageName);
        
        // Save employee data
        empser.isSaveData(employee);

        return "save";
    }

    @GetMapping("/getall")
    public List<Employee> getalllist() {
        return empser.getallEmployees();
    }

    // Endpoint to fetch images dynamically from static/imgs folder
    @GetMapping("/images/{imageName}")
    public Resource getImage(@PathVariable String imageName) {
        try {
            Path imagePath = Paths.get("src/main/resources/static/imgs").resolve(imageName);
            Resource resource = new UrlResource(imagePath.toUri());
            
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the image file.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving image: " + imageName, e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") int eid) {
        empser.deleteemp(eid);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Employee e) {
        e.setId(id);
        empser.upadterecodebyId(e);
    }
}
