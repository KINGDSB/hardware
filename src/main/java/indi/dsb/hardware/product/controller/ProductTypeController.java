package indi.dsb.hardware.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import indi.dsb.hardware.common.Log;
import indi.dsb.hardware.common.Page;
import indi.dsb.hardware.common.ResponseCode;
import indi.dsb.hardware.common.abstracts.AbstractController;
import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.common.utils.Response;
import indi.dsb.hardware.product.entity.ProductType;
import indi.dsb.hardware.product.service.ProductTypeService;
import indi.dsb.hardware.sys.entity.SysUser;
import indi.dsb.hardware.sys.service.SysResourceService;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping(value = "productType")
public class ProductTypeController extends AbstractController<ProductType, Long> {
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private SysResourceService sysResourceService;

	@Override
	public AbstractService<ProductType, Long> getService() {
		return productTypeService;
	}

	@Override
	public SysResourceService getSysResourceService() {
		return sysResourceService;
	}

	@RequestMapping(value = "/list")
	@Log(module = "产品类型管理", method = "产品类型列表")
	public ModelAndView find(HttpServletRequest request, HttpServletResponse response) {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int length = ServletRequestUtils.getIntParameter(request, "length", 10);
		int draw = ServletRequestUtils.getIntParameter(request, "draw", 1);
		String nameCn = ServletRequestUtils.getStringParameter(request, "nameCn", null);
		String nameEn = ServletRequestUtils.getStringParameter(request, "nameEn", null);

		RowBounds rowBounds = new RowBounds(start, length);

		Example example = new Example(ProductType.class);
		if (!StringUtils.isBlank(nameCn))
			example.createCriteria().andLike("nameCn", "%"+nameCn+"%");
		if (!StringUtils.isBlank(nameEn))
			example.createCriteria().andLike("nameEn", "%"+nameEn+"%");

		example.createCriteria().andEqualTo("isDeleted", false);
		Page<ProductType> res = productTypeService.selectPage(rowBounds, example);
		res.setDraw(draw);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", res);

		return modelAndView;
	}

	@RequestMapping(value = "remove")
	@Log(module = "产品类型管理", method = "删除产品类型")
	public @ResponseBody
    String remove(@RequestParam("id") Long id) {
		if (id == null)
			return new Response(ResponseCode.REQUEST_ERROR).toJson();
		Example example = new Example(SysUser.class);
		example.createCriteria().andEqualTo("id", id);

		List<ProductType> selectByExample = productTypeService.selectByExample(example);
		if (selectByExample.size() > 0) {
			ProductType productType = selectByExample.get(0);
			productType.setIsDeleted(true);
			productTypeService.update(productType);
		}
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "create")
	@Log(module = "产品类型管理", method = "添加产品类型")
	public @ResponseBody
    String create(@RequestBody ProductType productType) {
		productTypeService.insert(productType);
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "edit")
	@Log(module = "产品类型管理", method = "编辑产品类型")
	public @ResponseBody
    String edit(@RequestBody ProductType productType) {
		Long id = productType.getId();
		Example example = new Example(ProductType.class);
		example.createCriteria().andEqualTo("id", id);
		ProductType oProductType = productTypeService.selectByExample(example).get(0);

		oProductType.setNameCn(productType.getNameCn());
		oProductType.setNameEn(productType.getNameEn());

		productTypeService.update(oProductType);
		return new Response(ResponseCode.SUCCESS).toJson();
	}
	
    @RequestMapping(value = "/getSelect")
    @Log(module = "产品类型管理", method = "获取产品类型下拉列表")
    public ModelAndView getSelect(HttpServletRequest request, HttpServletResponse response) {

        Example example = new Example(ProductType.class);
        example.createCriteria().andEqualTo("isDeleted", false);
        List<ProductType> list = productTypeService.selectByExample(example);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("option", list);

        return modelAndView;
    }

}