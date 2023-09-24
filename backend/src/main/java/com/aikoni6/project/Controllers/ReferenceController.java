package com.aikoni6.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aikoni6.project.Services.system.ReferenceListService;

@RestController("refs")
public class ReferenceController {
    @Autowired
    private ReferenceListService referenceListService;

    @CrossOrigin
    @GetMapping("reference/{reflist_id}")
    public ResponseEntity<String> getFullRefListID(@PathVariable("reflist_id")Long ID){
        var refList = referenceListService.getRefListByID(ID);
        if(refList != null) {
            var res = referenceListService.collectRefList(refList);
            if(!res.isEmpty()) return ResponseEntity.ok().body(res.toString());
        }
        return ResponseEntity.badRequest().body("Error: couldn't find content!");
    }
    @CrossOrigin
    @PostMapping("reference/{reflist_id}")
    public ResponseEntity<String> createList(@PathVariable("reflist_id")Long ID){

        return ResponseEntity.badRequest().build();
    }

}
