package com.aikoni6.project.Services.system;

import com.aikoni6.project.Entities.system.ReferenceList;
import com.aikoni6.project.Repositories.system.ReferenceListRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReferenceListService {
    @Autowired
    private ReferenceListRepo referenceListRepo;

    public List<Long> getAllContentIDByRefID(Long ID) {
        Optional<ReferenceList> startCheck = referenceListRepo.findById(ID);
        if (startCheck.isEmpty()) return new ArrayList<>();

        ReferenceList start = startCheck.get();
        while (start.getPreviousRef() != null && referenceListRepo.findById(start.getPreviousRef()).isPresent())
            start = referenceListRepo.findById(start.getPreviousRef()).get();

        List<Long> result = new ArrayList<>();
        while (start.getNextRef() != null){
            Long content = getContentIDByRefID(start.getContentID());
            if(content != null)result.add(content);
        }
        return result;
    }
    public List<ReferenceList> collectRefList(ReferenceList start){
        while (start.getPreviousRef() != null && referenceListRepo.findById(start.getPreviousRef()).isPresent())
            start = referenceListRepo.findById(start.getPreviousRef()).get();

        List<ReferenceList> result = new ArrayList<>();
        while (start.getNextRef() != null){
            ReferenceList content = getRefListByID(start.getContentID());
            if(content != null)result.add(content);
        }
        return result;
    }
    public Long getContentIDByRefID(Long ID){
        Optional<ReferenceList> refList = referenceListRepo.findById(ID);
        return refList.map(ReferenceList::getContentID).orElse(null);
    }
    public ReferenceList getRefListByID(Long ID){
        return referenceListRepo.findById(ID).orElse(null);
    }
    public ReferenceList addToList(Long contentID){
        ReferenceList list = new ReferenceList();
        list.setContentID(contentID);
        list = referenceListRepo.save(list);
        return list;
    }
    public ReferenceList addToList(ReferenceList prevList, Long contentID){
        ReferenceList currList = addToList(contentID);
        currList.setPreviousRef(prevList.getId());
        prevList.setNextRef(currList.getId());
        referenceListRepo.save(prevList);
        return referenceListRepo.save(currList);
    }
}
