package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.VivoShowroom;
import com.example.showroommanagement.service.VivoShowroomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/showroom")
public class VivoShowroomController {
    private final VivoShowroomService vivoShowroomService;
    public VivoShowroomController(VivoShowroomService vivoShowroomService){
        this.vivoShowroomService = vivoShowroomService;
    }

    @PostMapping("/create")
    public VivoShowroom createShowroom(@RequestBody final VivoShowroom VivoShowroom){
        return this.vivoShowroomService.createShowroom(VivoShowroom);
    }

    @GetMapping("/retrieve/{id}")
    public VivoShowroom retrieveShowroomById(@PathVariable final Integer id)
    {
        return this.vivoShowroomService.retrieveShowroomById(id);
    }
    @GetMapping("/retrieve")
    public List<VivoShowroom> retrieveShowroom()
    {
        return this.vivoShowroomService.retrieveShowroom();
    }
    @PutMapping("/update/{id}")
    public Integer updateShowroom(@PathVariable final Integer id, @RequestBody final VivoShowroom VivoShowroom) {
        return this.vivoShowroomService.updateShowroomById(VivoShowroom,id).getId();
    }
   @DeleteMapping("/remove/{id}")
   public Map<String, String> removeShowroomById(@PathVariable("id") final Integer id) {
       return this .vivoShowroomService.removeSchoolById(id);
   }

}
