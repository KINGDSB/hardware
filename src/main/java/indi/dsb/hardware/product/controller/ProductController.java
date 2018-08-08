package indi.dsb.hardware.product.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import indi.dsb.hardware.common.Log;
import indi.dsb.hardware.common.Page;
import indi.dsb.hardware.common.ResponseCode;
import indi.dsb.hardware.common.abstracts.AbstractController;
import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.common.utils.DateUtil;
import indi.dsb.hardware.common.utils.FileUtils;
import indi.dsb.hardware.common.utils.Response;
import indi.dsb.hardware.product.entity.Product;
import indi.dsb.hardware.product.entity.ProductSeries;
import indi.dsb.hardware.product.entity.ProductSeriesTreeView;
import indi.dsb.hardware.product.service.ProductSeriesService;
import indi.dsb.hardware.product.service.ProductService;
import indi.dsb.hardware.sys.service.SysResourceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by Administrator on 2017/12/7.
 */

@RestController
@RequestMapping("/product")
public class ProductController extends AbstractController<Product, Long> {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSeriesService productSeriesService;
    @Autowired
    private SysResourceService sysResourceService;

    @Override
    public AbstractService getService() {
        return productService;
    }

    @Override
    public SysResourceService getSysResourceService() {
        return sysResourceService;
    }

    @ApiOperation(value = "查询产品列表")
    @ApiImplicitParam(name = "product", value = "实体类", required = true, dataType = "Product")
    @Log(method = "产品列表", module = "产品管理")
    @RequestMapping(value = "/list")
    public ModelAndView find(HttpServletRequest request, HttpServletResponse response) {
        int start = ServletRequestUtils.getIntParameter(request, "start", 0);
        int index = ServletRequestUtils.getIntParameter(request, "index", 0);
        int length = ServletRequestUtils.getIntParameter(request, "length", 10);
        int draw = ServletRequestUtils.getIntParameter(request, "draw", 1);

        int status = ServletRequestUtils.getIntParameter(request, "status", 0);
        int newProduct = ServletRequestUtils.getIntParameter(request, "newProduct", 0);
        int type = ServletRequestUtils.getIntParameter(request, "type", 0);
        String keyWord = ServletRequestUtils.getStringParameter(request, "keyWord", null);
        Date createdDate = null;
        String strCreatedDate = ServletRequestUtils.getStringParameter(request, "createdDate", null);
        if (StringUtils.isNotBlank(strCreatedDate)) {
            createdDate = DateUtil.stringToDate(strCreatedDate);
        }

        Product product = new Product();
        product.setIsDeleted(false);
        if (status > 0) {
            product.setStatus(status);
        }
        if (type > 0) {
            product.setType(type);
        }
        if (newProduct > 0) {
            product.setNewProduct(newProduct);
        }
        if (StringUtils.isNotBlank(keyWord)) {
            product.setKeyWord(keyWord);
        }
        if (StringUtils.isNotBlank(keyWord)) {
            product.setKeyWord(keyWord);
        }
        if (null != createdDate) {
            product.setCreatedDate(createdDate);
        }

        Sort sort = new Sort(Direction.DESC, Arrays.asList(new String[] { "t1.created_date" }));
        PageRequest pageRequest = null;
        if (index > 0) {
        	pageRequest = new PageRequest((index - 1) * length, length, sort);
		} else {
			pageRequest = new PageRequest(start, length, sort);
		}
        Page<Product> list = productService.findList(product, pageRequest);
        list.setDraw(draw);
        list.setIndex(index);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @ApiOperation(value = "添加产品")
    @ApiImplicitParam(name = "product", required = true, dataType = "Product")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Log(module = "产品模块", method = "添加产品")
    public @ResponseBody String create(@RequestBody Product product) {
        try {
            productService.insert(product);
        } catch (Exception e) {
            logger.error("添加产品出错", e);
            e.printStackTrace();
            return new Response(ResponseCode.SERVER_ERROR).toJson();
        }
        return new Response(ResponseCode.SUCCESS).toJson();
    }

	@RequestMapping(value = "edit")
	@Log(module = "产品管理", method = "编辑产品")
	public @ResponseBody
    String edit(@RequestBody Product product) {
		Long id = product.getId();
		Example example = new Example(Product.class);
		example.createCriteria().andEqualTo("id", id);
		Product oProduct = productService.selectByExample(example).get(0);

		oProduct.setNameCn(product.getNameCn());
		oProduct.setNameEn(product.getNameEn());
		oProduct.setType(product.getType());
		oProduct.setStatus(product.getStatus());
		oProduct.setAmount(product.getAmount());
		oProduct.setAmountOld(product.getAmountOld());
		oProduct.setDescription(product.getDescription());

		productService.update(oProduct);
		return new Response(ResponseCode.SUCCESS).toJson();
	}

    @RequestMapping(value = "/auditProduct")
    @Log(module = "问题模块", method = "审核产品")
    public Response auditProduct(HttpServletRequest request, HttpServletResponse response) {

        long id = ServletRequestUtils.getLongParameter(request, "id", 0);
        int status = ServletRequestUtils.getIntParameter(request, "status", 0);

        Product product = productService.get(id);
        product.setStatus(status);
        productService.update(product);
        return new Response(ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "remove")
    @Log(module = "问题管理", method = "删除产品")
    public @ResponseBody
    String remove(@RequestParam("id") Long id) {
        if (id == null)
            return new Response(ResponseCode.REQUEST_ERROR).toJson();
        
        // 删除问题
        Product product = productService.get(id);
        if (null != product) {
            product.setIsDeleted(true);
            productService.update(product);
        }
        
        return new Response(ResponseCode.SUCCESS).toJson();
    }
    
    @RequestMapping(value = "/detail")
    public ModelAndView detail(HttpServletRequest request) {
        String action = ServletRequestUtils.getStringParameter(request, "action", null);

        ModelAndView modelAndView = new ModelAndView(getControllerMapping(request.getRequestURI(), request.getContextPath()) + "detail");
        modelAndView.addObject("action", action);
        return modelAndView;
    }
    
    @RequestMapping(value = "/getDetail")
    public ModelAndView frontDetail(HttpServletRequest request) {
        int id = ServletRequestUtils.getIntParameter(request, "id", 0);
        
        Product product = productService.getById(id);
        product.setPicture(FileUtils.getFileUrls(product.getPicture()));
        product.setPictures(FileUtils.getFileUrls(product.getPictures()));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @RequestMapping(value = "/randList")
    public ModelAndView randList(HttpServletRequest request, HttpServletResponse response) {
        String keyWord = ServletRequestUtils.getStringParameter(request, "keyWord", null);
        int size = ServletRequestUtils.getIntParameter(request, "size", 0);

        List<Product> list = productService.randList(keyWord, size);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        return modelAndView;
    }
    
    /**
     * @Title productSeriesTree 
     * @Description 产品系列树形
     * @return
     */
    @RequestMapping(value = "/productSeriesTree")
    public ModelAndView productSeriesTree() {

        List<ProductSeries> list = productSeriesService.selectAll();
        // 排序
        Collections.sort(list, new Comparator<ProductSeries>(){
            @Override
            public int compare(ProductSeries o1, ProductSeries o2) {
                int id1 = o1.getParentId().intValue();
                int id2 = o2.getParentId().intValue();
                return id1 - id2;
            }
        });
        
        ModelAndView modelAndView = new ModelAndView();
        ProductSeriesTreeView treeView = ProductSeriesTreeView.buildResourceTree(list, Collections.emptyList());
        modelAndView.addObject(treeView.getNodes());
        
        return modelAndView;
    }
    
    /**
     * <p>Title: getProductSeriesList</p> 
     * <p>Description: 获取产品系列列表</p> 
     * @date 2018-08-01 20:55:56 
     * @author dsb
     * @return
     */
    @RequestMapping(value = "/getProductSeriesList")
    public ModelAndView getProductSeriesList() {
        List<ProductSeries> list = productSeriesService.selectAll();
		// 处理图片地址
		for(ProductSeries res : list){
            res.setPhoto(FileUtils.getFileUrls(res.getPhoto()));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(list);
        return modelAndView;
    }
}
