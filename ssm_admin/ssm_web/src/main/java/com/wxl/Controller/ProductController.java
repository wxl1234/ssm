package com.wxl.Controller;

import com.github.pagehelper.PageInfo;
import com.wxl.domain.Product;
import com.wxl.service.IProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 产品控制层
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private IProduceService produceService;

    /**
     * 查询所有商品信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "pageNum",defaultValue = "1",required = true)Integer pageNum,
                                @RequestParam(name = "size",defaultValue = "4",required = true)Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("product-list");
        List<Product> products = produceService.findAll(pageNum, size);
        mv.addObject("pageInfo",new PageInfo(products));
        return mv;
    }

    @RequestMapping(value = "/save.do",method = RequestMethod.POST)
    public String save(Product product) throws Exception {
        produceService.saveProduct(product);
        return "redirect:/product/findAll.do";
    }
}
