package com.agjava.dvdexchange.controller;

import com.agjava.dvdexchange.model.DiskForm;
import com.agjava.dvdexchange.model.entity.Disk;
import com.agjava.dvdexchange.model.entity.TakenItem;
import com.agjava.dvdexchange.service.IDiskService;
import com.agjava.dvdexchange.service.ITakeItemService;
import com.agjava.dvdexchange.validator.DiskFormValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class DiskController {

    private IDiskService diskService;

    private ITakeItemService takeItemService;

    private DiskFormValidator diskFormValidator;

    @RequestMapping(path = "/disk/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("diskForm", new DiskForm());
        return "addDisk";
    }

    @RequestMapping(path = "/disk/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("diskForm") DiskForm diskForm, BindingResult bindingResult) {
        this.diskFormValidator.validate(diskForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "addDisk";
        }

        this.diskService.createDisk(diskForm.getName());
        // return "redirect:availableDisks";
        return "redirect:/disk/available";
    }

    @RequestMapping(path = "/disk/available/list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Disk> availableDisks() {
        return this.diskService.availableDisksForCurrentUser();
    }

    @RequestMapping(path = "/disk/taken/list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<TakenItem> taken() {
        return this.takeItemService.takenDisksForCurrentUser();
    }

    @RequestMapping(path = "/disk/given/list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<TakenItem> given() {
        return this.takeItemService.givenDisksForCurrentUser();
    }

    @RequestMapping(path = "/disk/take", method = RequestMethod.POST,
            produces = {MediaType.TEXT_PLAIN_VALUE},
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String take(@RequestBody Disk disk) {
        log.info("request /take disk {}", disk.toString());
        this.takeItemService.takeDisk(disk);
        return "take success";
    }

    @RequestMapping(path = "/disk/return", method = RequestMethod.POST,
            produces = {MediaType.TEXT_PLAIN_VALUE},
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String returnDisk(@RequestBody TakenItem item) {
        log.info("request /return TakeItem {}", item.toString());
        this.takeItemService.returnDisk(item);
        return "return disk success";
    }
}