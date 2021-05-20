package org.speed.big.company.service.web.workflow.wf_base_process_item;

import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxWFBaseProcessItemController.REST_URL)
public class AjaxWFBaseProcessItemController extends AbstractWFBaseProcessItemController{
    final static String REST_URL = "/ajax/workflow/wfBaseProcessItems";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFBaseProcessItem> wfBaseProcessItems(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public WFBaseProcessItem wfBaseProcessItem(@PathVariable int id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(WFBaseProcessItem wfBaseProcessItem){
        if (wfBaseProcessItem.isNew())
            super.create(wfBaseProcessItem);
        else
            super.update(wfBaseProcessItem, wfBaseProcessItem.getId());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteWFBPI(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFBaseProcessItem> filterWFBPI(WFBaseProcessItem wfBaseProcessItem){
        return super.filter(wfBaseProcessItem);
    }
}
