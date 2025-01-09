package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.VivoShowrooms;
import com.example.showroommanagement.service.ShowroomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/showroom")
public class ShowroomController {
    private final ShowroomService showroomService;
    public ShowroomController(ShowroomService showroomService){
        this.showroomService=showroomService;
    }

    @PostMapping("/create")
    public VivoShowrooms createShowroom(@RequestBody final VivoShowrooms VivoShowrooms){
        return this.showroomService.createShowroom(VivoShowrooms);
    }

    @GetMapping("/retrieve/{id}")
    public VivoShowrooms retrieveShowroomById(@PathVariable final Integer id)
    {
        return this.showroomService.retrieveShowroomById(id);
    }
    @GetMapping("/retrieve")
    public List<VivoShowrooms> retrieveShowroom()
    {
        return this.showroomService.retrieveShowroom();
    }
    @PutMapping("/update/{id}")
    public Integer updateShowroom(@PathVariable final Integer id, @RequestBody final VivoShowrooms VivoShowrooms) {
        return this.showroomService.updateShowroomById(VivoShowrooms,id).getId();
    }
   @DeleteMapping("/remove/{id}")
   public Map<String, String> removeShowroomById(@PathVariable("id") final Integer id) {
       return this .showroomService.removeSchoolById(id);
   }

}
