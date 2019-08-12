package cn.suse.controller;

import cn.suse.domain.Items;
import cn.suse.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;
    @RequestMapping("findDetail")
    public String findDetail(Model model){
        Items item = itemsService.findById(1);
        model.addAttribute("item",item);
        return "itemDetail";
    }
}
